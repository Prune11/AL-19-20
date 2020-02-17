import 'dart:ui';

import 'package:credirama/common/MyAppBar.dart';
import 'package:credirama/common/myDrawer.dart';
import 'package:credirama/model/transactionObject.dart';
import 'package:credirama/charts/verticalGroupLabel.dart';
import 'package:credirama/widget/transaction.dart';
import 'package:credirama/widget/dateSearch.dart';
import 'package:flutter/rendering.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class MySimulation extends StatefulWidget {
  @override
  _MysimulationState createState() => _MysimulationState();
}

class _MysimulationState extends State<MySimulation> {

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
            DateSearchWidget().dateSearch(context),
            Table(
              defaultVerticalAlignment: TableCellVerticalAlignment.middle,
              defaultColumnWidth: IntrinsicColumnWidth(),
              columnWidths: {0: FractionColumnWidth(.2)},
              children: [
                TableRow(
                  decoration: BoxDecoration(
                    color: Colors.teal.shade100,
                  ),
                  children: [
                    Container(),
                    Text("WOOD", style: TextStyle(fontSize: 16.0),),
                    Text("STONE", style: TextStyle(fontSize: 16.0),),
                    Text("IRON", style: TextStyle(fontSize: 16.0),),
                    Text("DIAMOND", style: TextStyle(fontSize: 16.0),),
                ]
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
              height: 200.0,
              child: VerticalGroupBarLabelChart.feesPerDay(),
            ),
            Container(
              margin: EdgeInsets.symmetric(vertical: 10.0),
              height: 200.0,
              child: VerticalGroupBarLabelChart.avgPerDay(),
            ),
          ],
        ),
    );
  }
}