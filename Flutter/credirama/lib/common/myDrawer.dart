import 'package:credirama/data/User.dart';
import 'package:credirama/pages/homepage.dart';
import 'package:credirama/pages/parameters.dart';
import 'package:credirama/pages/transactionForm.dart';
import 'package:credirama/pages/profileDetail.dart';
import 'package:credirama/pages/analyticsMenu.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class MyDrawer extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Drawer(
        child: ListView(
          padding: EdgeInsets.zero,
          children: <Widget>[
            MaterialButton(
              color: Colors.teal,
              onPressed: () {
                Navigator.push(context,
                MaterialPageRoute(builder: (context) => MyProfile()));
              },
              child: UserAccountsDrawerHeader(
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
            ),

            ListTile(
              title: Text('Accueil'),
              onTap: () {
                Navigator.push(context,
                    MaterialPageRoute(builder: (context) => HomePage()));
              },
            ),
            ListTile(
              title: Text('Mes comptes'),
              onTap: () {
                Navigator.push(
                    context, MaterialPageRoute(builder: (context) => MyProfile()));
              },
            ),
            ListTile(
              title: Text('Virement'),
              onTap: () {
                Navigator.push(context,
                    MaterialPageRoute(builder: (context) => TransactionForm()));
              },
            ),
            ListTile(
              title: Text('Statistiques'),
              onTap: () {
                Navigator.push(context,
                    MaterialPageRoute(builder: (context) => MyAnalyticsMenu()));
              },
            ),
            ListTile(
              title: Text('Parametres'),
              onTap: () {
                Navigator.push(context,
                    MaterialPageRoute(builder: (context) => Parameter()));
              },
            ),
      ],
    ));
  }
}
