import 'dart:ffi';

import 'package:credirama/common/MyAppBar.dart';
import 'package:credirama/data/User.dart';
import 'package:credirama/model/transactionObject.dart';
import 'package:credirama/widget/transaction.dart';
import 'package:credirama/pages/analytics.dart';
import 'package:credirama/common/myDrawer.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:credirama/widget/transaction.dart';

class MyAccount extends StatefulWidget {
  @override
  _MyAccountState createState() => _MyAccountState();
}

class _MyAccountState extends State<MyAccount> {
  TransactionWidget transactionWidget = new TransactionWidget();
  TransactionObject transactionObject1 = new TransactionObject(
      "Trevello App", r"+ $ 4,946.00", "28-04-16", "credit");

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: MyAppBar(),
        drawer: MyDrawer(),
        body: Container(
          child: ListView(
            children: <Widget>[
              Card(
                  child: Container(
                      color: Colors.teal,
                      padding: EdgeInsets.all(5.0),
                      child: Column(
                        children: <Widget>[
                          Column(
                            mainAxisAlignment: MainAxisAlignment.spaceBetween,
                            children: <Widget>[
                              Text("Solde",
                                  style: TextStyle(
                                      color: Colors.white, fontSize: 20.0)),
                            ],
                          ),
                          Center(
                            child: Padding(
                              padding: EdgeInsets.all(5.0),
                              child: Text(r"DKK " + User.balance.toString(),
                                  style: TextStyle(
                                      color: Colors.white, fontSize: 24.0)),
                            ),
                          )
                        ],
                      ))),
              displayAccountList()
            ],
          ),
        ));
  }

  displayAccountList() {
    return Container(
      child: Column(
        children: <Widget>[
          transactionWidget.transaction(transactionObject1),
          transactionWidget.transaction(transactionObject1)
        ],
      ),
    );
  }
}
