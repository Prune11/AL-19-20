/// Class created for managing Client objects
import 'accountObject.dart';

class ClientObject {
  int id;
  String name;
  List<int> accountList;

  ClientObject({int id, String name, List<int> accountList}) {
    this.id = id;
    this.name = name;
    this.accountList = accountList;
  }

  factory ClientObject.fromJson(Map<String, dynamic> json) {
    return ClientObject(
      id: json['id'],
      name: json['name'],
      accountList: json['accountIds']
    );
  }
}