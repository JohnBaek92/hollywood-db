package com.johnbaek.hollywooddb.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = Favorites.class, version = 1, exportSchema = false)
    public abstract class FavoritesDatabase extends RoomDatabase {
        private static FavoritesDatabase FAVORITES_DATABASE;

        public abstract FavoritesDaoAccess favoritesDaoAccess();

        public static FavoritesDatabase getFavoritesDatabase(Context context) {
            if (FAVORITES_DATABASE == null) {
                FAVORITES_DATABASE = Room.databaseBuilder(context.getApplicationContext(), FavoritesDatabase.class, "favoritesDatabase")
                        .allowMainThreadQueries()
                        .build();
            }
            return FAVORITES_DATABASE;
        }

        public static void destroyInstance() {
            FAVORITES_DATABASE = null;
        }
}
