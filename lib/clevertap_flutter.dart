import 'dart:async';
import 'dart:collection';

import 'package:flutter/services.dart';

class ClevertapFlutter {
  static const MethodChannel _channel =
      const MethodChannel('clevertap_flutter');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<String> pushProfile(String name, String email) async {
    Map<String, dynamic> arguments = { 'Name': name, 'Email': email };

    final String result = await _channel.invokeMethod('pushProfile', arguments);
    return result;
  }

  static Future<bool> pushEvent(String eventName, [Map params]) async {
    Map<String, dynamic> arguments = { "eventName": eventName, "values" : params};

    bool result;
    result = await _channel.invokeMethod('pushEvent', arguments);

    print('pushEventParams Flutter $arguments');
    return result;
  }

  static Future<bool> onUserLogin(Map values) async {
    Map<String, dynamic> arguments = values;

    final bool result = await _channel.invokeMethod('onUserLogin', arguments);
    return result;
  }


}
