import 'dart:collection';
import 'dart:convert';

import 'package:credirama/model/accountObject.dart';
import 'package:credirama/model/clientObject.dart';
import 'package:credirama/model/simulationObject.dart';
import 'package:credirama/model/simulationPerDay.dart';
import 'package:credirama/model/transactionObject.dart';
import 'package:credirama/request/createAccountRequest.dart';
import 'package:credirama/request/feeBtw2DatesRequest.dart';
import 'package:credirama/request/feeRequest.dart';
import 'package:credirama/request/transactionsBtwTwoDatesRequest.dart';
import 'package:credirama/request/transactionRequest.dart';
import 'package:credirama/response/TransactionsBtwToDatesResponse.dart';
import 'package:credirama/response/feeResponse.dart';
import 'package:http/http.dart' as http;
import 'package:credirama/shared.dart';

class RestService {
  String _ipAddress = currentIpAddress ?? prefs.getString('ipAddress') ?? ipAddressMap['HugoC'];
  String _mea = ":8081";
  String _transaction = ":8084";
  String _analyze = ":8088";
  String _prettyDump = ":8085";

  Future<double> getBalance(int account) async {
    var url = new Uri.http( _ipAddress + _mea, '/access/balance/' + account.toString());
    var response = await http.get(url);
    print('Response status: ${response.statusCode}');
    print('Response body: ${response.body}');
  }

  Future<AccountObject> updateContract(int accountId, String contract) async {
    var url = new Uri.http(_ipAddress + _mea, "/accounts/update/" + accountId.toString() + "/contract/flutter");
    print(url);
    var response = await http.post(url, body: { "contract" : contract});
    return toAccount(response);
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

  Future<List<TransactionObject>> getAllTransactionsFromUser(int userId) async {
    var url = new Uri.http(_ipAddress + _transaction, "/access/operations/from/" + userId.toString());
    var response = await http.get(url);
    return toListTransaction(response);
  }

  Future<List<TransactionObject>> getAllTransactionsToUser(int userId) async {
    var url = new Uri.http(_ipAddress + _transaction, "/access/operations/to/" + userId.toString());
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

  Future<TransactionsBtwTwoDatesResponse> getTransactionsBetweenToDates(int userId, TransactionBtwTwoDatesRequest request) async {
    var url = new Uri.http(_ipAddress + _transaction, "/access/operations/" + userId.toString() + "/dates/flutter");
    print(url);
    var response = await http.post(url, body: request.toSend());
    return toTransactionsBtwTwoDatesResponse(response);
  }

  TransactionsBtwTwoDatesResponse toTransactionsBtwTwoDatesResponse(http.Response response) {
    if (response.statusCode == 200) {
      final decoded = jsonDecode(response.body)["transactionPerDay"] as Map<String, dynamic>;
      Map<String, List<TransactionObject>> map = new HashMap();
      for(String s in decoded.keys.toList()) {
        Iterable list = json.decode(response.body)["transactionPerDay"][s];
        List<TransactionObject> transactionList = list.map((model) => TransactionObject.fromJson(model)).toList();
        map.putIfAbsent(s, () => transactionList);
      }
      return TransactionsBtwTwoDatesResponse.fromStringMap(map);
    } else {
      throw Exception('Failed to load Response');
    }
  }

  Future<FeeResponse> getFee(FeeRequest request) async {
    var url = new Uri.http(_ipAddress + _analyze, "/analyse/fees/day");
    //print("sendRequest");
    print(url);
    var response = await http.post(url, body: request.toSend());
    //print('Response status: ${response.statusCode}');
    //print('Response body: ${response.body}');
    return FeeResponse.fromJson(jsonDecode(response.body));
  }

  Future<SimulationObject>  getFeeBtw2Dates(FeeBtwTwoDatesRequest request) async {
    var url = new Uri.http(_ipAddress + _analyze, "/analyse/fees/btw/day");
    //print("sendRequest");
    print(url);
    print(request.toSend().toString());
    var response = await http.post(url, body: request.toSend());
    //print('Response status: ${response.statusCode}');
    print('Response body: ${response.body}');
    return toSimulation(response);
  }

  Future<Map<String, SimulationObject>> getFeesWithOtherContracts(FeeBtwTwoDatesRequest request) async {
    var url = new Uri.http(_ipAddress + _analyze, "/analyse/simulation");
    //print("sendRequest");
    print(url);
    var response = await http.post(url, body: request.toSend());
    //print('Response status: ${response.statusCode}');
    print('Response body: ${response.body}');
    return toSimulationMap(response);
  }

  SimulationObject toSimulation(http.Response response) {
    if (response.statusCode == 200) {
      final decoded = jsonDecode(response.body);
      final dailyResult = decoded["dailyResult"];
      Map<String, SimulationPerDay> simulationPerDay = new HashMap();
      for(String date in dailyResult.keys.toList()){
        simulationPerDay.putIfAbsent(date, () => SimulationPerDay.fromJson(dailyResult[date]));
      }
      return SimulationObject(dailyResult: simulationPerDay,
          totalSum: decoded["totalSum"],
          totalAvg: decoded["totalAvg"],
          totalNbTransaction: decoded["totalNbTransaction"]);
    } else {
      throw Exception('Failed to load Response');
    }
  }

  Map<String, SimulationObject> toSimulationMap(http.Response response) {
    if (response.statusCode == 200) {
      final decoded = jsonDecode(response.body);
      Map<String, SimulationObject> map = new HashMap();
      for(String contract in decoded.keys.toList()) {
        final dailyResult = decoded[contract]["dailyResult"];
        Map<String, SimulationPerDay> simulationPerDay = new HashMap();
        for(String date in dailyResult.keys.toList()){
          simulationPerDay.putIfAbsent(date, () => SimulationPerDay.fromJson(dailyResult[date]));
        }
        map.putIfAbsent(contract, () => SimulationObject(dailyResult: simulationPerDay,
                                                   totalSum: decoded[contract]["totalSum"],
                                                   totalAvg: decoded[contract]["totalAvg"],
                                                   totalNbTransaction: decoded[contract]["totalNbTransaction"]));
      }
      return map;
    } else {
      throw Exception('Failed to load Response');
    }
  }

  Future<String> getPrettyDump() async {
    print("requete envoyée");
    var url = new Uri.http(_ipAddress + _prettyDump, '/prettyDump');
    var response = await http.get(url);
    print('Response status: ${response.statusCode}');
    print('Response body: ${response.body}');
    return response.body.toString();
  }
}
