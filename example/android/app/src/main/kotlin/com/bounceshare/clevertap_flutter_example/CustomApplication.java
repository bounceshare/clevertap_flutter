package com.bounceshare.clevertap_flutter_example;


import android.app.Activity;
import android.app.Application;
// import android.support.annotation.CallSuper;

import com.clevertap.android.sdk.ActivityLifecycleCallback;

import io.flutter.view.FlutterMain;

public class CustomApplication extends Application {
    private Activity mCurrentActivity = null;

    public CustomApplication() {
    }

    @Override
    public void onCreate() {
        ActivityLifecycleCallback.register(this);
        super.onCreate();
        FlutterMain.startInitialization(this);
    }

    public Activity getCurrentActivity() {
        return this.mCurrentActivity;
    }

    public void setCurrentActivity(Activity mCurrentActivity) {
        this.mCurrentActivity = mCurrentActivity;
    }
}

