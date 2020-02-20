import 'dart:io';

import 'package:credirama/common/MyAppBar.dart';
import 'package:credirama/main.dart';
import 'package:credirama/pages/AccountMenu.dart';
import 'package:credirama/pages/analyticsMenu.dart';
import 'package:credirama/pages/accountTransactionList.dart';
import 'package:credirama/pages/parameters.dart';
import 'package:credirama/pages/prettyDumpPage.dart';
import 'package:credirama/pages/profileDetail.dart';
import 'package:credirama/pages/transactionForm.dart';
import 'package:credirama/request/feeBtw2DatesRequest.dart';
import 'package:credirama/request/feeRequest.dart';
import 'package:credirama/request/transactionsBtwTwoDatesRequest.dart';
import 'package:credirama/services/restService.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';

import 'analyticAccountSelection.dart';

class HomePage extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: MyAppBar(),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceAround,
          children: <Widget>[
            FlatButton(
              onPressed: () {
                Navigator.push(context,
                    MaterialPageRoute(builder: (context) => MyProfile()));
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
                child: Text('Voir mes comptes', style: TextStyle(fontSize: 20)),
              ),
            ),
            FlatButton(
              onPressed: () {
                Navigator.push(context,
                    MaterialPageRoute(builder: (context) => TransactionForm()));
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
                child: Text('Virement', style: TextStyle(fontSize: 20)),
              ),
            ),
            FlatButton(
              onPressed: () {
                Navigator.push(context,
                    MaterialPageRoute(builder: (context) => AnalyticAccountSelection()));
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
                child: Text('Analytics', style: TextStyle(fontSize: 20)),
              ),
            ),

            FlatButton(
              onPressed: () {
                Navigator.push(context,
                    MaterialPageRoute(builder: (context) => PrettyDumpPage()));
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
                child: Text('Pretty Dump', style: TextStyle(fontSize: 20)),
              ),
            ),
            FlatButton(
              onPressed: () {
                Navigator.push(context,
                    MaterialPageRoute(builder: (context) => Parameter())
                );
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
                child: Text('Parametres', style: TextStyle(fontSize: 20)),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
