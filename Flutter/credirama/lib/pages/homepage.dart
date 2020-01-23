import 'dart:io';

import 'package:credirama/main.dart';
import 'package:credirama/services/restService.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';

class HomePage extends StatelessWidget {
  RestService restService = new RestService();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Compagnie particulière"),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            RaisedButton(
              padding: EdgeInsets.all(0.0),
              onPressed: () {
                restService.getBalance();
              },
              textColor: Colors.white,
              child: Container(
                padding: EdgeInsets.all(5.0),
                decoration: BoxDecoration(
                  gradient: LinearGradient(
                    colors: <Color>[
                      Color(0xff033f63),
                      Color(0xff088571),
                    ],
                  ),
                ),
                child: Text('Voir mon compte', style: TextStyle(fontSize: 20)),
              ),
            ),
            RaisedButton(
              padding: EdgeInsets.all(0.0),
              onPressed: () {
                restService.fetchPost();
              },
              textColor: Colors.white,
              child: Container(
                padding: EdgeInsets.all(5.0),
                decoration: BoxDecoration(
                  gradient: LinearGradient(
                    colors: <Color>[
                      Color(0xff033f63),
                      Color(0xff088571),
                    ],
                  ),
                ),
                child: Text('Voir mes frais', style: TextStyle(fontSize: 20)),
              ),
            ),
            RaisedButton(
              padding: EdgeInsets.all(0.0),
              onPressed: () {
               restService.getPrettyDump();
              },
              textColor: Colors.white,
              child: Container(
                padding: EdgeInsets.all(5.0),
                decoration: BoxDecoration(
                  gradient: LinearGradient(
                    colors: <Color>[
                      Color(0xff033f63),
                      Color(0xff088571),
                    ],
                  ),
                ),
                child: Text('Pretty Dump', style: TextStyle(fontSize: 20)),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
