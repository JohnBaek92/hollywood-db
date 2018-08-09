package com.johnbaek.hollywooddb;

import android.content.Context;
import android.graphics.Movie;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.johnbaek.hollywooddb.Database.DatabaseInitializer;
import com.johnbaek.hollywooddb.Database.Favorites;
import com.johnbaek.hollywooddb.network.MovieAPI;

public class Util {
    private static String BASE_URL = "https://image.tmdb.org/t/p";

    static public void onFavoriteClick(ToggleButton favoriteToggle, Favorites favorite){
        final ScaleAnimation scaleAnimation = new ScaleAnimation(0.7f, 1.0f, 0.7f, 1.0f, Animation.RELATIVE_TO_SELF, 0.7f, Animation.RELATIVE_TO_SELF, 0.7f);
        scaleAnimation.setDuration(500);
        BounceInterpolator bounceInterpolator = new BounceInterpolator();
        scaleAnimation.setInterpolator(bounceInterpolator);
        new DatabaseInitializer.AsyncToggleFavorite(favoriteReturn -> {}).execute(favorite);
        favoriteToggle.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            //animation
            compoundButton.startAnimation(scaleAnimation);
        });
    }

    static public void showToastMessage(String message, Context context) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    static public String getPosterURL(String filePath,  String posterSize) {
        return BASE_URL + posterSize + filePath;
    }
}
