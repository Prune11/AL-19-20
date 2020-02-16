import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_datetime_formfield/flutter_datetime_formfield.dart';

class DateSearchWidget {
  Key _formKey = GlobalKey<FormState>();
  DateTime _dateFrom;
  DateTime _dateTo;

  Container dateSearch(BuildContext context) => Container(
    decoration:  BoxDecoration(color: Colors.white),
    child: Row(
      children: <Widget>[
        Expanded(
          child: Padding(
            padding: const EdgeInsets.all(12.0) ,
            child: DateTimeFormField(
              initialValue: DateTime.now(),
              label: "From",
              validator: (DateTime dateTime) {
                if (dateTime == null) {
                  return "Date Time Required";
                }
                return null;
              },
              onSaved: (DateTime dateTime) => _dateFrom = dateTime,
            ),
          ),
        ),
        Expanded(
          child: Padding(
            padding: const EdgeInsets.all(12.0) ,
            child: DateTimeFormField(
              initialValue: DateTime.now(),
              label: "To",
              validator: (DateTime dateTime) {
                if (dateTime == null) {
                  return "Date Time Required";
                }
                return null;
              },
              onSaved: (DateTime dateTime) => _dateTo = dateTime,
            ),
          ),
        ),
        Expanded(
          child: Padding(
            padding: const EdgeInsets.all(16.0),
            child: RaisedButton(
              child: Text("Submit"),
              onPressed: () {
                print(_dateTo.toString());
                print(_dateFrom.toString());
                },
            ),
          ),
        ),
      ],
    ),
  );
}