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

  val deviceDetailsMap = HashMap<String, Any>()

  override fun onMethodCall(call: MethodCall, result: Result) {
    val ct = CleverTapAPI.getDefaultInstance(Activity())

    when(call.method){
      "getPlatformVersion" -> result.success("Android ${android.os.Build.VERSION.RELEASE}")

      "pushProfile" -> {
        initializeDeviceDetailsMap()

        val profileUpdate = HashMap<String, Any>()

        val name : String = call.argument("profileName")?:"TEST"
        val email : String = call.argument("profileEmail")?:"email@gmail.com"

        profileUpdate["Name"] = name
        profileUpdate["Email"] = email

        ct!!.pushProfile(profileUpdate)
        ct.pushEvent("Test event from pushProfile")

        Log.d("flutter method", "push Event $name $email")

        result.success("Pushed $name $email" )
      }

      "pushEvent" -> {
        Log.d("flutter pushEvent", deviceDetailsMap.entries.toString())

        val eventName : String = call.argument("eventName")?:"Test Event"

        ct!!.pushEvent(eventName, deviceDetailsMap)
        result.success(true)
      }


      else -> result.notImplemented()
    }
  }

  val isDebug: Boolean by lazy {
    BuildConfig.DEBUG
  }

//  override fun postEvent(eventName: String): Boolean {
//    val values = addDeviceDetails()
//    ct.pushEvent(eventName, values)
//    if (isDebug) {
//      Log.d("ClevertapEvent", "EventName: $eventName $values")
//    }
//    return true
//  }

  private fun initializeDeviceDetailsMap() {
    deviceDetailsMap.put("OS Version", Build.VERSION.RELEASE)
    deviceDetailsMap.put("App Version", BuildConfig.VERSION_NAME)
    deviceDetailsMap.put("Device Model", Build.MODEL)
    deviceDetailsMap.put("Manufacturer", Build.MANUFACTURER)
    deviceDetailsMap.put("Device Brand", Build.BRAND)
//    val telephonyManager = Activity().getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
//    if(checkReadPhoneStatePermission())
//      deviceDetailsMap.put("Device IMEI", telephonyManager.deviceId)

    Log.d("flutter device Details", deviceDetailsMap.entries.toString())
  }

//  private fun checkReadPhoneStatePermission(): Boolean {
//    val permission = android.Manifest.permission.READ_PHONE_STATE
//    return (Activity().checkCallingOrSelfPermission(permission) == PackageManager.PERMISSION_GRANTED)
//  }




}
