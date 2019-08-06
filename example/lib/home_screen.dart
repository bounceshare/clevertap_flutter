import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:clevertap_flutter/clevertap_flutter.dart';

class HomeScreen extends StatefulWidget {


  @override
  _HomeScreenState createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  String _pushProfileMessage = 'Unknown';
  bool _pushEventMessage;



  @override
  initState(){
    super.initState();
    pushClevertapEvent();
  }

  Future<void> pushClevertapEvent() async {
    bool eventSuccessMessage;
    String profileSuccessMessage;
    // Platform messages may fail, so we use a try/catch PlatformException.
    try {
      profileSuccessMessage = await ClevertapFlutter.pushProfile('PB', 'testpb@gmail.com');
      eventSuccessMessage = await ClevertapFlutter.pushEvent('Sign Up Click 2');

    } on PlatformException {
      profileSuccessMessage = 'Failed to push Profile.';
      eventSuccessMessage = false;
    }

    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;

    setState(() {
      _pushProfileMessage = profileSuccessMessage;
      _pushEventMessage = eventSuccessMessage;

    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        color: Colors.amber,
        child: Center(child: Text('This is $_pushProfileMessage $_pushEventMessage')),
      ),
    );
  }
}
