import 'package:credirama/common/MyAppBar.dart';
import 'package:credirama/pages/simulation.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:credirama/pages/analytics.dart';

class MyAnalyticsMenu extends StatefulWidget {

  final int _accountId;

  MyAnalyticsMenu(this._accountId);

  @override
  _MyAnalyticsMenuState createState() => _MyAnalyticsMenuState(_accountId);
}

class _MyAnalyticsMenuState extends State<MyAnalyticsMenu> {

  int _accountId;

  _MyAnalyticsMenuState(this._accountId);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: MyAppBar(),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: <Widget>[
            FlatButton(
              onPressed: () {
                Navigator.push(context,
                    MaterialPageRoute(builder: (context) => MyAnalytics(_accountId)));
              },
              textColor: Colors.white,
              child: Container(
                alignment: Alignment(0.0, 0.0),
                width: 200.0,
                height: 50.0,
                padding: EdgeInsets.all(10.0),
                decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(25.0),
                  gradient: LinearGradient(
                    colors: <Color>[
                      Color(0xff0F707E),
                      Color(0xff23c7ad),
                    ],
                  ),
                ),
                child: Text('Stats', style: TextStyle(fontSize: 20)),
              ),
            ),
            FlatButton(
              onPressed: () {
                Navigator.push(context,
                    MaterialPageRoute(builder: (context) => MySimulation(_accountId)));
              },
              textColor: Colors.white,
              child: Container(
                alignment: Alignment(0.0, 0.0),
                width: 200.0,
                height: 50.0,
                padding: EdgeInsets.all(10.0),
                decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(25.0),
                  gradient: LinearGradient(
                    colors: <Color>[
                      Color(0xff0F707E),
                      Color(0xff23c7ad),
                    ],
                  ),
                ),
                child: Text('Simulation', style: TextStyle(fontSize: 20)),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
