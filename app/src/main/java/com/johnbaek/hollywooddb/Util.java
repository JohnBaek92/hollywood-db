package com.johnbaek.hollywooddb;

import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.johnbaek.hollywooddb.Database.DatabaseInitializer;
import com.johnbaek.hollywooddb.Database.Favorites;

public class Util {

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

    static public void showToastMessage(String message) {
        Toast.makeText(HollywoodDB.getAppContext(), message, Toast.LENGTH_LONG).show();
    }
}
