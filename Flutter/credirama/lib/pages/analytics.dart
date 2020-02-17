import 'dart:ui';

import 'package:credirama/common/MyAppBar.dart';
import 'package:credirama/common/myDrawer.dart';
import 'package:credirama/model/transactionObject.dart';
import 'package:credirama/charts/verticalLabel.dart';
import 'package:credirama/widget/transaction.dart';
import 'package:credirama/widget/dateSearch.dart';
import 'package:flutter/rendering.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class MyAnalytics extends StatefulWidget {
  @override
  _MyAnalyticsState createState() => _MyAnalyticsState();
}

class _MyAnalyticsState extends State<MyAnalytics> {

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
              child: VerticalBarLabelChart.feesPerDay(),
            ),
            Container(
              height: 200.0,
              child: VerticalBarLabelChart.avgPerDay(),
            ),
            Container(
              height: 200.0,
              child: VerticalBarLabelChart.nbTransactionsPerDay(),
            ),
          ],
        ),
    );
  }
}