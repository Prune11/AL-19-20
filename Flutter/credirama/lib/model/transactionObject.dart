class TransactionObject {
  String id;
  String fromId;
  String toId;
  String amount;
  String timeStamp;
  String transactionType;
  String feeAmount;

  TransactionObject({String id, String fromId, String toId, String amount, String timeStamp, String transactionType, String feeAmount}) {
    this.id = id;
    this.fromId = fromId;
    this.toId = toId;
    this.amount = amount;
    this.timeStamp = timeStamp;
    this.transactionType = transactionType;
    this.feeAmount = feeAmount;
  }

  factory TransactionObject.fromJson(Map<String, dynamic> json) {
    return TransactionObject(
      id: json['id'].toString(),
      fromId: json['fromId'].toString(),
      toId: json['toId'].toString(),
      amount: json['amount'].toString(),
      timeStamp: json['timeStamp'],
      transactionType: json['transactionType'],
      feeAmount: json['feeAmount'].toString(),
    );
  }

  @override
  String toString() {
    return 'TransactionObject{id: $id, fromId: $fromId, toId: $toId, amount: $amount, timeStamp: $timeStamp, transactionType: $transactionType, feeAmount: $feeAmount}';
  }


}
