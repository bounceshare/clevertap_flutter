import 'dart:async';

import 'package:flutter/services.dart';

///
/// CleverTap Plugin for Flutter for app analytics and marketing.
///
class ClevertapFlutter {
  /// Channel for communicating with platform plugins using asynchronous
  /// message passing.
  static const MethodChannel _channel = MethodChannel('clevertap_flutter');

  ///
  /// Push User Events to CleverTap
  ///
  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  ///
  /// Push User Profile Data to CleverTap
  ///
  /// A user profile will be automatically created for every user
  /// whether logged in or not
  ///
  /// Feel free to add more events based on the Upload User Profiles API
  ///
  static Future<String> pushProfile(
      String name, String email, String identity) async {
    final Map<String, dynamic> arguments = {
      'Name': name,
      'Email': email,
      'Identity': identity
    };

    final String result = await _channel.invokeMethod('pushProfile', arguments);
    return result;
  }

  ///
  /// Push User Events to CleverTap
  ///
  /// Send associated key:value based event properties to CleverTap
  ///
  static Future<bool> pushEvent(String eventName, [Map params]) async {
    final Map<String, dynamic> arguments = {
      "eventName": eventName,
      "values": params
    };

    final bool result = await _channel.invokeMethod('pushEvent', arguments);
    return result;
  }

  ///
  /// Login Specific Event to CleverTap
  ///
  static Future<bool> onUserLogin(Map values) async {
    final Map<String, dynamic> arguments = values;

    final bool result = await _channel.invokeMethod('onUserLogin', arguments);
    return result;
  }
}
