package com.johnbaek.hollywooddb.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface FavoritesDaoAccess {

    @Insert
    Favorites insertFavorite (Favorites favorite);
    @Query("SELECT * FROM favorites WHERE identifier = :identifier")
    Favorites fetchFavoriteByIdentifier (String identifier);
    @Query("SELECT * FROM favorites")
    List<Favorites> fetchAllFavorites();
    @Delete
    void deleteFavorite (Favorites favorite);
}
