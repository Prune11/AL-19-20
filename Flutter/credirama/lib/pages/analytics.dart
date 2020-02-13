import 'package:credirama/common/MyAppBar.dart';
import 'package:credirama/common/myDrawer.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:credirama/charts/verticalLabel.dart';

class MyAnalytics extends StatefulWidget {
  @override
  _MyAnalyticsState createState() => _MyAnalyticsState();
}

class _MyAnalyticsState extends State<MyAnalytics> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: MyAppBar(),
        drawer: MyDrawer(),
        body: Center(
          child: Container(
              padding: EdgeInsets.symmetric(),
              child: ListView(
                children: <Widget>[
                  Container(
                    alignment: Alignment.center,
                    child: Text("Fees per month", style: TextStyle(fontSize: 20)),
                  ),
                  Container(
                    height: 200.0,
                    child: VerticalBarLabelChart.withSampleData(),
                  )
                ],
              ),
          ),
        ),
    );
  }
}

/*Container(
    height: 200.0,
    child: EndPointsAxisTimeSeriesChart.withSampleData(),
  ),
  Container(
      height: 200.0,
      child: StackedAreaLineChart.withSampleData(),
  ),*/