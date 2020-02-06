import 'package:credirama/common/MyAppBar.dart';
import 'package:credirama/common/myDrawer.dart';
import 'package:credirama/pages/homepage.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:credirama/shared.dart';


class Parameter extends StatefulWidget {
  @override
  _ParameterState createState() => _ParameterState();
}

class _ParameterState extends State<Parameter> {
  List<String> _listName = [];

  @override
  void initState() {
    super.initState();
    ipAddressMap.forEach((key, value) {
      _listName.add(key);
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: MyAppBar(),
        drawer: MyDrawer(),
        body: Container(
          child: ListView(
            children: <Widget>[
              DropdownButton<String>(
                hint: Text("Choisir Utilisateur"),
                icon: Icon(Icons.arrow_drop_down),
                iconSize: 24,
                elevation: 16,
                underline: Container(height: 2, color: Colors.blue),
                onChanged: (String newValue) {
                  currentIpAddress = newValue;
                  prefs.setString("ipAddress", newValue);
                  print(prefs.get("ipAddress"));
                  Navigator.push(context,
                      MaterialPageRoute(builder: (context) => HomePage())
                  );
                },
                items: _listName.map<DropdownMenuItem<String>>((String value) {
                  return DropdownMenuItem<String>(
                    value: ipAddressMap[value],
                    child: Text(value),
                  );
                }).toList(),
              ),
            ],
          ),
        ));
  }
}
