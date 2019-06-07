import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:clevertap_flutter/clevertap_flutter.dart';

class HomeScreen extends StatefulWidget {


  @override
  _HomeScreenState createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  String _eventMessage = 'Unknown';


  @override
  initState(){
    super.initState();
    pushClevertapEvent();
  }

  Future<void> pushClevertapEvent() async {
    String eventSuccessMessage;
    // Platform messages may fail, so we use a try/catch PlatformException.
    try {
      eventSuccessMessage = await ClevertapFlutter.pushEvent('Hello');

    } on PlatformException {
      eventSuccessMessage = 'Failed to get platform version.';
    }

    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;

    setState(() {
      _eventMessage = eventSuccessMessage;

    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        color: Colors.amber,
        child: Text('This is $_eventMessage'),
      ),
    );
  }
}
