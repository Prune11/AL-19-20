import 'package:credirama/model/accountObject.dart';
import 'package:credirama/pages/myaccount.dart';
import 'package:credirama/model/clientObject.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import '../model/accountObject.dart';

class AccountWidget {
  Container account(AccountObject accountObject, int numAccount, BuildContext context) => Container(
      decoration: BoxDecoration(color: Colors.white),
      padding: EdgeInsets.only(top: 20.0, bottom: 20.0, left: 5.0, right: 5.0),
      child: MaterialButton(
        onPressed: () {
          Navigator.push( context,
              MaterialPageRoute(builder: (context) => MyAccount()));
        } ,
        child: Column(
          children: <Widget>[
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: <Widget>[
                Text("Account " + numAccount.toString() + "", style: TextStyle(fontSize: 16.0)),
                //ATTENTION: € change in order to accept more types of money?
                Text(accountObject.balance.toString() + " €", style: TextStyle(fontSize: 16.0))
              ],
            ),
            SizedBox(
              height: 10.0,
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: <Widget>[
                Text("Contrat",
                    style: TextStyle(color: Colors.grey, fontSize: 14.0)),
              ],
            ),
          ],
        ),
      ),
      );
}