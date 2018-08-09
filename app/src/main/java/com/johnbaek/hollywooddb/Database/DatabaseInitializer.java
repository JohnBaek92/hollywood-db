package com.johnbaek.hollywooddb.Database;

import android.os.AsyncTask;
import android.util.Log;

import com.johnbaek.hollywooddb.HollywoodDB;

import java.util.List;

public class DatabaseInitializer {
//    final static FavoritesDatabase db = FavoritesDatabase.getFavoritesDatabase(HollywoodDB.getAppContext());
    final static FavoritesDatabase db = HollywoodDB.getFavoritesDatabase();

    public static Favorites addFavorite(Favorites favorite){
        db.favoritesDaoAccess().insertFavorite(favorite);
        return favorite;
    }

    public static String deleteFavorite(Favorites favorite){
        db.favoritesDaoAccess().deleteFavorite(favorite);
        return favorite.getIdentifier();
    }

    public static List<Favorites> getAllFavorites(){
        List<Favorites> favorites = db.favoritesDaoAccess().fetchAllFavorites();
        return favorites;
    }

    public static Favorites getFavoriteByIdentifier(String identifier){
        Favorites favorite = db.favoritesDaoAccess().fetchFavoriteByIdentifier(identifier);
        return favorite;
    }

    public static class AsyncGetFavorites extends AsyncTask<Void, Void, List<Favorites>> {
        public interface FavoritesList{
            void processFinishAsync(List<Favorites> favorites);
        }

        public FavoritesList favoritesList;

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
            Favorites temp = DatabaseInitializer.getFavoriteByIdentifier(favorite.getIdentifier());

            if (temp == null){
                addFavorite(favorite);
            } else {
                deleteFavorite(temp);
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
