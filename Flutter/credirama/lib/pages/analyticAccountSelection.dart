import 'package:credirama/common/MyAppBar.dart';
import 'package:credirama/pages/analyticsMenu.dart';
import 'package:credirama/services/restService.dart';
import 'package:credirama/model/clientObject.dart';
import 'package:credirama/model/accountObject.dart';
import 'package:credirama/widget/account.dart';
import 'package:credirama/common/myDrawer.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

import 'AccountMenu.dart';

class AnalyticAccountSelection extends StatefulWidget {

  @override
  _AnalyticAccountSelectionState createState() => _AnalyticAccountSelectionState();
}

class _AnalyticAccountSelectionState extends State<AnalyticAccountSelection> {
  Future<ClientObject> client;

  @override
  void initState() {
    super.initState();
    RestService restService = RestService();
    client = restService.getClient(1);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: MyAppBar(),
        drawer: MyDrawer(),
        body: Container(
          child: FutureBuilder<ClientObject>(
            future: client,
            builder: (context, snapshot) {
              if (snapshot.hasData) {
                return ListView(
                  children: <Widget>[
                    Card(
                        child: Container(
                            color: Colors.teal,
                            padding: EdgeInsets.all(5.0),
                            child: Column(
                              children: <Widget>[
                                Center(
                                  child: Padding(
                                    padding: EdgeInsets.all(5.0),
                                    child: Text(snapshot.data.name,
                                        style: TextStyle(
                                            color: Colors.white,
                                            fontSize: 24.0)),
                                  ),
                                )
                              ],
                            )
                        )
                    ),
                    displayAccountList(context, snapshot.data.accountList)
                  ],
                );
              } else if (snapshot.hasError) {
                return Column(children: <Widget>[
                    Text("${snapshot.error}"),
                  ]
                );
              }
              return Center(child: CircularProgressIndicator());
            },
          )
        )
    );
  }

  displayAccountList(BuildContext context, List<AccountObject> accounts) {
    int i = 1;
    var accountWidget = accounts.map(
          (account) => AccountWidget().account(account,i++ , MyAnalyticsMenu(account.id), context )
    );
    return Container(
      child: Column(
        children: <Widget>[]..addAll(accountWidget)
      ),
    );
  }
}
