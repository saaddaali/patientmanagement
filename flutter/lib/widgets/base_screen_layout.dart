import 'package:flutter/material.dart';
import 'package:patient_app/models/contact.dart';
import '../screens/home_screen.dart';
import '../screens/map_screen.dart';
import '../screens/profile_screen.dart';
import '../screens/contacts_screen.dart';

class BaseScreenLayout extends StatefulWidget {
  final int currentIndex;
  final Widget child;
  final List<Contact> contacts = [
    Contact(name: 'Kevin', imageUrl: 'assets/kevin.jpg', status: 'Available'),
    Contact(name: 'Amber', imageUrl: 'assets/amber.jpg', status: 'Available'),
    Contact(
        name: 'Rasheed & Josh',
        imageUrl: 'assets/rasheed.jpg',
        status: 'Available'),
    Contact(name: 'Jacob', imageUrl: 'assets/jacob.jpg', status: 'Available'),
  ];

   BaseScreenLayout({
    Key? key,
    required this.currentIndex,
    required this.child,
  }) : super(key: key);


  @override
  State<BaseScreenLayout> createState() => _BaseScreenLayoutState();
}

class _BaseScreenLayoutState extends State<BaseScreenLayout> {
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

  void _onItemTapped(int index) {
    if (index == widget.currentIndex) return;

    switch (index) {
      case 0:
        Navigator.of(context).pushReplacement(
          MaterialPageRoute(builder: (context) =>  HomeScreen()),
        );
        break;
      case 1:
        Navigator.of(context).pushReplacement(
          MaterialPageRoute(builder: (context) => const MapScreen()),
        );
        break;
      case 2:
        Navigator.of(context).pushReplacement(
          MaterialPageRoute(builder: (context) =>  ContactsScreen()),
        );
        break;
      case 3:
        Navigator.of(context).pushReplacement(
          MaterialPageRoute(builder: (context) => ProfileScreen()),
        );
        break;
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: widget.child,
      bottomNavigationBar: Container(
        decoration: BoxDecoration(
          boxShadow: [
            BoxShadow(
              color: Colors.grey.withOpacity(0.2),
              spreadRadius: 1,
              blurRadius: 10,
              offset: const Offset(0, -3),
            ),
          ],
        ),
        child: ClipRRect(
          borderRadius: const BorderRadius.vertical(top: Radius.circular(20)),
          child: BottomNavigationBar(
            currentIndex: widget.currentIndex,
            onTap: _onItemTapped,
            type: BottomNavigationBarType.fixed,
            backgroundColor: Colors.white,
            selectedItemColor: Theme.of(context).primaryColor,
            unselectedItemColor: Colors.grey,
            selectedFontSize: 12,
            unselectedFontSize: 12,
            elevation: 0,
            items: _navigationItems.map((item) {
              return BottomNavigationBarItem(
                icon: Icon(item.icon),
                activeIcon: Icon(item.selectedIcon),
                label: item.label,
              );
            }).toList(),
          ),
        ),
      ),
    );
  }
} 