package com.johnbaek.hollywooddb.Database;

import android.os.AsyncTask;
import android.util.Log;

import com.johnbaek.hollywooddb.HollywoodDBApplication;

import java.util.List;

public class DatabaseInitializer {
    private final static FavoritesDatabase db = HollywoodDBApplication.getFavoritesDatabase();

    private static Favorites addFavorite(Favorites favorite){
        db.favoritesDaoAccess().insertFavorite(favorite);
        return favorite;
    }

    private static String deleteFavorite(Favorites favorite){
        db.favoritesDaoAccess().deleteFavorite(favorite);
        return favorite.getIdentifier();
    }

    private static List<Favorites> getAllFavorites(){
        return db.favoritesDaoAccess().fetchAllFavorites();
    }

    private static Favorites getFavoriteByIdentifier(String identifier){
        return db.favoritesDaoAccess().fetchFavoriteByIdentifier(identifier);
    }

    public static class AsyncGetFavorites extends AsyncTask<Void, Void, List<Favorites>> {
        public interface FavoritesList{
            void processFinishAsync(List<Favorites> favorites);
        }

        FavoritesList favoritesList;

        public AsyncGetFavorites(FavoritesList favoritesList){
            this.favoritesList = favoritesList;
        }


        @Override
        protected List<Favorites> doInBackground(Void... voids) {
            try {
             return getAllFavorites();
            } catch (Exception e) {
                Log.d("error", e.getMessage());
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Favorites> favorites) {
            super.onPostExecute(favorites);
            favoritesList.processFinishAsync(favorites);
        }
    }

    public static class AsyncToggleFavorite extends AsyncTask<Favorites, Void, Favorites>{
        public interface ToggleFavorite {
            void processFinishAsync(Favorites favorite);
        }

        public ToggleFavorite favorite;

        public AsyncToggleFavorite(ToggleFavorite favorite){
            this.favorite = favorite;
        }

        @Override
        protected Favorites doInBackground(Favorites... params) {
            Favorites favorite = params[0];
            Favorites favoriteChecker = DatabaseInitializer.getFavoriteByIdentifier(favorite.getIdentifier());

            if (favoriteChecker == null){
                addFavorite(favorite);
            } else {
                deleteFavorite(favoriteChecker);
            }
            return favorite;
        }

        @Override
        protected void onPostExecute(Favorites favoriteToToggle) {
            super.onPostExecute(favoriteToToggle);
            favorite.processFinishAsync(favoriteToToggle);
        }
    }
}
