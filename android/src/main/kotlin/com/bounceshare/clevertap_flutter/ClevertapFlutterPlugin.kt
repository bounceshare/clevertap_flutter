package com.bounceshare.clevertap_flutter

import android.app.Activity
import android.util.Log
import com.clevertap.android.sdk.CleverTapAPI
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar
import java.util.HashMap

class ClevertapFlutterPlugin: MethodCallHandler {
  companion object {
    @JvmStatic
    fun registerWith(registrar: Registrar) {
      val channel = MethodChannel(registrar.messenger(), "clevertap_flutter")
      channel.setMethodCallHandler(ClevertapFlutterPlugin())
    }
  }

  override fun onMethodCall(call: MethodCall, result: Result) {
    val clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(Activity())

    if (call.method == "getPlatformVersion") {
      result.success("Android ${android.os.Build.VERSION.RELEASE}")
    }
    else if(call.method == "pushProfile"){

      val profileUpdate = HashMap<String, Any>()

      val name : String = call.argument("profileName")?:"TEST"
      val email : String = call.argument("profileEmail")?:"email@gmail.com"

      profileUpdate["Name"] = name
      profileUpdate["Email"] = email

      clevertapDefaultInstance!!.pushProfile(profileUpdate)
      clevertapDefaultInstance.pushEvent("Test event from pushProfile")
      result.success("Pushed $name $email" )
    }
    else if (call.method == "pushEvent"){

      clevertapDefaultInstance!!.pushEvent("Test event from pushEvent")
      result.success("Pushed")
    }
    else {
      result.notImplemented()
    }
  }




}
