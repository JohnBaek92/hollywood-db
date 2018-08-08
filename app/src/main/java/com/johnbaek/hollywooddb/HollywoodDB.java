package com.johnbaek.hollywooddb;

import android.app.Application;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.johnbaek.hollywooddb.CategorizedSearchFragment.CategorizedSearchFragment;

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
