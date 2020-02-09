import 'dart:ffi';
import 'dart:io';

import 'package:credirama/common/MyAppBar.dart';
import 'package:credirama/data/User.dart';
import 'package:credirama/model/accountObject.dart';
import 'package:credirama/model/clientObject.dart';
import 'package:credirama/widget/account.dart';
import 'package:credirama/widget/transaction.dart';
import 'package:credirama/common/myDrawer.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

class MyProfile extends StatefulWidget {
  @override
  _MyProfileState createState() => _MyProfileState();
}

class _MyProfileState extends State<MyProfile> {
  static AccountObject mockAccount = new AccountObject(id: 1, ownerId: 1, contract: "Wood", transactions: [1, 2], balance: 2.3);
  //Mockup client
  ClientObject clientObject = new ClientObject(id: 1, name: "Michel", accountList: [mockAccount]);

  //TODO: Get Client
  //TODO: Get Contract
  AccountWidget accountWidget = new AccountWidget();
  TransactionWidget transactionWidget = new TransactionWidget();
  //TransactionObject transactionObject1 = new TransactionObject(
    //  "Trevello App", r"+ $ 4,946.00", "28-04-16", "credit");

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
                          Center(
                            child: Padding(
                              padding: EdgeInsets.all(5.0),
                              child: Text(this.clientObject.name,
                                  style: TextStyle(
                                      color: Colors.white, fontSize: 24.0)),
                            ),
                          )
                        ],
                      ))),
              displayAccountList(context)
            ],
          ),
        ));
  }

  displayAccountList(BuildContext context) {
    return Container(
      child: Column(
        children: <Widget>[
          accountWidget.account(mockAccount, 1, context),
        ],
      ),
    );
  }
}
