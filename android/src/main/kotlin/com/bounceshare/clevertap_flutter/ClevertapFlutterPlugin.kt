package com.bounceshare.clevertap_flutter

import android.app.Activity
import android.content.Context
import android.os.Build
import android.telephony.TelephonyManager
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

  val deviceDetailsMap = HashMap<String, Any>()

  override fun onMethodCall(call: MethodCall, result: Result) {
    val clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(Activity())

    when(call.method){
      "getPlatformVersion" -> result.success("Android ${android.os.Build.VERSION.RELEASE}")

      "pushProfile" -> {

        initializeDeviceDetailsMap()

        val profileUpdate = HashMap<String, Any>()

        val name : String = call.argument("profileName")?:"TEST"
        val email : String = call.argument("profileEmail")?:"email@gmail.com"

        profileUpdate["Name"] = name
        profileUpdate["Email"] = email

        clevertapDefaultInstance!!.pushProfile(profileUpdate)
        clevertapDefaultInstance.pushEvent("Test event from pushProfile")

        Log.d("flutter method", "push Event $name $email")

        result.success("Pushed $name $email" )
      }


      "pushEvent" -> {
        clevertapDefaultInstance!!.pushEvent("Test event from pushEvent")
        result.success("Pushed")
      }



      else -> result.notImplemented()
    }
  }

  private fun initializeDeviceDetailsMap() {
    deviceDetailsMap.put("OS Version", Build.VERSION.RELEASE)
    deviceDetailsMap.put("App Version", BuildConfig.VERSION_NAME)
    deviceDetailsMap.put("Device Model", Build.MODEL)
    deviceDetailsMap.put("Manufacturer", Build.MANUFACTURER)
    deviceDetailsMap.put("Device Brand", Build.BRAND)
//    val telephonyManager = context.applicationContext.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
//    if(checkReadPhoneStatePermission())
//      deviceDetailsMap.put("Device IMEI", telephonyManager.deviceId)

    Log.d("flutter device Details", deviceDetailsMap.entries.toString())
  }




}
