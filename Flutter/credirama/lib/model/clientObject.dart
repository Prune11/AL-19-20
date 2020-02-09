/// Class created for managing Client objects
import 'accountObject.dart';

class ClientObject {
  int id;
  String name;
  List<AccountObject> accountList;

  ClientObject({int id, String name, List<AccountObject> accountList}) {
    this.id = id;
    this.name = name;
    this.accountList = accountList;
  }

  @override
  String toString() {
    return 'ClientObject{id: $id, name: $name, accountList: $accountList}';
  }
}