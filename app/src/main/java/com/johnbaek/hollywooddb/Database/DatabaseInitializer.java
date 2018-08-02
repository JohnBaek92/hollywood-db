package com.johnbaek.hollywooddb.Database;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.johnbaek.hollywooddb.HollywoodDB;

import java.util.ArrayList;
import java.util.List;

public class DatabaseInitializer {
    final static FavoritesDatabase db = FavoritesDatabase.getFavoritesDatabase(HollywoodDB.getAppContext());

    public static Favorites asyncAddFavorite(Favorites favorite){
        return DbAsync.addFavorite(favorite);
    }

    public static Favorites getFavoriteByIdentifier(String identifier){
        Favorites favorite = db.favoritesDaoAccess().fetchFavoriteByIdentifier(identifier);
        return favorite;
    }

//    public static List<Favorites> getAllFavorites(){
//        List<Favorites> favorites = db.favoritesDaoAccess().fetchAllFavorites();
//        return favorites;
//    }
    public static List<Favorites> getAllFavorites(){
        List<Favorites> temp = new ArrayList<>();
        new AsyncTask<Void, Void, List<Favorites>>() {
            @Override
            protected List<Favorites> doInBackground(Void... voids) {
                return db.favoritesDaoAccess().fetchAllFavorites();
            }
            @Override
            protected void onPostExecute(List<Favorites> favorites) {
                super.onPostExecute(favorites);
                if(favorites == null || favorites.isEmpty()) {
                    Log.d("BrowseActivityModel", "Movie database is empty");
                }
                else {
                    temp = favorites;
                }
            }
        }.execute();
    }

    public static String asyncDeleteFavorite(Favorites favorite){
        return DbAsync.deleteFavorite(favorite);
    }

    private static class DbAsync extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        private static Favorites addFavorite(Favorites favorite){
            db.favoritesDaoAccess().insertFavorite(favorite);
            return favorite;
        }

        private static String deleteFavorite(Favorites favorite){
            db.favoritesDaoAccess().deleteFavorite(favorite);
            return "Favorite has been deleted";
        }
    }
}
