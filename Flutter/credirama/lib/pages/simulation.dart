import 'dart:ui';

import 'package:credirama/common/MyAppBar.dart';
import 'package:credirama/common/myDrawer.dart';
import 'package:credirama/model/transactionObject.dart';
import 'package:credirama/model/simulation.dart';
import 'package:credirama/model/simulationPerDay.dart';
import 'package:credirama/charts/verticalGroupLabel.dart';
import 'package:credirama/widget/transaction.dart';
import 'package:credirama/services/restService.dart';
import 'package:flutter/rendering.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_datetime_formfield/flutter_datetime_formfield.dart';
import 'package:intl/intl.dart';
import 'package:credirama/model/simulation.dart';
import 'package:credirama/request/feeBtw2DatesRequest.dart';

class MySimulation extends StatefulWidget {
  @override
  _MySimulationState createState() => _MySimulationState();
}

class _MySimulationState extends State<MySimulation> {

  GlobalKey<FormState> _formKey = GlobalKey<FormState>();
  DateTime _dateFrom;
  DateTime _dateTo;

  RestService restSer = RestService();

  Future<Map<String, SimulationObject>> simulations;
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
                              simulations = restSer.getFeesWithOtherContracts(req);
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
            FutureBuilder<Map<String, SimulationObject>>(
              future: simulations,
              builder: (context, snapshot){
                if(snapshot.hasData) {
                  var contractRow;
                  snapshot.data.forEach(
                    (contract, sim) => contractRow += Text(contract, style: TextStyle(fontSize: 16.0), ),
                  );
                  return ListView(
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
                                Text("Total fees", style: TextStyle(fontSize: 16.0),),
                                Text("vW", style: TextStyle(fontSize: 16.0),),
                                Text("vS", style: TextStyle(fontSize: 16.0),),
                                Text("vI", style: TextStyle(fontSize: 16.0),),
                                Text("vD", style: TextStyle(fontSize: 16.0),),
                              ]
                          ),
                          TableRow(
                              decoration: BoxDecoration(
                                color: Colors.teal.shade100,
                              ),
                              children: [
                                Text("Total average", style: TextStyle(fontSize: 16.0),),
                                Text("aW.O", style: TextStyle(fontSize: 16.0),),
                                Text("aS.T", style: TextStyle(fontSize: 16.0),),
                                Text("aI.R", style: TextStyle(fontSize: 16.0),),
                                Text("aD.I", style: TextStyle(fontSize: 16.0),),
                              ]
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
                return Center(child: Text("Please introduce two dates"),);
              },
            ),
          ],
        ),
    );
  }
}