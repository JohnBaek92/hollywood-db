package com.johnbaek.hollywooddb.BrowsePage;

import com.johnbaek.hollywooddb.R;
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
        fetchTopMovies();
        fetchUpcomingMovies();
        fetchNowPlayingMovies();
    }

    public void fetchTopMovies() {
        model.retrieveTopMovies();
    }

    public void fetchUpcomingMovies() {
        model.retrieveUpcomingMovies();
    }

    public void fetchNowPlayingMovies() {
        model.retrieveNowPlayingMovies();
    }

    public void onTopMoviesRetrievedSuccessful(Response<SearchListings> response) {
        ArrayList<SearchItem> movies = response.body().getSearchItemListings();
        view.displayMovies(movies, R.id.browse_top_movies_recycler);
    }

    public void onUpcomingMoviesRetrievedSuccessful(Response<SearchListings> response) {
        ArrayList<SearchItem> movies = response.body().getSearchItemListings();
        view.displayMovies(movies, R.id.browse_upcoming_movies_recycler);
    }

    public void onNowPlayingMoviesRetrievedSuccessful(Response<SearchListings> response) {
        ArrayList<SearchItem> movies = response.body().getSearchItemListings();
        view.displayMovies(movies, R.id.now_playing_movies_recycler);
    }

    public void onMoviesRetrievedFailed(Throwable throwable) {
        view.showToastMessage(throwable.getMessage());
    }
}
