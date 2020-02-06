/// Class created for managing Client objects
import 'accountObject.dart';

class ClientObject {
  String name;
  List<AccountObject> accountList;

  ClientObject(String name, List<AccountObject> accountList) {
    this.name = name;
    this.accountList = accountList;
  }
}