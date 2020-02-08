import 'dart:ffi';

import 'package:credirama/common/MyAppBar.dart';
import 'package:credirama/data/User.dart';
import 'package:credirama/model/transactionObject.dart';
import 'package:credirama/services/restService.dart';
import 'package:credirama/widget/transaction.dart';
import 'package:credirama/common/myDrawer.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:flutter/widgets.dart';

class MyAccount extends StatefulWidget {
  @override
  _MyAccountState createState() => _MyAccountState();
}

class _MyAccountState extends State<MyAccount> {
  List<TransactionObject> transactions = [];
  bool transactionsLoaded = false;

  @override
  void initState() {
    super.initState();
    RestService restService = RestService();
    restService.getAllTransactions().then((value) =>
        this.setState(() {
          transactions = value;
          transactionsLoaded = true;
          if(this.transactions.length == 0) {
            print("on a pas trouver de transaction du coup tien en voila une par default");
            TransactionObject transactionObject1 = new TransactionObject(toId:"Trevello App", amount:r"+ $ 4,946.00", timeStamp:"28-04-16", transactionType:"credit");
            this.transactions.add(transactionObject1);
          }
        })

    );
  }
  
  @override
  Widget build(BuildContext context) {
    /*RestService restService = new RestService();
    //récupère la balance de l'account 1
    double balance;
    restService.getBalance(1).then((value) {
      balance = value;
    });*/
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
                      )
                  )
              ),
              transactionsLoaded ?
                displayTransactionList() :
                Column(
                  children: <Widget>[
                    CircularProgressIndicator()
                  ],

                )
            ],
          ),
        ));
  }

  displayTransactionList() {
    var transactionsWidget = transactions.map(
          (transaction) => TransactionWidget().transaction(transaction),
    );
    return Container(
      child: Column(
        children: <Widget>[]..addAll(transactionsWidget),
      ),
    );
  }
}
