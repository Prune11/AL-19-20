import 'dart:ui';

import 'package:credirama/common/MyAppBar.dart';
import 'package:credirama/common/myDrawer.dart';
import 'package:credirama/model/simulationObject.dart';
import 'package:credirama/charts/verticalGroupLabel.dart';
import 'package:credirama/services/restService.dart';
import 'package:flutter/rendering.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_datetime_formfield/flutter_datetime_formfield.dart';
import 'package:intl/intl.dart';
import 'package:credirama/request/feeBtw2DatesRequest.dart';

class MySimulation extends StatefulWidget {
  @override
  _MySimulationState createState() => _MySimulationState();
}

class _MySimulationState extends State<MySimulation> {

  GlobalKey<FormState> _formKey = GlobalKey<FormState>();
  DateTime _dateFrom;
  DateTime _dateTo;

  Future<Map<String, SimulationObject>> simulations;

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
                              if (_dateFrom.isBefore(_dateTo)) {
                                this.setState((){
                                  FeeBtwTwoDatesRequest req = FeeBtwTwoDatesRequest(_dateFrom,_dateTo, 1);
                                  RestService restService = new RestService();
                                  simulations = restService.getFeesWithOtherContracts(req);
                                });
                              }
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
            FutureBuilder<Map<String, SimulationObject>>(
              future: simulations,
              builder: (context, snapshot){
                if(snapshot.hasData) {
                  List<String> contracts = ["Wood", "Stone" ,"Iron", "Diamond"];
                  var contractRow = <Widget>[];
                  var totalFeeRow = <Widget>[];
                  var totalAverageRow = <Widget>[];
                  for( String contract in contracts){
                    contractRow.add(Center( child: Text(contract, style: TextStyle(fontSize: 16.0))));
                    totalFeeRow.add(Center(
                        child: Text(snapshot.data[contract.toUpperCase()].totalSum.toString(),
                            style: TextStyle(fontSize: 16.0))
                        )
                    );
                    totalAverageRow.add(Center(
                        child: Text(snapshot.data[contract.toUpperCase()].totalAvg.toString(),
                            style: TextStyle(fontSize: 16.0))
                        )
                    );
                  }
                  return Column(
                    children: <Widget>[
                      Table(
                        defaultVerticalAlignment: TableCellVerticalAlignment.middle,
                        defaultColumnWidth: IntrinsicColumnWidth(),
                        columnWidths: {0: FractionColumnWidth(.2)},
                        children: [
                          TableRow(
                              decoration: BoxDecoration(
                                color: Colors.teal.shade100,
                              ),
                              children: <Widget>[] ..add(Container()) ..addAll(contractRow),
                          ),
                          TableRow(
                              children: [
                                Column( children: <Widget>[
                                  Text("Total", style: TextStyle(fontSize: 16.0)),
                                  Text("fees", style: TextStyle(fontSize: 16.0))
                                ]),
                              ]..addAll(totalFeeRow)
                          ),
                          TableRow(
                              decoration: BoxDecoration(
                                color: Colors.teal.shade100,
                              ),
                              children: [
                                Column( children: <Widget>[
                                  Text("Total", style: TextStyle(fontSize: 16.0)),
                                  Text("average", style: TextStyle(fontSize: 16.0))
                                ]),
                              ]..addAll(totalAverageRow)
                          ),
                        ],
                      ),
                      Container(
                        margin: EdgeInsets.symmetric(vertical: 10.0),
                        height: 250.0,
                        child: VerticalGroupBarLabelChart.feesPerDay(snapshot.data),
                      ),
                      Container(
                        margin: EdgeInsets.symmetric(vertical: 10.0),
                        height: 250.0,
                        child: VerticalGroupBarLabelChart.avgPerDay(snapshot.data),
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
                return Center(child: Text("Please introduce two dates"));
              },
            ),
          ],
        ),
    );
  }
}