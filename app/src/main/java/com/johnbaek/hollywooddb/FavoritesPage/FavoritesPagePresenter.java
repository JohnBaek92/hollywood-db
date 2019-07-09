package com.johnbaek.hollywooddb.FavoritesPage;

import com.johnbaek.hollywooddb.Database.Favorites;

import java.util.List;

public class FavoritesPagePresenter implements FavoritesPageContract.Presenter {
    private FavoritesPageContract.Model model;
    private FavoritesPageContract.View view;

    FavoritesPagePresenter(FavoritesActivity view) {
        this.view = view;
        this.model = new FavoritesPageModel(this);
    }

    public void fetchFavorites() {
        model.retrieveFavorites();
    }

    public void displayFavorites(List<Favorites> favorites) {
        view.displayResults(favorites);
    }
}
