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
              FutureBuilder<List<TransactionObject>>(
                future: transactions,
                builder: (context, snapshot) {
                  if (snapshot.hasData) {
                    var transactionsWidget = snapshot.data.map(
                          (transaction) => TransactionWidget().transaction(transaction),
                    );
                    return Container(
                      child: Column(
                        children: <Widget>[]..addAll(transactionsWidget),
                      ),
                    );
                  } else if (snapshot.hasError) {
                    return Column(children: <Widget>[
                      Text("${snapshot.error}"),
                      Divider(),
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

}
