import 'dart:core';

class TransactionRequest {
  int fromId;
  int toId;
  double amount;
  static String type = "TRANSFER";

  TransactionRequest(this.fromId, this.toId, this.amount);

  Map<String, String> toSend() {
    Map<String, String> result = new Map();
    result.putIfAbsent("fromId", () => this.fromId.toString());
    result.putIfAbsent("toId", () => this.toId.toString());
    result.putIfAbsent("amount", () => this.amount.toString());
    result.putIfAbsent("type", () => type);
    return result;
  }

  @override
  String toString() {
    return 'TransactionRequest{fromId: $fromId, toId: $toId, amount: $amount}';
  }


}