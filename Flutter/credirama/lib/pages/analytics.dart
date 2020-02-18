import 'dart:ui';

import 'package:credirama/common/MyAppBar.dart';
import 'package:credirama/common/myDrawer.dart';
import 'package:credirama/model/transactionObject.dart';
import 'package:credirama/model/simulation.dart';
import 'package:credirama/charts/verticalLabel.dart';
import 'package:credirama/services/restService.dart';
import 'package:credirama/widget/transaction.dart';
import 'package:flutter/rendering.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_datetime_formfield/flutter_datetime_formfield.dart';
import 'package:intl/intl.dart';
import 'package:credirama/request/feeBtw2DatesRequest.dart';

class MyAnalytics extends StatefulWidget {
  @override
  _MyAnalyticsState createState() => _MyAnalyticsState();
}

class _MyAnalyticsState extends State<MyAnalytics> {

  Future<SimulationObject> stats;
  GlobalKey<FormState> _formKey = GlobalKey<FormState>();
  DateTime _dateFrom;
  DateTime _dateTo;

  RestService restSer = RestService();

  TextStyle formatTitleStats = TextStyle(fontSize: 18.0/*, fontWeight: FontWeight.w600*/);
  TextStyle formatValueStats = TextStyle(fontSize: 16.0);

  TransactionObject tMin = new TransactionObject(toId:"Default Transaction", amount:r"+ $ 4,946.00", timeStamp:"28-04-16", transactionType:"credit");
  TransactionObject tMax = new TransactionObject(toId:"Default Transaction", amount:r"+ $ 4,946.00", timeStamp:"28-04-16", transactionType:"credit");

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: MyAppBar(),
        drawer: MyDrawer(),
        body: ListView (
          children: <Widget>[
            Form(
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
                              FeeBtwTwoDatesRequest req = FeeBtwTwoDatesRequest(_dateFrom,_dateTo, 1);
                              //stats = restSer.getFeesWithOtherContracts(req);
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
            FutureBuilder<SimulationObject> (
              future: stats,
              builder: (context, snapshot) {
                if (snapshot.hasData) {
                  //Vars?
                  return ListView (
                    children: <Widget>[
                      Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: Row(
                          mainAxisSize: MainAxisSize.min,
                          children: <Widget>[
                            Text(
                              "Total number of transactions: ",
                              textAlign: TextAlign.left,
                              style: formatTitleStats,
                            ),
                            Text(
                              "value",
                              style: formatValueStats,
                            ),
                          ],
                        ),
                      ),
                      Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: Row(
                          mainAxisSize: MainAxisSize.min,
                          children: <Widget>[
                            Expanded(
                              child: Row(
                                children: <Widget>[
                                  Text ("Total fees: ",
                                    style: formatTitleStats,
                                  ),
                                  Text(
                                    "value",
                                    style: formatValueStats,
                                  ),
                                ],
                              ),
                            ),
                            Expanded(
                              child: Row (
                                children: <Widget>[
                                  Text(
                                    "Total average: ",
                                    textAlign: TextAlign.left,
                                    style: formatTitleStats,
                                  ),
                                  Text(
                                    "value",
                                    style: formatValueStats,
                                  ),
                                ],
                              ),
                            ),
                          ],
                        ),
                      ),
                      Container(
                        child: Column(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          mainAxisSize: MainAxisSize.min,
                          children: <Widget>[
                            Padding(
                              padding: const EdgeInsets.all(8.0),
                              child: Text(
                                "Minimum transaction ",
                                textAlign: TextAlign.left,
                                style: formatTitleStats,
                              ),
                            ),
                            TransactionWidget().transaction(tMin),
                            Padding(
                              padding: const EdgeInsets.all(8.0),
                              child: Text(
                                "Maximum transaction ",
                                style: formatTitleStats,
                              ),
                            ),
                            TransactionWidget().transaction(tMax),
                          ],
                        ),
                      ),
                      Container(
                        height: 200.0,
                        child: VerticalBarLabelChart.feesPerDay(snapshot.data.dailyResult),
                      ),
                      Container(
                        height: 200.0,
                        child: VerticalBarLabelChart.avgPerDay(snapshot.data.dailyResult),
                      ),
                      Container(
                        height: 200.0,
                        child: VerticalBarLabelChart.nbTransactionsPerDay(snapshot.data.dailyResult),
                      ),
                    ],
                  );
                }
                else if (snapshot.hasError){
                  return Column(children: <Widget>[
                    Text("${snapshot.error}"),
                    Divider(),
                  ]);
                }
                return Center(child: Text("Please introduce two dates"),);
              },
            )
          ],
        ),
    );
  }
}