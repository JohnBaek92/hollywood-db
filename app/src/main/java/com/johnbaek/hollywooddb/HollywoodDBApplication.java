package com.johnbaek.hollywooddb;

import android.app.Application;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.johnbaek.hollywooddb.CategorizedSearchFragment.CategorizedSearchFragment;
import com.johnbaek.hollywooddb.Database.FavoritesDatabase;

public class HollywoodDBApplication extends Application {
    private static FavoritesDatabase favoritesDatabase;

    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        favoritesDatabase = FavoritesDatabase.getFavoritesDatabase(this);
    }

    public static FavoritesDatabase getFavoritesDatabase() {
        return favoritesDatabase;
    }
}
