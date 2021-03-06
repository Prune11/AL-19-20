import 'dart:ui';

import 'package:credirama/common/MyAppBar.dart';
import 'package:credirama/common/myDrawer.dart';
import 'package:credirama/model/transactionObject.dart';
import 'package:credirama/model/simulationObject.dart';
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

  final int _accountId;

  MyAnalytics(this._accountId);

  @override
  _MyAnalyticsState createState() => _MyAnalyticsState(_accountId);
}

class _MyAnalyticsState extends State<MyAnalytics> {

  final int _accountId;

  _MyAnalyticsState(this._accountId);

  Future<SimulationObject> stats;
  GlobalKey<FormState> _formKey = GlobalKey<FormState>();
  DateTime _dateFrom;
  DateTime _dateTo;

  TextStyle formatTitleStats = TextStyle(fontSize: 18.0);
  TextStyle formatValueStats = TextStyle(fontSize: 16.0);

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
                        label: "Date début",
                        onlyDate: true,
                        validator: (DateTime dateTime) {
                          if (dateTime == null) {
                            return "Date requise";
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
                        label: "Date fin",
                        validator: (DateTime dateTime) {
                          if (dateTime == null) {
                            return "Date Requise";
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
                          child: Text("Calcul"),
                          onPressed: () {
                            if (_formKey.currentState.validate()) {
                              _formKey.currentState.save();
                              if (_dateFrom.isBefore(_dateTo) || _dateFrom.isAtSameMomentAs(_dateTo)) {
                                this.setState((){
                                  FeeBtwTwoDatesRequest req = FeeBtwTwoDatesRequest(_dateFrom,_dateTo, _accountId);
                                  RestService restService = new RestService();
                                  stats = restService.getFeeBtw2Dates(req);
                                });
                              }
                            }

                            else {
                              showDialog(
                                context: context,
                                builder: (BuildContext context) {
                                  return AlertDialog(
                                    title: Text("ATTENTION!!"),
                                    content: Text("Dates saisies invalides"),
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
                  return Column (
                    children: <Widget>[
                      Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: Row(
                          mainAxisSize: MainAxisSize.min,
                          children: <Widget>[
                            Text(
                              "Nombre totale de transactions: ",
                              textAlign: TextAlign.left,
                              style: formatTitleStats,
                            ),
                            Text(
                              snapshot.data.totalNbTransaction.toString(),
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
                                  Text ("Total frais: ",
                                    style: formatValueStats,
                                  ),
                                  Text(
                                    snapshot.data.totalSum.toString(),
                                    style: formatValueStats,
                                  ),
                                ],
                              ),
                            ),
                            Expanded(
                              child: Row (
                                children: <Widget>[
                                  Text(
                                    "Moyenne: ",
                                    textAlign: TextAlign.left,
                                    style: formatValueStats,
                                  ),
                                  Text(
                                    snapshot.data.totalAvg.toString(),
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
                                "Plus petite transaction ",
                                textAlign: TextAlign.left,
                                style: formatTitleStats,
                              ),
                            ),
                            TransactionWidget().transaction(snapshot.data.globalMinTransaction, _accountId),
                            Padding(
                              padding: const EdgeInsets.all(8.0),
                              child: Text(
                                "Plus grosse transaction ",
                                style: formatTitleStats,
                              ),
                            ),
                            TransactionWidget().transaction(snapshot.data.globalMaxTransaction, _accountId),
                          ],
                        ),
                      ),
                      Container(
                        height: 200.0,
                        child: VerticalBarLabelChart.feesPerDay(snapshot.data),
                      ),
                      Container(
                        height: 200.0,
                        child: VerticalBarLabelChart.avgPerDay(snapshot.data),
                      ),
                      Container(
                        height: 200.0,
                        child: VerticalBarLabelChart.nbTransactionsPerDay(snapshot.data),
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
                return Center(child: Text("Veuillez selectionner deux dates"));
              },
            )
          ],
        ),
    );
  }
}