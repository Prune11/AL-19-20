import 'package:credirama/common/MyAppBar.dart';
import 'package:credirama/common/myDrawer.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

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
        body: Container(
          child: ListView(
            children: <Widget>[Text("bjr c les stats")],
          ),
        ));
  }
}
