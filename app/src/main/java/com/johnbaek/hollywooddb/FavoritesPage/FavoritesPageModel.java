package com.johnbaek.hollywooddb.FavoritesPage;

import com.johnbaek.hollywooddb.Database.DatabaseInitializer;

public class FavoritesPageModel implements FavoritesPageContract.Model {
    private FavoritesPageContract.Presenter presenter;

    public FavoritesPageModel(FavoritesPagePresenter presenter){
        this.presenter = presenter;
    }

    public void retrieveFavorites(){
        new DatabaseInitializer.AsyncGetFavorites(favorites -> {
            presenter.displayFavorites(favorites);
        }).execute();
    }
}
