import 'package:credirama/common/MyAppBar.dart';
import 'package:credirama/model/transactionObject.dart';
import 'package:credirama/services/restService.dart';
import 'package:credirama/widget/transaction.dart';
import 'package:credirama/common/myDrawer.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:flutter/widgets.dart';

class AccountTransactionList extends StatefulWidget {

  final String _filter;
  final String _accountName;
  final double _balance;

  AccountTransactionList(this._filter, this._accountName, this._balance);

  @override
  _AccountTransactionListState createState() => _AccountTransactionListState(_filter, _accountName, _balance);

}

class _AccountTransactionListState extends State<AccountTransactionList> {
  Future<List<TransactionObject>> transactions;
  String _filter;
  String _accountName;
  double _balance;

  _AccountTransactionListState(this._filter, this._accountName, this._balance);

  @override
  void initState() {
    super.initState();
    RestService restService = RestService();
    if(_filter == "IN") {
      transactions = restService.getAllTransactionsToUser(1);
    } else if (_filter == "OUT") {
      transactions = restService.getAllTransactionsFromUser(1);
    } else {
      transactions = restService.getAllTransactions();
    }
  }
  
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
                              Text(_accountName,
                                  style: TextStyle(
                                      color: Colors.white, fontSize: 20.0)),
                            ],
                          ),
                          Center(
                            child: Padding(
                              padding: EdgeInsets.all(5.0),
                              child: Text(r"DKK " + _balance.toString(),
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
