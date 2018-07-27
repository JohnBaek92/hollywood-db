package com.johnbaek.hollywooddb.BrowsePage;

import com.johnbaek.hollywooddb.model.SearchItem;
import com.johnbaek.hollywooddb.model.SearchListings;

import java.util.ArrayList;

import retrofit2.Response;

public class BrowsePagePresenter implements BrowsePageContract.Presenter {
    private BrowsePageContract.View view;
    private BrowsePageContract.Model model;

    public BrowsePagePresenter(BrowseActivity view) {
        this.view = view;
        this.model = new BrowsePageModel(this);
    }

    public void onMoviesRetrievedSuccessful(Response<SearchListings> response, String recyclerView) {
        SearchListings unformattedMovies = response.body();
        ArrayList<SearchItem> movies = unformattedMovies.getSearchItemListings();
        view.displayMovies(movies, recyclerView);

    }

    public void onMoviesRetrievedFailed(Throwable throwable) {
        view.showToastMessage(throwable.getMessage());
    }
}
