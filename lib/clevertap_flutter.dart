import 'dart:async';

import 'package:flutter/services.dart';

class ClevertapFlutter {
  static const MethodChannel _channel =
      const MethodChannel('clevertap_flutter');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<String> pushProfile(String name, String email) async {
    Map<String, dynamic> arguments = { 'profileName': name, 'profileEmail': email };

    final String result = await _channel.invokeMethod('pushProfile', arguments);
    return result;
  }

  static Future<String> pushEvent() async {
    final String result = await _channel.invokeMethod('pushEvent');
    print('pushEvent Flutter $result');
    return result;
  }


}
