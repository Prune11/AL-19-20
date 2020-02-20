import 'package:credirama/common/MyAppBar.dart';
import 'package:credirama/model/accountObject.dart';
import 'package:credirama/pages/accountTransactionList.dart';
import 'package:credirama/pages/updateContract.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class AccountMenu extends StatefulWidget {

  final AccountObject _account;
  final String _accountName;

  AccountMenu(this._account, this._accountName);

  @override
  _AccountMenuState createState() => _AccountMenuState(_account, _accountName);

}

class _AccountMenuState extends State<AccountMenu> {

  AccountObject _account;
  String _accountName;

  _AccountMenuState(this._account, this._accountName);

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
                            child: Text(r"DKK " + _account.balance.toString(),
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
                    MaterialPageRoute(builder: (context) => AccountTransactionList("All", _accountName, _account.balance)));
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
                    MaterialPageRoute(builder: (context) => AccountTransactionList("IN", _accountName, _account.balance)));
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
                    MaterialPageRoute(builder: (context) => AccountTransactionList("OUT", _accountName, _account.balance)));
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
                    MaterialPageRoute(builder: (context) => ContractUpdate(_account.id, _account.contract)));
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
