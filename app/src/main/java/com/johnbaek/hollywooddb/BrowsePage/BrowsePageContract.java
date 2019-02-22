package com.johnbaek.hollywooddb.BrowsePage;

import com.johnbaek.hollywooddb.model.SearchItem;
import com.johnbaek.hollywooddb.model.SearchListings;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

public interface BrowsePageContract {
    interface Model {
        void retrieveTopMovies();
        void retrieveUpcomingMovies();
        void retrieveNowPlayingMovies();
    }

    interface View {
        void displayMovies(ArrayList<SearchItem> movies, int recyclerViewID);
        void showToastMessage(String message);
    }

    interface Presenter {
        void onMoviesRetrievedFailed(Throwable throwable);
        void fetchTopMovies();
        void fetchUpcomingMovies();
        void fetchNowPlayingMovies();
        void onTopMoviesRetrievedSuccessful(Response<SearchListings> response);
        void onUpcomingMoviesRetrievedSuccessful(Response<SearchListings> response);
        void onNowPlayingMoviesRetrievedSuccessful(Response<SearchListings> response);
    }
}
