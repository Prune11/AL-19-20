import 'dart:convert';

import 'package:credirama/model/accountObject.dart';
import 'package:credirama/model/clientObject.dart';
import 'package:credirama/model/transactionObject.dart';
import 'package:credirama/request/createAccountRequest.dart';
import 'package:credirama/request/transactionRequest.dart';
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

  Future<ClientObject> createClient(String name) async {
    var url = new Uri.http(_ipAddress + _mea, "/clients");
    print(url);
    var response = await http.post(url, body: name);
    return toClient(response);
  }

  Future<ClientObject> getClient(int clientID) async {
    var url = new Uri.http(_ipAddress + _mea, "/clients/" + clientID.toString());
    print(url);
    var response = await http.get(url);
    print(response.body);
    return toClient(response);
  }

  ClientObject toClient(http.Response response) {
    if (response.statusCode == 200) {
      Iterable list = json.decode(response.body)['accountList'];
      List<AccountObject> accountList = list.map((model) => AccountObject.fromJson(model)).toList();
      ClientObject result = ClientObject(id: json.decode(response.body)['id'], name: json.decode(response.body)['name'], accountList: accountList);
      return result;
    } else {
      throw Exception('Failed to load Clients');
    }
  }

  Future<AccountObject> createAccount(CreateAccountRequest request) async {
    var url = new Uri.http(_ipAddress + _mea, "/accounts");
    print(url);
    var response = await http.post(url, body: request.toSend());
    return toAccount(response);
  }

  AccountObject toAccount(http.Response response) {
    if (response.statusCode == 200) {
      AccountObject account = AccountObject.fromJson(json.decode(response.body));
      return account;
    } else {
      print(("Catch Error"));
      throw Exception('Failed to load account');
    }
  }

  Future<http.Response> postTransaction(TransactionRequest request) async {
    var url = new Uri.http(_ipAddress + _transaction, "/access/operations/flutter");
    //print("sendRequest");
    print(url);
    var response = await http.post(url, body: request.toSend());
    //print('Response status: ${response.statusCode}');
    print('Response body: ${response.body}');
  }

  Future<List<TransactionObject>> getAllTransactions() async {
    var url = new Uri.http(_ipAddress + _transaction, "/access/operations");
    var response = await http.get(url);
    return toListTransaction(response);
  }

  Future getAllTransactionsFromUser(int userId) async {
    var url = new Uri.http(_ipAddress + _transaction, "/access/operations/from/" + userId.toString());
    var response = await http.get(url);
    return toListTransaction(response);
  }

  Future getAllTransactionsToUser(int userId) async {
    var url = new Uri.http(_ipAddress + _transaction, "/access/operations/to" + userId.toString());
    var response = await http.get(url);
    return toListTransaction(response);
  }

  List<TransactionObject> toListTransaction(http.Response response) {
    List<TransactionObject> result = [];
    if (response.statusCode == 200) {
      Iterable list = json.decode(response.body);
      result = list.map((model) => TransactionObject.fromJson(model)).toList();
      return result;
    } else {
      print(("Catch Error"));
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
