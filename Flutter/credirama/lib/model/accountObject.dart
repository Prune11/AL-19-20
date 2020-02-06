///  Class created for managing Account objects
class AccountObject {
  String amount;
  List<String> transactionList;

  AccountObject(String amount, List<String> transactionList) {
    this.amount = amount;
    this.transactionList = transactionList;
  }
}