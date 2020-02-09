///  Class created for managing Account objects
class AccountObject {
  int id;
  int ownerId;
  String contract;
  List<int> transactions;
  double balance;

  AccountObject({int id, int ownerId, String contract, List<int> transactions, double balance}) {
    this.id = id;
    this.ownerId = ownerId;
    this.contract = contract;
    this.transactions = transactions;
    this.balance = balance;
  }

  factory AccountObject.fromJson(Map<String, dynamic> json) {
    return AccountObject(
      id: json['id'],
      ownerId: json['ownerId'],
      contract: json['contract'],
      transactions: json['transactionIDs'].cast<int>(),
      balance: json['balance'],
    );
  }

  @override
  String toString() {
    return 'AccountObject{id: $id, ownerId: $ownerId, contract: $contract, transactions: $transactions, balance: $balance}';
  }
}