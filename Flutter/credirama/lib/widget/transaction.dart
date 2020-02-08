import 'package:credirama/model/transactionObject.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class TransactionWidget {
  Container transaction(TransactionObject transactionObject) => Container(
      decoration: BoxDecoration(color: Colors.white),
      padding: EdgeInsets.only(top: 20.0, bottom: 20.0, left: 5.0, right: 5.0),
      child: Column(
        children: <Widget>[
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: <Widget>[
              Text(transactionObject.toId, style: TextStyle(fontSize: 16.0)),
              Text(transactionObject.amount, style: TextStyle(fontSize: 16.0))
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
