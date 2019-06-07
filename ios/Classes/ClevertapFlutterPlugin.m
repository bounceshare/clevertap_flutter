#import "ClevertapFlutterPlugin.h"
#import <clevertap_flutter/clevertap_flutter-Swift.h>

@implementation ClevertapFlutterPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftClevertapFlutterPlugin registerWithRegistrar:registrar];
}
@end
