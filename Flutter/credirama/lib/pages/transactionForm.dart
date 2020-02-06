import 'package:credirama/common/MyAppBar.dart';
import 'package:credirama/model/transactionRequest.dart';
import 'package:credirama/services/restService.dart';
import 'package:credirama/common/myDrawer.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

class TransactionForm extends StatefulWidget {
  @override
  _TransactionForm createState() => _TransactionForm();
}

class _TransactionForm extends State<TransactionForm> {

  TextEditingController _accountFromController = TextEditingController();
  TextEditingController _accountToController = TextEditingController();
  TextEditingController _amountController = TextEditingController();
  String _accountFrom;
  String _accountTo;
  String _amount;


  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: MyAppBar(),
        drawer: MyDrawer(),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.spaceAround,
            children: <Widget>[
              Column(
                children: <Widget>[
                  Text("Ici on prend de la thune", style: TextStyle(fontSize: 20)),
                  Container(
                    child:
                      TextField(
                        textAlign: TextAlign.center,
                        textCapitalization: TextCapitalization.words,
                        controller: _accountFromController,
                        keyboardType: TextInputType.numberWithOptions(signed: false, decimal: false),
                        onChanged: (String newValue) {
                          setState(() {
                            _accountFrom = newValue;
                          });
                        },
                        decoration: InputDecoration.collapsed(
                          hintText: 'Id du compte créditeur',
                        ),
                      )
                  )
                ]
              ),
              Column(
                children: <Widget>[
                  Text("Et on la met là", style: TextStyle(fontSize: 20)),
                  TextField(
                    textAlign: TextAlign.center,
                    textCapitalization: TextCapitalization.words,
                    controller: _accountToController,
                    keyboardType: TextInputType.numberWithOptions(signed: false, decimal: false),
                    onChanged: (String newValue) {
                      setState(() {
                        _accountTo = newValue;
                      });
                    },
                    decoration: InputDecoration.collapsed(
                      hintText: 'Id du compte débiteur',
                    ),
                  ),
                ]
              ),
              Column(
                children: <Widget>[
                  Text("Somme", style: TextStyle(fontSize: 20)),
                  TextField(
                    textAlign: TextAlign.center,
                    textCapitalization: TextCapitalization.words,
                    controller: _amountController,
                    keyboardType: TextInputType.numberWithOptions(decimal: true, signed: false),
                    onChanged: (String newValue) {
                      setState(() {
                        _amount = newValue;
                      });
                    },
                    decoration: InputDecoration.collapsed(
                      hintText: 'Indiquer somme',
                    ),
                  ),
                ]
              ),
              FlatButton(
                onPressed: () {
                  TransactionRequest transactionRequest = new TransactionRequest(int.parse(_accountFrom), int.parse(_accountTo), double.parse(_amount));
                  print(transactionRequest.toString());
                  RestService restService = new RestService();
                  restService.postTransaction(transactionRequest);
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
                  child: Text('Valider', style: TextStyle(fontSize: 20)),
                ),
              ),
            ]
          )
        ));
  }


}
