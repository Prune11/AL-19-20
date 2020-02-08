import 'dart:convert';

import 'package:credirama/model/transactionObject.dart';
import 'package:credirama/model/transactionRequest.dart';
import 'package:http/http.dart' as http;
import 'package:credirama/shared.dart';

class RestService {
  String _ipAddress = currentIpAddress ?? prefs.getString('ipAddress') ?? ipAddressMap['HugoC'];
  String _mea = ":8081";
  String _transaction = ":8084";
  String _prettyDump = ":8085";

  Future<double> getBalance(int account) async {
    var url = new Uri.http( _ipAddress + _mea, '/access/balance/' + account.toString());
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
    var url = new Uri.http(_ipAddress + _transaction, "/access/operations/flutter");
    //print("sendRequest");
    print(url);
    var response = await http.post(url, body: request.toSend());
    //print('Response status: ${response.statusCode}');
    print('Response body: ${response.body}');
  }

  Future getAllTransactions() async {
    var url = new Uri.http(_ipAddress + _transaction, "/access/operations");
    var response = await http.get(url);
    //print('Response status: ${response.statusCode}');
    //print('Response body: ${response.body}');
    List<TransactionObject> result = [];
    if (response.statusCode == 200) {
      Iterable list = json.decode(response.body);
      result = list.map((model) => TransactionObject.fromJson(model)).toList();
      return result;
    } else {
      // If that response was not OK, throw an error.
      throw Exception('Failed to load transactions');
    }
  }

  void getPrettyDump() async {
    print("requete envoyée");
    var url = new Uri.http(_ipAddress + _prettyDump, '/prettyDump');
    var response = await http.get(url);
    print('Response status: ${response.statusCode}');
    print('Response body: ${response.body}');
  }
}
