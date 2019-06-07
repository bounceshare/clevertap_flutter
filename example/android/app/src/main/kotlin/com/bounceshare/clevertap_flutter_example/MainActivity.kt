package com.bounceshare.clevertap_flutter_example

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.clevertap.android.sdk.ActivityLifecycleCallback
import com.clevertap.android.sdk.CleverTapAPI

import io.flutter.app.FlutterActivity
import io.flutter.app.FlutterApplication
import io.flutter.plugins.GeneratedPluginRegistrant
import java.util.HashMap

class MainActivity: FlutterActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    GeneratedPluginRegistrant.registerWith(this)
  }
}
