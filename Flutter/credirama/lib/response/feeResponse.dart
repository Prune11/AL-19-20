import 'dart:collection';

import 'package:credirama/model/transactionObject.dart';
import 'package:intl/intl.dart';

class FeeResponse {

  String date;
  int accountId;
  double sum;
  double avg;
  int nbTransaction;

  FeeResponse({String date, int accountId, double sum, double avg, int nbTransaction}) {
    this.date = date;
    this.accountId = accountId;
    this.sum = sum;
    this.avg = avg;
    this.nbTransaction = nbTransaction;
  }

  factory FeeResponse.fromJson(Map<String, dynamic> json) {
    return FeeResponse(
        date: json['date'].toString(),
        accountId: json['accountId'],
        sum: json['sum'],
        avg: json['avg'],
        nbTransaction: json['nbTransaction']);
  }

  @override
  String toString() {
    return 'FeeResponse{date: $date, accountId: $accountId, sum: $sum, avg: $avg, nbTransaction: $nbTransaction}';
  }


}
