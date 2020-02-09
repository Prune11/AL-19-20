import 'package:credirama/common/MyAppBar.dart';
import 'package:credirama/common/myDrawer.dart';
import 'package:credirama/pages/homepage.dart';
import 'package:credirama/request/createAccountRequest.dart';
import 'package:credirama/services/restService.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:credirama/shared.dart';


class Parameter extends StatefulWidget {
  @override
  _ParameterState createState() => _ParameterState();
}

class _ParameterState extends State<Parameter> {
  List<String> _listName = [];
  TextEditingController _nameController = TextEditingController();
  TextEditingController _clientIdController = TextEditingController();
  TextEditingController _contractController = TextEditingController();
  String _name;
  String _clientId;
  String _contract;

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
          child: Column(
            mainAxisAlignment: MainAxisAlignment.spaceAround,
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
              Center(
                  child: Text("Creer un Client", style: TextStyle(fontSize: 20))
              ),
              Container(
                  child:
                  TextField(
                    textAlign: TextAlign.center,
                    textCapitalization: TextCapitalization.words,
                    controller: _nameController,
                    onChanged: (String newValue) {
                      setState(() {
                        _name = newValue;
                      });
                    },
                    decoration: InputDecoration.collapsed(
                      hintText: 'Nom du titulaire du compte',
                    ),
                  )
              ),
              FlatButton(
                onPressed: () {
                  RestService restService = new RestService();
                  restService.createClient(_name ?? "Michel").then((value) => print(value.toString()));
                },
                textColor: Colors.white,
                child: Container(
                  alignment: Alignment(0.0, 0.0),
                  width: 200.0,
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
                  child: Text('Creer client', style: TextStyle(fontSize: 20)),
                ),
              ),
              Center(
                child: Text("Creer un Compte", style: TextStyle(fontSize: 20))
              ),
              Container(
                  child:
                  TextField(
                    textAlign: TextAlign.center,
                    textCapitalization: TextCapitalization.words,
                    controller: _clientIdController,
                    keyboardType: TextInputType.numberWithOptions(signed: false, decimal: false),
                    onChanged: (String newValue) {
                      setState(() {
                        _clientId = newValue;
                      });
                    },
                    decoration: InputDecoration.collapsed(
                      hintText: 'Id client du titulaire du compte',
                    ),
                  )
              ),
              Container(
                  child:
                  TextField(
                    textAlign: TextAlign.center,
                    textCapitalization: TextCapitalization.words,
                    controller: _contractController,
                    onChanged: (String newValue) {
                      setState(() {
                        _contract = newValue;
                      });
                    },
                    decoration: InputDecoration.collapsed(
                      hintText: 'Contrat du compte',
                    ),
                  )
              ),
              FlatButton(
                onPressed: () {
                  RestService restService = new RestService();
                  CreateAccountRequest request = CreateAccountRequest(int.parse(_clientId), _contract);
                  restService.createAccount(request).then((value) => print(value.toString()));
                },
                textColor: Colors.white,
                child: Container(
                  alignment: Alignment(0.0, 0.0),
                  width: 200.0,
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
                  child: Text('Creer compte', style: TextStyle(fontSize: 20)),
                ),
              ),
            ],
          ),
        )
    );
  }
}
