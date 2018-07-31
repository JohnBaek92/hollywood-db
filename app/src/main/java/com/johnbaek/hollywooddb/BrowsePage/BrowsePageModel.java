package com.johnbaek.hollywooddb.BrowsePage;

import android.support.annotation.NonNull;

import com.johnbaek.hollywooddb.model.SearchListings;
import com.johnbaek.hollywooddb.network.MovieAPI;
import com.johnbaek.hollywooddb.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BrowsePageModel implements BrowsePageContract.Model {
    private BrowsePageContract.Presenter presenter;
    private MovieAPI movieAPI;
    private static String BROWSE_TOP_MOVIES_RECYCLER = "browse_top_movies_recycler";
    private static String BROWSE_UPCOMING_MOVIES_RECYCLER = "browse_upcoming_movies_recycler";
    private static String BROWSE_NOW_PLAYING_MOVIES_RECYCLER = "now_playing_movies_recycler";

    BrowsePageModel(BrowsePagePresenter presenter) {
        this.movieAPI = RetrofitClient.getRetrofitMovieClient();
        this.presenter = presenter;
    }

    public void retrieveTopMovies(){
        Call<SearchListings> topMovies = movieAPI.getTopMovies();
        enqueueMovies(topMovies, BROWSE_TOP_MOVIES_RECYCLER);
    }

    public void retrieveUpcomingMovies(){
        Call<SearchListings> upcomingMovies = movieAPI.getUpcomingMovies();
        enqueueMovies(upcomingMovies, BROWSE_UPCOMING_MOVIES_RECYCLER);
    }

    public void retrieveNowPlayingMovies(){
        Call<SearchListings> nowPlayingMovies = movieAPI.getNowPlayingMovies();
        enqueueMovies(nowPlayingMovies, BROWSE_NOW_PLAYING_MOVIES_RECYCLER);
    }

    public void enqueueMovies(Call<SearchListings> movies, final String recyclerView){
        movies.enqueue(new Callback<SearchListings>() {
            @Override
            public void onResponse(@NonNull Call<SearchListings> call, @NonNull Response<SearchListings> response) {
                if (response.isSuccessful()) {
                    presenter.onMoviesRetrievedSuccessful(response, recyclerView);
                }
            }

            @Override
            public void onFailure(@NonNull Call<SearchListings> call, @NonNull Throwable t) {
                presenter.onMoviesRetrievedFailed(t);
            }
        });
    }
}
