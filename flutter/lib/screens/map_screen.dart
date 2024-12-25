import 'package:flutter/material.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';
import 'package:geolocator/geolocator.dart';
import '../widgets/location_filter.dart';

class MapScreen extends StatefulWidget {
  const MapScreen({super.key});

  @override
  _MapScreenState createState() => _MapScreenState();
}

class _MapScreenState extends State<MapScreen> {
  GoogleMapController? mapController;
  Position? currentPosition;

  @override
  void initState() {
    super.initState();
    _checkPermissionAndGetLocation();
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
      Position position = await Geolocator.getCurrentPosition(
          desiredAccuracy: LocationAccuracy.high);
      setState(() {
        currentPosition = position;
      });
      // Update map camera position to the current location
      if (mapController != null && currentPosition != null) {
        mapController!.animateCamera(
          CameraUpdate.newLatLng(
            LatLng(currentPosition!.latitude, currentPosition!.longitude),
          ),
        );
      }
    } catch (e) {
      print("Error getting location: $e");
    }
  }

  // Listen to location changes for real-time updates
  void _listenToLocationChanges() {
    Geolocator.getPositionStream(
      locationSettings: const LocationSettings(
        accuracy: LocationAccuracy.high,
        distanceFilter: 10,  // Update every 10 meters
      ),
    ).listen((Position position) {
      setState(() {
        currentPosition = position;
      });

      // Update map camera position to the new location
      if (mapController != null && currentPosition != null) {
        mapController!.animateCamera(
          CameraUpdate.newLatLng(
            LatLng(currentPosition!.latitude, currentPosition!.longitude),
          ),
        );
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        children: [
          _buildHeader(),
          const LocationFilterBar(),
          Expanded(
            child: _buildMap(),
          ),
        ],
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _getCurrentLocation,
        child: const Icon(Icons.my_location),
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
    mapController?.dispose();
    super.dispose();
  }
}
