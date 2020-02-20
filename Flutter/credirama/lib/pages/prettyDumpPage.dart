import 'package:credirama/common/MyAppBar.dart';
import 'package:credirama/pages/accountTransactionList.dart';
import 'package:credirama/pages/updateContract.dart';
import 'package:credirama/services/restService.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class PrettyDumpPage extends StatefulWidget {

  @override
  _PrettyDumpPageState createState() => _PrettyDumpPageState();

}

class _PrettyDumpPageState extends State<PrettyDumpPage> {

  Future<String> prettyDump;

  @override
  void initState() {
    super.initState();
    RestService restService = RestService();
    prettyDump = restService.getPrettyDump();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: MyAppBar(),
      body: Center(
        child: ListView(
          children: <Widget>[
            FutureBuilder<String>(
              future: prettyDump,
              builder: (context, snapshot) {
                if (snapshot.hasData) {
                  return Center( child: Text(snapshot.data),);
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
      ),
    );
  }
}
