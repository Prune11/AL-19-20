import 'dart:io';

import 'package:credirama/model/transactionRequest.dart';
import 'package:http/http.dart' as http;

class RestService {
  String _mea = "192.168.43.190:8081";
  String _transaction = "192.168.1.4:8084";
  String _prettyDump = "192.168.43.190:8085";

  Future<double> getBalance(int account) async {
    var url = new Uri.http(_mea, '/access/balance/' + account.toString());
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

  Future<http.Response> postTransaction(TransactionRequest request) async {
    var url = new Uri.http(_transaction, "/access/operations/flutter");
    print("sendRequest");
    var response = await http.post(url, body: request.toSend());
    print('Response status: ${response.statusCode}');
    print('Response body: ${response.body}');
  }

  void getPrettyDump() async {
    print("requete envoyée");
    var url = new Uri.http(_prettyDump, '/prettyDump');
    var response = await http.get(url);
    print('Response status: ${response.statusCode}');
    print('Response body: ${response.body}');
  }
}
