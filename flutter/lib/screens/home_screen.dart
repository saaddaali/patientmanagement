import 'package:flutter/material.dart';
import '../models/contact.dart';
import '../widgets/contact_avatar.dart';
import '../widgets/sos_button.dart';
import '../widgets/swipe_to_launch_button.dart';

class HomeScreen extends StatelessWidget {
  final List<Contact> contacts;

  const HomeScreen({super.key, required this.contacts});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(
        child: Padding(
          padding: const EdgeInsets.all(16.0),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              _buildHeader(),
              const SizedBox(height: 24),
              const Text(
                'Hi, George!',
                style: TextStyle(
                  fontSize: 28,
                  fontWeight: FontWeight.bold,
                ),
              ),
              const SizedBox(height: 24),
              _buildContactsList(),
              const SizedBox(height: 40),
              const SOSButton(),
              const SizedBox(height: 24),
              Center(
                child: Text(
                  'Your SOS will be sent to 4 people',
                  style: TextStyle(
                    color: Colors.grey[600],
                  ),
                ),
              ),
              const SizedBox(height: 24),
              const SwipeToLaunchButton(),
            ],
          ),
        ),
      ),
    );
  }

  Widget _buildHeader() {
    return const Row(
      mainAxisAlignment: MainAxisAlignment.spaceBetween,
      children: [
        Text(
          'SafeZ',
          style: TextStyle(
            fontSize: 24,
            fontWeight: FontWeight.bold,
          ),
        ),
        Row(
          children: [
            Icon(Icons.notifications_none),
            SizedBox(width: 16),
            CircleAvatar(
              radius: 16,
              backgroundImage: AssetImage('assets/profile.jpg'),
            ),
          ],
        ),
      ],
    );
  }

  Widget _buildContactsList() {
    return Row(
      mainAxisAlignment: MainAxisAlignment.spaceAround,
      children:
          contacts.map((contact) => ContactAvatar(contact: contact)).toList(),
    );
  }
}
