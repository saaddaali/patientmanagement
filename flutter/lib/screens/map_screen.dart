import 'package:flutter/material.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';
import 'package:geolocator/geolocator.dart';
import 'package:shared_preferences/shared_preferences.dart';
import '../widgets/location_filter.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';
import 'dart:async';
import '../widgets/base_screen_layout.dart';

class MapScreen extends StatefulWidget {
  const MapScreen({super.key});

  @override
  _MapScreenState createState() => _MapScreenState();
}

class _MapScreenState extends State<MapScreen> {
  GoogleMapController? mapController;
  Position? currentPosition;
  Timer? locationTimer;
  final String baseUrl = 'http://localhost:8036/api/admin/localisation/';
  String? authToken;
  StreamSubscription<Position>? _positionStreamSubscription;
  bool _isDisposed = false;

  @override
  void initState() {
    super.initState();
    _checkPermissionAndGetLocation();
    _loadAuthToken();
    _startLocationSharing();
  }

  // Check permission and get location
  Future<void> _checkPermissionAndGetLocation() async {
    try {
      LocationPermission permission = await Geolocator.checkPermission();
      if (permission == LocationPermission.denied) {
        permission = await Geolocator.requestPermission();
        if (permission == LocationPermission.denied) {
          return;
        }
      }

      if (permission == LocationPermission.deniedForever) {
        return;
      }

      _getCurrentLocation();
    } catch (e) {
      print("Permission error: $e");
    }
  }

  // Get the current location and update the map camera position
  Future<void> _getCurrentLocation() async {
    try {
      _log('Getting current location...');
      Position position = await Geolocator.getCurrentPosition(
          desiredAccuracy: LocationAccuracy.high);
      
      if (_isDisposed) return;
      
      if (mounted) {
        setState(() {
          currentPosition = position;
          _log('Position updated: ${position.latitude}, ${position.longitude}');
        });
      }
      
      _log('Sharing initial location with backend...');
      await _shareLocationWithBackend();
      
      if (mapController != null && currentPosition != null) {
        _log('Updating map camera position');
        mapController!.animateCamera(
          CameraUpdate.newLatLng(
            LatLng(currentPosition!.latitude, currentPosition!.longitude),
          ),
        );
      }
    } catch (e) {
      _log('❌ Error getting location: $e');
    }
  }

  // Listen to location changes for real-time updates
  void _listenToLocationChanges() {
    _log('Starting location stream...');
    _positionStreamSubscription?.cancel();
    _positionStreamSubscription = Geolocator.getPositionStream(
      locationSettings: const LocationSettings(
        accuracy: LocationAccuracy.high,
        distanceFilter: 10,
      ),
    ).listen(
      (Position position) {
        if (_isDisposed) return;
        
        if (mounted) {
          setState(() {
            currentPosition = position;
            _log('Stream position update: ${position.latitude}, ${position.longitude}');
          });
        }

        _log('Sharing location from stream update...');
        _shareLocationWithBackend();
      },
      onError: (error) {
        _log('❌ Location stream error: $error');
      },
      cancelOnError: false,
    );
  }

  // Add this method to handle periodic location sharing
  void _startLocationSharing() {
    _log('Starting periodic location sharing...');
    locationTimer?.cancel();
    locationTimer = Timer.periodic(const Duration(minutes: 1), (timer) {
      if (_isDisposed) {
        _log('Timer cancelled - widget disposed');
        timer.cancel();
        return;
      }
      
      if (currentPosition != null) {
        _log('Timer triggered - sharing location...');
        _shareLocationWithBackend();
        _log('Current position: ${currentPosition!.latitude}, ${currentPosition!.longitude}');
      } else {
        _log('⚠️ Timer triggered but no position available');
      }
    });
  }

  // Add this method to share location with backend
  Future<void> _shareLocationWithBackend() async {
    if (_isDisposed) {
      _log('Skipping location share - widget disposed');
      return;
    }
    if (currentPosition == null) {
      _log('⚠️ Skipping location share - no position available');
      return;
    }
    if (authToken == null) {
      _log('⚠️ Skipping location share - no auth token');
      return;
    }

    try {
      _log('Sending location to backend...');
      final response = await http.post(
        Uri.parse(baseUrl),
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer $authToken',
        },
        body: jsonEncode({
          'dateLocalisation': DateTime.now().toIso8601String(),
          'longitude': currentPosition!.longitude,
          'latitude': currentPosition!.latitude,
          'capteur': {
            'id': 1,
            'code': 'PHONE',
            'libelle': 'Smartphone GPS'
          },
          'patient': {
            'id': 1
          }
        }),
      );

      if (!_isDisposed) {
        if (response.statusCode == 201 || response.statusCode == 200) {
          _log('✅ Location shared successfully');
        } else {
          _log('❌ Failed to share location: ${response.statusCode}');
          _log('Response: ${response.body}');
        }
      }
    } catch (e) {
      if (!_isDisposed) {
        _log('❌ Error sharing location: $e');
      }
    }
  }

  // Add method to load auth token
  Future<void> _loadAuthToken() async {
    if (_isDisposed) return;
    final SharedPreferences prefs = await SharedPreferences.getInstance();
    if (!_isDisposed) {
      setState(() {
        authToken = prefs.getString('auth_token');
      });
    }
  }

  // Add this helper method for logging
  void _log(String message) {
    if (!_isDisposed) {
      print('🌍 LocationService: $message');
    }
  }

  @override
  Widget build(BuildContext context) {
    return BaseScreenLayout(
      currentIndex: 1,
      child: SafeArea(
        child: Column(
          children: [
            _buildHeader(),
            Expanded(
              child: _buildMap(),
            ),
          ],
        ),
      ),
    );
  }

  Widget _buildHeader() {
    return Container(
      height: 60,
      padding: const EdgeInsets.symmetric(horizontal: 16),
      child: const Row(
        children: [
          BackButton(),
          SizedBox(width: 8),
          Text(
            'Location',
            style: TextStyle(
              fontSize: 20,
              fontWeight: FontWeight.bold,
            ),
          ),
        ],
      ),
    );
  }

  // Display the map
  Widget _buildMap() {
    try {
      return currentPosition == null
          ? const Center(child: CircularProgressIndicator())
          : GoogleMap(
              initialCameraPosition: CameraPosition(
                target: LatLng(
                  currentPosition!.latitude,
                  currentPosition!.longitude,
                ),
                zoom: 15,
              ),
              onMapCreated: (GoogleMapController controller) {
                setState(() {
                  mapController = controller;
                });
                _listenToLocationChanges(); // Start listening to location updates
              },
              myLocationEnabled: true,
              myLocationButtonEnabled: false,
              zoomControlsEnabled: false,
              mapType: MapType.normal,
            );
    } catch (e) {
      print('Map error: $e');
      return const Center(child: Text('Error loading map'));
    }
  }

  @override
  void dispose() {
    _log('Disposing MapScreen...');
    _isDisposed = true;
    locationTimer?.cancel();
    _positionStreamSubscription?.cancel();
    mapController?.dispose();
    super.dispose();
  }
}
