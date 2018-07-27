package com.johnbaek.hollywooddb.BrowsePage;

import com.johnbaek.hollywooddb.model.SearchItem;
import com.johnbaek.hollywooddb.model.SearchListings;

import java.util.ArrayList;

import retrofit2.Response;

public interface BrowsePageContract {
    interface Model {
        void retrieveTopMovies();
        void retrieveUpcomingMovies();
        void retrieveNowPlayingMovies();
    }

    interface View {
        void showToastMessage(String message);
        void displayMovies(ArrayList<SearchItem> movies, String recyclerView);
    }

    interface Presenter {
        void onMoviesRetrievedSuccessful(Response<SearchListings> response, String recyclerView);
        void onMoviesRetrievedFailed(Throwable throwable);
    }
}
