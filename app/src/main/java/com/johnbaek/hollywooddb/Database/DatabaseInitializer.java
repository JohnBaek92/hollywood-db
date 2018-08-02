package com.johnbaek.hollywooddb.Database;

import android.os.AsyncTask;

import com.johnbaek.hollywooddb.HollywoodDB;

import java.util.List;

public class DatabaseInitializer {
    final static FavoritesDatabase db = FavoritesDatabase.getFavoritesDatabase(HollywoodDB.getAppContext());

//    public static List<Favorites> getAllFavorites(){
//        List<Favorites> favorites = db.favoritesDaoAccess().fetchAllFavorites();
//        return favorites;
//    }

    public static List<Favorites> asyncGetAllFavorites(){
        return DbAsync.getAllFavorites();
    }

//    public static Favorites asyncAddFavorite(Favorites favorite){
//        return DbAsync.addFavorite(favorite);
//    }

    public static Favorites getFavoriteByIdentifier(String identifier){
        Favorites favorite = db.favoritesDaoAccess().fetchFavoriteByIdentifier(identifier);
        return favorite;
    }

//    public static String asyncDeleteFavorite(Favorites favorite){
//        return DbAsync.deleteFavorite(favorite);
//    }

    public static class DbAsync extends AsyncTask<Favorites, Void, Void> {
        @Override
        protected Void doInBackground(Favorites... favorites) {
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        public static Favorites addFavorite(Favorites favorite){
            db.favoritesDaoAccess().insertFavorite(favorite);
            return favorite;
        }

        public static String deleteFavorite(Favorites favorite){
            db.favoritesDaoAccess().deleteFavorite(favorite);
            return "Favorite has been deleted";
        }

        public static List<Favorites> getAllFavorites(){
            List<Favorites> favorites = db.favoritesDaoAccess().fetchAllFavorites();
            return favorites;
        }
    }
}
