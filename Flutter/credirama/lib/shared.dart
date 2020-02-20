import 'package:shared_preferences/shared_preferences.dart';

SharedPreferences prefs;

Map<String, String> ipAddressMap = {"HugoC": "192.168.1.4", "Elena": "10.188.146.90", "Prune": "192.168.43.190", "PruneEduroam" : "172.19.250.238", "ElenaPolytech": "10.212.107.8", "HugoCPolytech":"10.212.106.156", "HugoFPortable":"192.168.43.25"};

String currentIpAddress;