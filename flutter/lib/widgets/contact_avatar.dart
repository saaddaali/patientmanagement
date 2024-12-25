import 'package:flutter/material.dart';
import '../models/contact.dart';

class ContactAvatar extends StatelessWidget {
  final Contact contact;

  const ContactAvatar({super.key, required this.contact});

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        CircleAvatar(
          radius: 30,
          backgroundImage: AssetImage(contact.imageUrl),
        ),
        const SizedBox(height: 8),
        Text(
          contact.name,
          style: const TextStyle(
            fontSize: 12,
          ),
        ),
      ],
    );
  }
}
