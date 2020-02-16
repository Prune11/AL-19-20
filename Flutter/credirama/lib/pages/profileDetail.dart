import 'dart:ffi';
import 'dart:io';

import 'package:credirama/common/MyAppBar.dart';
import 'package:credirama/services/restService.dart';
import 'package:credirama/model/clientObject.dart';
import 'package:credirama/model/accountObject.dart';
import 'package:credirama/widget/account.dart';
import 'package:credirama/widget/transaction.dart';
import 'package:credirama/common/myDrawer.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:http/http.dart';

class MyProfile extends StatefulWidget {
  @override
  _MyProfileState createState() => _MyProfileState();
}

class _MyProfileState extends State<MyProfile> {
  Future<ClientObject> client;

  //TODO: Get Client
  //TODO: Get Contract
  AccountWidget accountWidget = new AccountWidget();
  TransactionWidget transactionWidget = new TransactionWidget();
  //TransactionObject transactionObject1 = new TransactionObject(
    //  "Trevello App", r"+ $ 4,946.00", "28-04-16", "credit");

  @override
  void initState() {
    super.initState();
    RestService restService = RestService();
    client = restService.getClient(1);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: MyAppBar(),
        drawer: MyDrawer(),
        body: Container(
          child: FutureBuilder<ClientObject>(
            future: client,
            builder: (context,snapshot) {
              return ListView(
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
                                  child: Text(snapshot.data.name,
                                      style: TextStyle(
                                          color: Colors.white, fontSize: 24.0)),
                                ),
                              )
                            ],
                          ))),
                  displayAccountList(context)
                ],
              );
            }
          )
        ));
  }

  displayAccountList(BuildContext context) {

    return Container(
      child: Column(
        children: <Widget>[
          FutureBuilder<ClientObject>(
            future: client,
            builder: (context, snapshot) {
              AccountObject accountObject = new AccountObject(id: 1, ownerId: 1, contract: "Wood", transactions: [1, 2], balance: 2.3);
              if (snapshot.hasData) {

                final accountsWidget = <Widget>[];
                for (var i = 0; i < snapshot.data.accountList.length; i++) {
                  accountsWidget.add(AccountWidget().account(snapshot.data.accountList[i], i+1, context));
                }
                if(accountsWidget.length == 0) {
                  return Container(
                    child: Column(
                      children: <Widget>[
                        AccountWidget().account(accountObject, 1, context),
                      ],
                    ),
                  );
                }
                return Container(
                  child: Column(
                    children: accountsWidget,
                  ),
                );
              } else if (snapshot.hasError) {
                return Column(children: <Widget>[
                  Text("${snapshot.error}"),
                  Divider(),
                  Text("Tiens une compte par default"),
                  Divider(),
                  AccountWidget().account(accountObject, 1, context),
                  ]
                );
              }
              return Center(child: CircularProgressIndicator());
            },
          )
        ],
      ),
    );
  }
}
