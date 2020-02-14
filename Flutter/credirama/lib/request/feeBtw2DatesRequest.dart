import 'dart:core';

class FeeBtwTwoDatesRequest {

  DateTime from;
  DateTime to;
  int accountId;

  FeeBtwTwoDatesRequest(this.from, this.to, this.accountId);

  Map<String, String> toSend() {
    Map<String, String> result = new Map();
    result.putIfAbsent("from", () => dateToString(from));
    result.putIfAbsent("to", () => dateToString(to));
    result.putIfAbsent("accountId", () => this.accountId.toString());
    return result;
  }

  String dateToString(DateTime dateTime) {
    return '${dateTime.month}/${dateTime.day}/${dateTime.year} ${dateTime.hour}:${dateTime.minute}:${dateTime.second}';
  }

  @override
  String toString() {
    return 'FeeBtwTwoDatesRequest{accountId: ${accountId.toString()}, from: ${dateToString(from)}, to: ${dateToString(to)}}';
  }


}