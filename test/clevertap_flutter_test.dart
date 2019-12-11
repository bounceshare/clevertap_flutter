import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:clevertap_flutter/clevertap_flutter.dart';

void main() {
  const MethodChannel channel = MethodChannel('clevertap_flutter');

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async => '42');
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await ClevertapFlutter.platformVersion, '42');
  });
}
