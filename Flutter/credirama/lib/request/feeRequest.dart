import 'dart:core';

class FeeRequest {

  DateTime date;
  int accountId;

  FeeRequest(this.date, this.accountId);

  Map<String, String> toSend() {
    Map<String, String> result = new Map();
    result.putIfAbsent("date", () => dateToString(date));
    result.putIfAbsent("accountId", () => accountId.toString());
    return result;
  }

  String dateToString(DateTime dateTime) {
    return '${dateTime.month}/${dateTime.day}/${dateTime.year} ${dateTime.hour}:${dateTime.minute}:${dateTime.second}';
  }

  @override
  String toString() {
    return 'FeeRequest{accountId: ${accountId.toString()}, date: ${dateToString(date)}}';
  }


}