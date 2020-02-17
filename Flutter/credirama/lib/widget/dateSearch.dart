import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_datetime_formfield/flutter_datetime_formfield.dart';
import 'package:intl/intl.dart';

class DateSearchWidget {
  GlobalKey<FormState> _formKey = GlobalKey<FormState>();
  DateTime _dateFrom;
  DateTime _dateTo;

  Container dateSearch(BuildContext context) => Container(
    //decoration:  BoxDecoration(color: Colors.white),
    child: Form(
      key: _formKey,
      child: Row(
        children: <Widget>[
          Expanded(
            child: Padding(
              padding: const EdgeInsets.all(12.0) ,
              child: DateTimeFormField(
                initialValue: DateTime.now(),
                formatter: DateFormat("d-MM-yyyy"),
                label: "From",
                onlyDate: true,
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
                onlyDate: true,
                formatter: DateFormat("d-MM-yyyy"),
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
                child: Text("Go!"),
                onPressed: () {
                  if (_formKey.currentState.validate()) {
                    _formKey.currentState.save();
                  }
                  if (_dateFrom.isBefore(_dateTo)) {
                    print("I should show the results");
                  }
                  else {
                    showDialog(
                      context: context,
                      builder: (BuildContext context) {
                        return AlertDialog(
                          title: Text("ATTENTION!!"),
                          content: Text("From must be before To"),
                        );
                      },
                    );
                  }
                }
                ),
            ),
          ),
        ],
      ),
    ),
  );
}