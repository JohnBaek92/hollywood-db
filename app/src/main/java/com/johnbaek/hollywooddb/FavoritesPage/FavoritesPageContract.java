package com.johnbaek.hollywooddb.FavoritesPage;

import com.johnbaek.hollywooddb.Database.Favorites;

import java.util.List;

class FavoritesPageContract {
    interface Model {
        void retrieveFavorites();
    }

    interface View {
        void displayResults(List<Favorites> favorites);
    }

    interface Presenter {
        void fetchFavorites();
        void displayFavorites(List<Favorites> favorites);
    }
}
