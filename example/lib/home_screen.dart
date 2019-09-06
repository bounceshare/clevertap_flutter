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
  bool _onUserLoginMessage;
  bool _pushParamsEventMessage, _pushNoParamsEventMessage;

  Map<String, dynamic> paramsMap = {
    "version": 2.0,
    "name": "TestName",
  };

  Map<String, dynamic> loginMap = {
    "onUserLoginParam": 100,
  };

  @override
  initState() {
    super.initState();
    pushClevertapEvent();
  }

  Future<void> pushClevertapEvent() async {
    bool pushEventParamsResult, pushEventResult, onUserLoginResult;
    String pushProfileResult;
    // Platform messages may fail, so we use a try/catch PlatformException.
    try {
      pushProfileResult =
          await ClevertapFlutter.pushProfile('TestName', 'test@gmail.com');
      onUserLoginResult = await ClevertapFlutter.onUserLogin(loginMap);

      pushEventParamsResult =
          await ClevertapFlutter.pushEvent('Params Event', paramsMap);
      pushEventResult =
          await ClevertapFlutter.pushEvent('No Params Test Event');
    } on PlatformException {
      pushProfileResult = 'Failed to push Profile.';
      onUserLoginResult = false;
      pushEventParamsResult = false;
      pushEventResult = false;
    }

    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;

    setState(() {
      _pushProfileMessage = pushProfileResult;
      _onUserLoginMessage = onUserLoginResult;
      _pushParamsEventMessage = pushEventParamsResult;
      _pushNoParamsEventMessage = pushEventResult;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        child: Center(
            child: Text(
                '$_pushProfileMessage $_onUserLoginMessage pushEvent1 $_pushParamsEventMessage pushEvent2 $_pushNoParamsEventMessage')),
      ),
    );
  }
}
