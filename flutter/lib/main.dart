import 'package:flutter/material.dart';
import 'screens/home_screen.dart';
import 'screens/map_screen.dart';
import 'screens/contacts_screen.dart';
import 'screens/profile_screen.dart';
import 'models/contact.dart';

void main() {
  runApp(const LocationSharingApp());
}

class LocationSharingApp extends StatefulWidget {
  const LocationSharingApp({super.key});

  @override
  _LocationSharingAppState createState() => _LocationSharingAppState();
}

class _LocationSharingAppState extends State<LocationSharingApp> {
  int _currentIndex = 0;

  final List<Contact> contacts = [
    Contact(name: 'Kevin', imageUrl: 'assets/kevin.jpg', status: 'Available'),
    Contact(name: 'Amber', imageUrl: 'assets/amber.jpg', status: 'Available'),
    Contact(
        name: 'Rasheed & Josh',
        imageUrl: 'assets/rasheed.jpg',
        status: 'Available'),
    Contact(name: 'Jacob', imageUrl: 'assets/jacob.jpg', status: 'Available'),
  ];

  late final List<Widget> _screens;

  @override
  void initState() {
    super.initState();
    _screens = [
      HomeScreen(contacts: contacts),
      ContactsScreen(contacts: contacts),
      const MapScreen(),
      const ProfileScreen(),
    ];
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData(
        primarySwatch: Colors.blue,
        scaffoldBackgroundColor: Colors.white,
      ),
      home: Scaffold(
        body: _screens[_currentIndex],
        bottomNavigationBar: BottomNavigationBar(
          items: const [
            BottomNavigationBarItem(
              icon: Icon(Icons.home),
              label: 'Home',
            ),
            BottomNavigationBarItem(
              icon: Icon(Icons.contacts),
              label: 'Contacts',
            ),
            BottomNavigationBarItem(
              icon: Icon(Icons.location_on),
              label: 'Zone',
            ),
            BottomNavigationBarItem(
              icon: Icon(Icons.person),
              label: 'Profile',
            ),
          ],
          currentIndex: _currentIndex,
          type: BottomNavigationBarType.fixed,
          onTap: (index) {
            setState(() {
              _currentIndex = index;
            });
          },
        ),
      ),
    );
  }
}
