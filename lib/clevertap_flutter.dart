import 'dart:async';

import 'package:flutter/services.dart';

class ClevertapFlutter {
  static const MethodChannel _channel =
      const MethodChannel('clevertap_flutter');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<String> pushEvent(String text) async {
    final String version = await _channel.invokeMethod('pushProfile', <String, dynamic>{
      'str': text,
    });
    return version;
  }
}
