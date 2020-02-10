import 'dart:collection';

import 'package:credirama/model/transactionObject.dart';
import 'package:intl/intl.dart';

class TransactionsBtwTwoDatesResponse {

  Map<DateTime, List<TransactionObject>> transactionPerDay;

  TransactionsBtwTwoDatesResponse({Map<DateTime, List<TransactionObject>> transactionPerDay}) {
    this.transactionPerDay = transactionPerDay;
  }

  factory TransactionsBtwTwoDatesResponse.fromStringMap(Map<String, List<TransactionObject>> map) {
    Map<DateTime, List<TransactionObject>> transactionPerDay = new HashMap();
    for(String s in map.keys){
      DateTime date = new DateFormat("MM/dd/yyyy HH:mm:ss").parse(s);
      transactionPerDay.putIfAbsent(date, () => map[s]);
    }
    return TransactionsBtwTwoDatesResponse(transactionPerDay: transactionPerDay);
  }

  @override
  String toString() {
    return 'TransactionsBtwTwoDatesResponse{transactionPerDay: $transactionPerDay}';
  }


}
