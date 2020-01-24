import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class MyAppBar extends AppBar {
  @override
  Widget build(BuildContext context) {
    return AppBar(
        title: Text("Compagnie particulière"),
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.vertical(
            bottom: Radius.circular(30),
          ),
        )
    );
  }
}
