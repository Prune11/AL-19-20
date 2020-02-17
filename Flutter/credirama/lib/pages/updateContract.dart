import 'package:credirama/common/MyAppBar.dart';
import 'package:credirama/common/myDrawer.dart';
import 'package:credirama/pages/homepage.dart';
import 'package:credirama/services/restService.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';


class ContractUpdate extends StatefulWidget {

  final int _accountId;
  final String _currentContract;

  ContractUpdate(this._accountId, this._currentContract);

  @override
  _ContractUpdateState createState() => _ContractUpdateState(_accountId, _currentContract);
}

class _ContractUpdateState extends State<ContractUpdate> {

  List<String> _listContract = ["WOOD", "STONE", "IRON", "DIAMOND"];
  int _accountId;
  String _currentContract;
  String _newContract;

  _ContractUpdateState(this._accountId, this._currentContract);

  @override
  void initState() {
    super.initState();
    _listContract.remove(this._currentContract);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: MyAppBar(),
        drawer: MyDrawer(),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: <Widget>[
              DropdownButton<String>(
                hint: Text("Nouveau contract"),
                value: _newContract,
                icon: Icon(Icons.arrow_drop_down),
                iconSize: 24,
                elevation: 16,
                underline: Container(height: 2, color: Color(0xff0F707E)),
                onChanged: (String newValue) {
                  this.setState(() {
                    _newContract = newValue;
                  });
                },
                items: _listContract.map<DropdownMenuItem<String>>((String value) {
                  return DropdownMenuItem<String>(
                    value: value,
                    child: Text(value),
                  );
                }).toList(),
              ),
              FlatButton(
                onPressed: () {
                  if(_newContract != null) {
                    RestService restService = new RestService();
                    restService.updateContract(_accountId, _newContract);
                    Navigator.push(context,
                        MaterialPageRoute(builder: (context) => HomePage())
                    );
                  }

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
                  child: Text('Changer Contract', style: TextStyle(fontSize: 20)),
                ),
              ),
            ],
          ),
        )
    );
  }
}
