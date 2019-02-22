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

    BrowsePageModel(BrowsePagePresenter presenter) {
        this.movieAPI = RetrofitClient.getRetrofitMovieClient();
        this.presenter = presenter;
    }

    @Override
    public void retrieveTopMovies(){
        Call<SearchListings> topMovies = movieAPI.getTopMovies();
        topMovies.enqueue(new Callback<SearchListings>() {
            @Override
            public void onResponse(Call<SearchListings> call, Response<SearchListings> response) {
                presenter.onTopMoviesRetrievedSuccessful(response);
            }

            @Override
            public void onFailure(Call<SearchListings> call, Throwable t) {
                presenter.onMoviesRetrievedFailed(t);
            }
        });
    }

    @Override
    public void retrieveUpcomingMovies(){
        Call<SearchListings> upcomingMovies = movieAPI.getUpcomingMovies();
        upcomingMovies.enqueue(new Callback<SearchListings>() {
            @Override
            public void onResponse(Call<SearchListings> call, Response<SearchListings> response) {
                presenter.onUpcomingMoviesRetrievedSuccessful(response);
            }

            @Override
            public void onFailure(Call<SearchListings> call, Throwable t) {
                presenter.onMoviesRetrievedFailed(t);
            }
        });
    }

    @Override
    public void retrieveNowPlayingMovies(){
        Call<SearchListings> nowPlayingMovies = movieAPI.getNowPlayingMovies();
        nowPlayingMovies.enqueue(new Callback<SearchListings>() {
            @Override
            public void onResponse(Call<SearchListings> call, Response<SearchListings> response) {
                presenter.onNowPlayingMoviesRetrievedSuccessful(response);
            }

            @Override
            public void onFailure(Call<SearchListings> call, Throwable t) {
                presenter.onMoviesRetrievedFailed(t);
            }
        });
    }
}
