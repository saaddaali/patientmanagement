import 'package:flutter/material.dart';
import '../models/contact.dart';

class ContactsScreen extends StatelessWidget {
  final List<Contact> contacts;

  const ContactsScreen({super.key, required this.contacts});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('My Contacts'),
        elevation: 0,
      ),
      body: ListView.builder(
        padding: const EdgeInsets.all(16),
        itemCount: contacts.length,
        itemBuilder: (context, index) {
          final contact = contacts[index];
          return Card(
            child: ListTile(
              leading: CircleAvatar(
                backgroundImage: AssetImage(contact.imageUrl),
              ),
              title: Text(contact.name),
              subtitle: Text(contact.status),
              trailing: Icon(
                Icons.circle,
                size: 12,
                color:
                    contact.status == 'Available' ? Colors.green : Colors.grey,
              ),
            ),
          );
        },
      ),
    );
  }
}
