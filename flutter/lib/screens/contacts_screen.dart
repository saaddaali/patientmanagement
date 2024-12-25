import 'package:flutter/material.dart';
import '../models/contact.dart';
import '../widgets/base_screen_layout.dart';

class ContactsScreen extends StatelessWidget {
  final List<Contact> contacts = [
    Contact(name: 'Kevin', imageUrl: 'assets/kevin.jpg', status: 'Available'),
    Contact(name: 'Amber', imageUrl: 'assets/amber.jpg', status: 'Available'),
    Contact(name: 'Rasheed & Josh', imageUrl: 'assets/rasheed.jpg', status: 'Available'),
    Contact(name: 'Jacob', imageUrl: 'assets/jacob.jpg', status: 'Available'),
  ];

  @override
  Widget build(BuildContext context) {
    return BaseScreenLayout(
      currentIndex: 2,
      child: Container(
        color: Colors.white,
        child: SafeArea(
          child: Column(
            children: [
              AppBar(
                title: const Text('My Contacts'),
                elevation: 0,
                backgroundColor: Colors.white,
                foregroundColor: Colors.black,
              ),
              Padding(
                padding: const EdgeInsets.all(16.0),
                child: SearchBar(
                  hintText: 'Search contacts...',
                  backgroundColor: MaterialStateProperty.all(Colors.grey[100]),
                  padding: MaterialStateProperty.all(
                    EdgeInsets.symmetric(horizontal: 16),
                  ),
                ),
              ),
              Expanded(
                child: ListView.builder(
                  padding: const EdgeInsets.symmetric(horizontal: 16),
                  itemCount: contacts.length,
                  itemBuilder: (context, index) {
                    final contact = contacts[index];
                    return Card(
                      elevation: 0,
                      color: Colors.grey[100],
                      margin: EdgeInsets.only(bottom: 8),
                      child: ListTile(
                        leading: CircleAvatar(
                          radius: 25,
                          backgroundImage: AssetImage(contact.imageUrl),
                        ),
                        title: Text(
                          contact.name,
                          style: TextStyle(fontWeight: FontWeight.bold),
                        ),
                        subtitle: Text(contact.status),
                        trailing: Icon(
                          Icons.circle,
                          size: 12,
                          color: contact.status == 'Available' ? Colors.green : Colors.grey,
                        ),
                      ),
                    );
                  },
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}