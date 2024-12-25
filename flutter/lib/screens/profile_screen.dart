import 'package:flutter/material.dart';
import '../widgets/base_screen_layout.dart';

class ProfileScreen extends StatelessWidget {
  const ProfileScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return BaseScreenLayout(
      currentIndex: 3,
      child: SafeArea(
        child: SingleChildScrollView(
          padding: const EdgeInsets.all(16),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              const SizedBox(height: 20),
              const CircleAvatar(
                radius: 50,
                backgroundImage: AssetImage('assets/profile.jpg'),
              ),
              const SizedBox(height: 20),
              const Text(
                'George Wilson',
                style: TextStyle(
                  fontSize: 24,
                  fontWeight: FontWeight.bold,
                ),
              ),
              const SizedBox(height: 8),
              Text(
                'Active',
                style: TextStyle(
                  color: Colors.green[600],
                  fontSize: 16,
                ),
              ),
              const SizedBox(height: 32),
              _buildProfileOption(Icons.settings, 'Settings'),
              _buildProfileOption(Icons.notification_important, 'Notifications'),
              _buildProfileOption(Icons.lock, 'Privacy'),
              _buildProfileOption(Icons.help, 'Help & Support'),
              _buildProfileOption(Icons.logout, 'Logout'),
            ],
          ),
        ),
      ),
    );
  }

  Widget _buildProfileOption(IconData icon, String title) {
    return ListTile(
      leading: Icon(icon),
      title: Text(title),
      trailing: const Icon(Icons.chevron_right),
      onTap: () {},
    );
  }
}
