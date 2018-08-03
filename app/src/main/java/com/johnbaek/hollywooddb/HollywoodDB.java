package com.johnbaek.hollywooddb;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

public class HollywoodDB extends Application {
    private static Context context;

    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        HollywoodDB.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return HollywoodDB.context;
    }
}
