package com.johnbaek.hollywooddb;

import android.arch.persistence.room.Database;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.johnbaek.hollywooddb.Database.DatabaseInitializer;
import com.johnbaek.hollywooddb.Database.Favorites;

import java.util.ArrayList;
import java.util.List;

public class Util {

    public void onFavoriteClick(ToggleButton favoriteToggle, Favorites favorite){
        final ScaleAnimation scaleAnimation = new ScaleAnimation(0.7f, 1.0f, 0.7f, 1.0f, Animation.RELATIVE_TO_SELF, 0.7f, Animation.RELATIVE_TO_SELF, 0.7f);
        scaleAnimation.setDuration(500);
        BounceInterpolator bounceInterpolator = new BounceInterpolator();
        scaleAnimation.setInterpolator(bounceInterpolator);
        Favorites temp = DatabaseInitializer.getFavoriteByIdentifier(favorite.getIdentifier());

        if (temp == null){
//            DatabaseInitializer.asyncAddFavorite(favorite);
            new Thread(() -> DatabaseInitializer.asyncAddFavorite(favorite)).start();
        } else {
            DatabaseInitializer.asyncDeleteFavorite(temp);
            new Thread(() -> DatabaseInitializer.asyncDeleteFavorite(temp)).start();
        }

        favoriteToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                //animation
                compoundButton.startAnimation(scaleAnimation);
            }
        });
    }
}
