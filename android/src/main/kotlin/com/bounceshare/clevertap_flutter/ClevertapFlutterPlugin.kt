package com.bounceshare.clevertap_flutter

import android.app.Activity
import android.os.Build
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

  private val deviceDetailsMap = HashMap<String, Any>()

  override fun onMethodCall(call: MethodCall, result: Result) {
    val ct = CleverTapAPI.getDefaultInstance(Activity())
    when(call.method){
      "getPlatformVersion" -> result.success("Android ${android.os.Build.VERSION.RELEASE}")

      "pushProfile" -> {
        initializeDeviceDetailsMap()

        val profileUpdate = call.arguments as HashMap<String, Any>
        ct!!.pushProfile(profileUpdate)

        Log.d("CleverTapEvent", "pushProfile: $profileUpdate")

        result.success("Profile Pushed ${call.arguments}" )
      }

      "pushEvent" -> {

        val eventName : String = call.argument("eventName")?:"Null Event"
        val params : HashMap<String, Any>? = call.argument("values") as HashMap<String, Any>?

        Log.d("flutter pushEvent", eventName + addDeviceDetails(params).entries.toString())

        ct!!.pushEvent(eventName, addDeviceDetails(params))
        result.success(true)
      }

      "onUserLogin" -> {
        initializeDeviceDetailsMap()

        val values = call.arguments as HashMap<String, Any>
        ct!!.onUserLogin(values)

        Log.d("CleverTapEvent", "onUserLogin: $values")
        result.success(true)
      }

      else -> result.notImplemented()
    }
  }

  private fun addDeviceDetails(values: HashMap<String, Any>? = null): HashMap<String, Any> {
    deviceDetailsMap.clear()
    initializeDeviceDetailsMap()

    values?.let {
      deviceDetailsMap.putAll(it)
    }

    Log.d("pushEvent DEVICE ", deviceDetailsMap.entries.toString())
    return deviceDetailsMap
  }


  private fun initializeDeviceDetailsMap() {
    deviceDetailsMap.clear()
    deviceDetailsMap.put("OS Version", Build.VERSION.RELEASE)
//    deviceDetailsMap.put("App Version", BuildConfig.VERSION_NAME)
    deviceDetailsMap.put("Device Model", Build.MODEL)
    deviceDetailsMap.put("Manufacturer", Build.MANUFACTURER)
    deviceDetailsMap.put("Device Brand", Build.BRAND)
//    val telephonyManager = Activity().getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
//    if(checkReadPhoneStatePermission())
//      deviceDetailsMap.put("Device IMEI", telephonyManager.deviceId)

    Log.d("CleverTapEvent device", deviceDetailsMap.entries.toString())
  }

//  private fun checkReadPhoneStatePermission(): Boolean {
//    val permission = android.Manifest.permission.READ_PHONE_STATE
//    return (Activity().checkCallingOrSelfPermission(permission) == PackageManager.PERMISSION_GRANTED)
//  }




}
