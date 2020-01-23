import 'package:http/http.dart' as http;

class RestService {
  String _mea = "localhost:8080";
  String _prettyDump = "192.168.43.190:8085";

  Future<double> getBalance() async {
    var url = 'https://localhost:8080/access/balance/1';
    var response = await http.get(url);
    print('Response status: ${response.statusCode}');
    print('Response body: ${response.body}');
  }

  Future<http.Response> fetchPost() {
    print(http.get('https://jsonplaceholder.typicode.com/posts/1'));
  }

  void getPrettyDump() async {
   // var response = http.get(_prettyDump + "/prettyDump");
    var url = new Uri.http("192.168.43.190:8085", "/prettyDump");
    print('hehehe');
    var response = await http.get(url);
    print('Response status: ${response.statusCode}');
    print('Response body: ${response.body}');

  }
}
