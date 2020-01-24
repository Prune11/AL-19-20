import 'package:credirama/data/User.dart';
import 'package:credirama/pages/homepage.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import '../pages/analytics.dart';
import '../pages/myaccount.dart';

class MyDrawer extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Drawer(
        child: ListView(
      padding: EdgeInsets.zero,
      children: <Widget>[
        UserAccountsDrawerHeader(
          accountName: Text(User.name),
          currentAccountPicture: CircleAvatar(
            backgroundColor: Colors.white,
            child: Text(
              User.name.substring(0, 1),
              style: TextStyle(fontSize: 40.0),
            ),
          ),
          otherAccountsPictures: <Widget>[
            CircleAvatar(
              backgroundColor: Colors.white,
              child: Text(
                User.balance.toString(),
                style: TextStyle(
                    fontSize: 20.0,
                    color: (User.balance >= 10) ? Colors.teal : Colors.pink),
              ),
            )
          ],
        ),
        ListTile(
          title: Text('Mon compte'),
          onTap: () {
            Navigator.push(
                context, MaterialPageRoute(builder: (context) => MyAccount()));
          },
        ),
        ListTile(
          title: Text('Statistiques'),
          onTap: () {
            Navigator.push(context,
                MaterialPageRoute(builder: (context) => MyAnalytics()));
          },
        ),
        ListTile(
          title: Text('Accueil'),
          onTap: () {
            Navigator.push(context,
                MaterialPageRoute(builder: (context) => HomePage()));
          },
        )
      ],
    ));
  }
}
