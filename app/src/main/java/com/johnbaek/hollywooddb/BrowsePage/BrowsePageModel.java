package com.johnbaek.hollywooddb.BrowsePage;

import com.johnbaek.hollywooddb.SearchPage.SearchPageContract;
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
        this.movieAPI = RetrofitClient.getRetrofitInstance().create(com.johnbaek.hollywooddb.network.MovieAPI.class);
        this.presenter = presenter;
    }

    public void retrieveTopMovies(){
        Call<SearchListings> topMovies = movieAPI.getTopMovies();
        topMovies.enqueue(new Callback<SearchListings>() {
            @Override
            public void onResponse(Call<SearchListings> call, Response<SearchListings> response) {
                if (response.isSuccessful()) {
                    presenter.onMoviesRetrievedSuccessful(response, "browse_top_movies_recycler");
                }
            }

            @Override
            public void onFailure(Call<SearchListings> call, Throwable t) {
                presenter.onMoviesRetrievedFailed(t);
            }
        });
    }

    public void retrieveUpcomingMovies(){
        Call<SearchListings> topMovies = movieAPI.getUpcomingMovies();
        topMovies.enqueue(new Callback<SearchListings>() {
            @Override
            public void onResponse(Call<SearchListings> call, Response<SearchListings> response) {
                if (response.isSuccessful()) {
                    presenter.onMoviesRetrievedSuccessful(response, "browse_upcoming_movies_recycler");
                }
            }

            @Override
            public void onFailure(Call<SearchListings> call, Throwable t) {
                presenter.onMoviesRetrievedFailed(t);
            }
        });
    }

    public void retrieveNowPlayingMovies(){
        Call<SearchListings> topMovies = movieAPI.getNowPlayingMovies();
        topMovies.enqueue(new Callback<SearchListings>() {
            @Override
            public void onResponse(Call<SearchListings> call, Response<SearchListings> response) {
                if (response.isSuccessful()) {
                    presenter.onMoviesRetrievedSuccessful(response, "now_playing_movies_recycler");
                }
            }

            @Override
            public void onFailure(Call<SearchListings> call, Throwable t) {
                presenter.onMoviesRetrievedFailed(t);
            }
        });
    }
}
