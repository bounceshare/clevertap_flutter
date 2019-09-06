package com.bounceshare.clevertap_flutter;

import android.app.Activity;
import android.app.Application;
import com.clevertap.android.sdk.ActivityLifecycleCallback;

import io.flutter.view.FlutterMain;

public class ClevertapApplication extends Application {
    private Activity mCurrentActivity = null;

    public ClevertapApplication() {
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

