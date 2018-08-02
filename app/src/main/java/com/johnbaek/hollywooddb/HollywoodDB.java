package com.johnbaek.hollywooddb;

import android.app.Application;
import android.content.Context;

public class HollywoodDB extends Application {
    private static Context context;

    public void onCreate() {
        super.onCreate();
        HollywoodDB.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return HollywoodDB.context;
    }
}
