package com.johnbaek.hollywooddb.DetailPage;

import android.net.Uri;

import com.johnbaek.hollywooddb.Util;
import com.johnbaek.hollywooddb.model.SearchItem;
import com.johnbaek.hollywooddb.model.SearchListings;
import com.johnbaek.hollywooddb.model.TrailerListings;
import com.johnbaek.hollywooddb.network.MovieAPI;
import com.johnbaek.hollywooddb.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPageModel implements DetailPageContract.Model {
    DetailPageContract.Presenter presenter;
    private static String MOVIE = "movie";
    private MovieAPI movieAPI;

    DetailPageModel(DetailPagePresenter presenter){
        this.movieAPI = RetrofitClient.getRetrofitMovieClient();
        this.presenter = presenter;
    }

    public String setMediaType(SearchItem searchItem) {
        return searchItem.getMediaType() != null ? searchItem.getMediaType() : MOVIE;
    }

    public String setID(SearchItem searchItem){
        String ID = searchItem.getHollywoodName() != null ? ID = searchItem.getHollywoodName() : searchItem.getHollywoodTitle();
        return ID;
    }

    public void retrieveTrailers(SearchItem searchItem){
        String mediaType = searchItem.getMediaType();
        int databaseId = searchItem.getDatabaseId();
        Call<TrailerListings> trailers = movieAPI.getTrailers(mediaType, databaseId);
        trailers.enqueue(new Callback<TrailerListings>() {
            @Override
            public void onResponse(Call<TrailerListings> call, Response<TrailerListings> response) {
                if(response.isSuccessful() && !response.body().getTrailerListings().isEmpty()){
                    String trailerKey = response.body().getTrailerListings().get(0).getKey();
                    searchItem.setTrailerKey(trailerKey);
                }
                presenter.onPersonRetrievedSuccessful();
            }

            @Override
            public void onFailure(Call<TrailerListings> call, Throwable t) {
                presenter.onSearchResultsRetrievedFailed(t);
            }
        });
    }

    public void retrievePersonOverview(SearchItem searchItem){
        Call<SearchItem> searchListing = movieAPI.getPersonQuery(searchItem.getDatabaseId());
        searchListing.enqueue(new Callback<SearchItem>(){

            @Override
            public void onResponse(Call<SearchItem> call, Response<SearchItem> response) {
                if(response.isSuccessful()){
                    searchItem.setPersonOverview(response.body().getBiography());
                }
                presenter.onPersonRetrievedSuccessful();
            }

            @Override
            public void onFailure(Call<SearchItem> call, Throwable t) {
                presenter.onSearchResultsRetrievedFailed(t);
            }
        });
    }
}
