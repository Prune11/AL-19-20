import 'package:credirama/common/MyAppBar.dart';
import 'package:credirama/pages/accountTransactionList.dart';
import 'package:credirama/pages/updateContract.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class AccountMenu extends StatefulWidget {

  final int _accountId;
  final String _accountName;
  final String _currentContract;
  final double _balance;


  AccountMenu(this._accountId, this._accountName, this._currentContract, this._balance);

  @override
  _AccountMenuState createState() => _AccountMenuState(_accountId, _accountName, _currentContract, _balance);

}

class _AccountMenuState extends State<AccountMenu> {

  int _accountId;
  String _accountName;
  String _currentContract;
  double _balance;

  _AccountMenuState(this._accountId, this._accountName, this._currentContract, this._balance);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: MyAppBar(),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
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
            FlatButton(
              onPressed: () {
                Navigator.push(context,
                    MaterialPageRoute(builder: (context) => AccountTransactionList("All", _accountName, _balance)));
              },
              textColor: Colors.white,
              child: Container(
                alignment: Alignment(0.0, 0.0),
                width: 240.0,
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
                child: Text('Afficher transactions', style: TextStyle(fontSize: 20)),
              ),
            ),
            FlatButton(
              onPressed: () {
                Navigator.push(context,
                    MaterialPageRoute(builder: (context) => AccountTransactionList("IN", _accountName, _balance)));
              },
              textColor: Colors.white,
              child: Container(
                alignment: Alignment(0.0, 0.0),
                width: 240.0,
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
                child: Text('Afficher recettes', style: TextStyle(fontSize: 20)),
              ),
            ),
            FlatButton(
              onPressed: () {
                Navigator.push(context,
                    MaterialPageRoute(builder: (context) => AccountTransactionList("OUT", _accountName, _balance)));
              },
              textColor: Colors.white,
              child: Container(
                alignment: Alignment(0.0, 0.0),
                width: 240.0,
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
                child: Text('Afficher dépenses', style: TextStyle(fontSize: 20)),
              ),
            ),
            FlatButton(
              onPressed: () {
                Navigator.push(context,
                    MaterialPageRoute(builder: (context) => ContractUpdate(_accountId, _currentContract)));
              },
              textColor: Colors.white,
              child: Container(
                alignment: Alignment(0.0, 0.0),
                width: 240.0,
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
                child: Text('Changer Contract', style: TextStyle(fontSize: 20)),
              ),
            ),
            Container(),
          ],
        ),
      ),
    );
  }
}
