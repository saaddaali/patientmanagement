import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';
import '../models/contact.dart';
import 'login_screen.dart';
import '../widgets/base_screen_layout.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';

class HomeScreen extends StatefulWidget {

  final List<Contact> contacts = [
    Contact(name: 'Kevin', imageUrl: 'assets/kevin.jpg', status: 'Available'),
    Contact(name: 'Amber', imageUrl: 'assets/amber.jpg', status: 'Available'),
    Contact(
        name: 'Rasheed & Josh',
        imageUrl: 'assets/rasheed.jpg',
        status: 'Available'),
    Contact(name: 'Jacob', imageUrl: 'assets/jacob.jpg', status: 'Available'),
  ];

  @override
  _HomeScreenState createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  String username = '';
  String email = '';
  int _selectedIndex = 0;

  // Add navigation items
  final List<NavigationItem> _navigationItems = [
    NavigationItem(
      label: 'Home',
      icon: Icons.home_outlined,
      selectedIcon: Icons.home,
    ),
    NavigationItem(
      label: 'Location',
      icon: Icons.location_on_outlined,
      selectedIcon: Icons.location_on,
    ),
    NavigationItem(
      label: 'Contacts',
      icon: Icons.group,
      selectedIcon: Icons.group,
    ),
    NavigationItem(
      label: 'Profile',
      icon: Icons.person_outline,
      selectedIcon: Icons.person,
    ),
  ];

  @override
  void initState() {
    super.initState();
    _loadUserData();
  }

  Future<void> _loadUserData() async {
    final prefs = await SharedPreferences.getInstance();
    setState(() {
      username = prefs.getString('username') ?? 'Patient';
      email = prefs.getString('email') ?? 'patient@example.com';
    });
  }



  Future<void> _logout() async {
    final prefs = await SharedPreferences.getInstance();
    await prefs.clear();
    if (!mounted) return;
    
    Navigator.of(context).pushAndRemoveUntil(
      MaterialPageRoute(builder: (context) => const LoginScreen()),
      (Route<dynamic> route) => false,
    );
  }

  @override
  Widget build(BuildContext context) {
    return BaseScreenLayout(
      currentIndex: 0,
      child: SafeArea(
        child: SingleChildScrollView(
          child: Column(
            children: [
              _buildHeader(),
              _buildQuickActions(),
              _buildRecentContacts(),
              _buildSOSCircle(),
            ],
          ),
        ),
      ),
    );
  }

  Widget _buildHeader() {
    return Container(
      padding: const EdgeInsets.all(20),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text(
                'Welcome back,',
                style: TextStyle(
                  fontSize: 14,
                  color: Colors.grey[600],
                ),
              ),
              const SizedBox(height: 4),
              Text(
                username,
                style: const TextStyle(
                  fontSize: 24,
                  fontWeight: FontWeight.bold,
                ),
              ),
            ],
          ),
          IconButton(
            icon: const Icon(Icons.logout),
            onPressed: _logout,
          ),
        ],
      ),
    );
  }

  Widget _buildQuickActions() {
    return Container(
      padding: const EdgeInsets.all(10),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          const Text(
            'Quick Actions',
            style: TextStyle(
              fontSize: 18,
              fontWeight: FontWeight.bold,
            ),
          ),
          const SizedBox(height: 16),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceAround,
            children: [
              _buildActionButton(
                icon: Icons.location_on,
                label: 'Share\nLocation',
                color: Colors.blue,
                onTap: () {
                  // Navigate to location sharing
                },
              ),
              _buildActionButton(
                icon: Icons.medical_services,
                label: 'Medical\nInfo',
                color: Colors.green,
                onTap: () {
                  // Navigate to medical info
                },
              ),
              _buildActionButton(
                icon: Icons.emergency,
                label: 'Emergency\nContacts',
                color: Colors.red,
                onTap: () {
                  // Navigate to emergency contacts
                },
              ),
            ],
          ),
        ],
      ),
    );
  }

  Widget _buildActionButton({
    required IconData icon,
    required String label,
    required Color color,
    required VoidCallback onTap,
  }) {
    return GestureDetector(
      onTap: onTap,
      child: Column(
        children: [
          Container(
            padding: const EdgeInsets.all(16),
            decoration: BoxDecoration(
              color: color.withOpacity(0.1),
              borderRadius: BorderRadius.circular(12),
            ),
            child: Icon(
              icon,
              color: color,
              size: 32,
            ),
          ),
          const SizedBox(height: 8),
          Text(
            label,
            textAlign: TextAlign.center,
            style: TextStyle(
              fontSize: 12,
              color: Colors.grey[600],
            ),
          ),
        ],
      ),
    );
  }

  Widget _buildRecentContacts() {
    return Container(
      padding: const EdgeInsets.all(20),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          const Text(
            'Recent Contacts',
            style: TextStyle(
              fontSize: 18,
              fontWeight: FontWeight.bold,
            ),
          ),
          const SizedBox(height: 16),
          SizedBox(
            height: 100,
            child: ListView.builder(
              scrollDirection: Axis.horizontal,
              itemCount: widget.contacts.length,
              itemBuilder: (context, index) {
                final contact = widget.contacts[index];
                return Padding(
                  padding: const EdgeInsets.only(right: 16),
                  child: Column(
                    children: [
                      CircleAvatar(
                        radius: 30,
                        backgroundImage: AssetImage(contact.imageUrl),
                      ),
                      const SizedBox(height: 8),
                      Text(
                        contact.name,
                        style: const TextStyle(fontSize: 12),
                      ),
                    ],
                  ),
                );
              },
            ),
          ),
        ],
      ),
    );
  }


  Widget _buildSOSCircle() {
    return Container(
      padding: const EdgeInsets.symmetric(vertical: 20),
      child: Column(
        children: [
          GestureDetector(
            onTap: () {
              // Handle SOS action
              _handleSOS();
            },
            child: Container(
              width: 190,
              height: 190,
              decoration: BoxDecoration(
                shape: BoxShape.circle,
                color: Colors.red.shade50,
                border: Border.all(
                  color: Colors.red,
                  width: 3,
                ),
                boxShadow: [
                  BoxShadow(
                    color: Colors.red.withOpacity(0.3),
                    spreadRadius: 2,
                    blurRadius: 8,
                    offset: const Offset(0, 2),
                  ),
                ],
              ),
              child: Center(
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    Icon(
                      Icons.emergency,
                      size: 50,
                      color: Colors.red.shade700,
                    ),
                    const SizedBox(height: 8),
                    Text(
                      'SOS',
                      style: TextStyle(
                        fontSize: 24,
                        fontWeight: FontWeight.bold,
                        color: Colors.red.shade700,
                      ),
                    ),
                  ],
                ),
              ),
            ),
          ),
          const SizedBox(height: 12),
          Text(
            'Press for Emergency',
            style: TextStyle(
              color: Colors.grey[600],
              fontSize: 14,
            ),
          ),
        ],
      ),
    );
  }

  Future<void> _handleSOS() async {
  showDialog(
    context: context,
    builder: (BuildContext context) {
      return AlertDialog(
        title: Row(
          children: [
            Icon(Icons.warning_amber_rounded, color: Colors.red.shade700),
            const SizedBox(width: 8),
            const Text('Emergency Alert'),
          ],
        ),
        content: const Text(
          'This will alert your emergency contacts and send a warning. Continue?'
        ),
        actions: [
          TextButton(
            onPressed: () => Navigator.of(context).pop(),
            child: const Text('Cancel'),
          ),
          TextButton(
            onPressed: () async {
              Navigator.of(context).pop();
              await _sendEmergencyAlert();
            },
            style: TextButton.styleFrom(
              foregroundColor: Colors.white,
              backgroundColor: Colors.red,
            ),
            child: const Text('Send Alert'),
          ),
        ],
      );
    },
  );
}

Future<void> _sendEmergencyAlert() async {
  const String apiUrl = 'http://localhost:8037/api/admin/warningPatient/';
  final DateTime now = DateTime.now();
  final String formattedDate = "${now.month}/${now.day}/${now.year} ${now.hour}:${now.minute}";

  final Map<String, dynamic> payload = {
    "message": "SOS",
    "dateWarning": formattedDate,
    "patient": {
      "id": 9,
    },
    "warningType": {
      "id": 1,
      "code": "danger",
      "libelle": "HIGHT",
      "description": "Hight"
    }
  };

  try {
    final response = await http.post(
      Uri.parse(apiUrl),
      headers: {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGVzIjpbIlJPTEVfQURNSU4iXSwiZXhwIjoxNzM1NDY0NDY1LCJlbWFpbCI6ImFkbWluIn0.SZPqbESxtHqemzswAhbMFy_jsrtQm40cTZ28Xzf1oL0',
    },
      body: jsonEncode(payload),
    );

    if (response.statusCode == 200 || response.statusCode == 201) {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(
          content: Text('Emergency alert sent successfully'),
          backgroundColor: Colors.green,
        ),
      );
    } else {
      print('Failed to send warning. Status code: ${response.statusCode}');
      throw Exception('Failed to send warning. Status code: ${response.statusCode}');
    }
  } catch (error) {
    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(
        content: Text('Error: $error'),
        backgroundColor: Colors.red,
      ),
    );
  }
}}

// Add this class at the top of the file
class NavigationItem {
  final String label;
  final IconData icon;
  final IconData selectedIcon;

  NavigationItem({
    required this.label,
    required this.icon,
    required this.selectedIcon,
  });
}