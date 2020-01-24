import 'dart:io';

import 'package:http/http.dart' as http;

class RestService {
  String _mea = "192.168.43.190:8081";
  String _prettyDump = "192.168.43.190:8085";

  Future<double> getBalance() async {
    var url = new Uri.http(_mea, '/access/balance/1');
    var response = await http.get(url);
    print('Response status: ${response.statusCode}');
    print('Response body: ${response.body}');
  }

  Future<http.Response> fetchPost() async {
    var response =
        await http.get('https://jsonplaceholder.typicode.com/posts/1');
    print('Response status: ${response.statusCode}');
    print('Response body: ${response.body}');
  }

  void getPrettyDump() async {
    final response = await http.get('http://10.0.2.2.:8081/access/balance/1');
    print("ok" + response.toString());
  }
}
