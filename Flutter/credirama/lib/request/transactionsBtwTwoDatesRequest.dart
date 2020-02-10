import 'dart:core';

class TransactionBtwTwoDatesRequest {

  DateTime dateFrom;
  DateTime dateTo;

  TransactionBtwTwoDatesRequest(this.dateFrom, this.dateTo);

  Map<String, String> toSend() {
    Map<String, String> result = new Map();
    result.putIfAbsent("dateFrom", () => dateToString(dateFrom));
    result.putIfAbsent("dateTo", () => dateToString(dateTo));
    return result;
  }

  String dateToString(DateTime dateTime) {
    return '${dateTime.month}/${dateTime.day}/${dateTime.year} ${dateTime.hour}:${dateTime.minute}:${dateTime.second}';
  }

  @override
  String toString() {
    return 'TransactionBtwTwoDatesRequest{dateFrom: ${dateToString(dateFrom)}, dateTo: ${dateToString(dateTo)}}';
  }


}