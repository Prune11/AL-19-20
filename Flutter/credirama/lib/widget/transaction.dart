import 'package:credirama/model/transactionObject.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class TransactionWidget {
  Container transaction(TransactionObject transactionObject, int accountId) => Container(
      decoration: BoxDecoration(color: Colors.white),
      padding: EdgeInsets.only(top: 20.0, bottom: 20.0, left: 5.0, right: 5.0),
      child: Column(
        children: <Widget>[
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: <Widget>[
              Text("Account " + transactionObject.toId, style: TextStyle(fontSize: 16.0)),
              Row(
                children: <Widget>[
                  Icon((accountId == int.parse(transactionObject.toId))? Icons.trending_up : Icons.trending_down, size: 30,
                            color: (accountId == int.parse(transactionObject.toId))? Colors.green: Colors.red),
                  Text(transactionObject.amount,
                      style: TextStyle(fontSize: 16.0,
                        color: (accountId == int.parse(transactionObject.toId))? Colors.green: Colors.red)
                  )
                ]
              )
            ],
          ),
          SizedBox(
            height: 10.0,
          ),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: <Widget>[
              Text(transactionObject.timeStamp,
                  style: TextStyle(color: Colors.grey, fontSize: 14.0)),
              Text(transactionObject.transactionType,
                  style: TextStyle(color: Colors.grey, fontSize: 14.0))
            ],
          ),
        ],
      ));
}
