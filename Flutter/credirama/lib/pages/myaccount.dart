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
  Future<List<TransactionObject>> transactions;

  @override
  void initState() {
    super.initState();
    RestService restService = RestService();
    transactions = restService.getAllTransactions();
    /*restService.getAllTransactions().then((value) =>
        this.setState(() {
          transactions = value;
          /*if(this.transactions.length == 0) {
            print("On n'a pas trouvé de transaction du coup tiens en voila une par défault");
            TransactionObject transactionObject1 = new TransactionObject(toId:"Trevello App", amount:r"+ $ 4,946.00", timeStamp:"28-04-16", transactionType:"credit");
            this.transactions.add(transactionObject1);
          }*/
        })

    );*/
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
              /*transactionsLoaded ?
                displayTransactionList() :
                Column(
                  children: <Widget>[
                    CircularProgressIndicator()
                  ],

                )*/
              FutureBuilder<List<TransactionObject>>(
                future: transactions,
                builder: (context, snapshot) {
                  TransactionObject transaction = new TransactionObject(toId:"Default Transaction", amount:r"+ $ 4,946.00", timeStamp:"28-04-16", transactionType:"credit");
                  if (snapshot.hasData) {
                    var transactionsWidget = snapshot.data.map(
                          (transaction) => TransactionWidget().transaction(transaction),
                    );
                    if(transactionsWidget.length == 0) {
                      return Container(
                        child: Column(
                          children: <Widget>[
                            TransactionWidget().transaction(transaction)
                          ],
                        ),
                      );
                    }
                    return Container(
                      child: Column(
                        children: <Widget>[]..addAll(transactionsWidget),
                      ),
                    );
                  } else if (snapshot.hasError) {
                    return Column(children: <Widget>[
                      Text("${snapshot.error}"),
                      Divider(),
                      Text("Tiens une transaction par default"),
                      Divider(),
                      TransactionWidget().transaction(transaction)
                    ]);
                  }
                  return Center(child: CircularProgressIndicator());
                },
              )
            ],
          ),
        )
    );
  }

  /*displayTransactionList() {
    var transactionsWidget = transactions.map(
          (transaction) => TransactionWidget().transaction(transaction),
    );
    return Container(
      child: Column(
        children: <Widget>[]..addAll(transactionsWidget),
      ),
    );
  }*/
}
