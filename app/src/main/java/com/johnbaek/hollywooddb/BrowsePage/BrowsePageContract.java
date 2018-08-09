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
        void enqueueMovies(Call<SearchListings> movies, final String recyclerViewID);
    }

    interface View {
        void displayMovies(ArrayList<SearchItem> movies, String recyclerViewID);
        void showToastMessage(String throwable);
    }

    interface Presenter {
        void onMoviesRetrievedSuccessful(Response<SearchListings> response, String recyclerViewID);
        void onMoviesRetrievedFailed(Throwable throwable);
        void fetchTopMovies();
        void fetchUpcomingMovies();
        void fetchNowPlayingMovies();
    }
}
