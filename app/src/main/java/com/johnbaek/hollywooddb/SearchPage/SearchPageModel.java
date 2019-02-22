package com.johnbaek.hollywooddb.SearchPage;

import android.support.annotation.NonNull;

import com.johnbaek.hollywooddb.model.SearchItem;
import com.johnbaek.hollywooddb.model.SearchListings;
import com.johnbaek.hollywooddb.network.MovieAPI;
import com.johnbaek.hollywooddb.network.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPageModel implements SearchPageContract.Model {
    private SearchPageContract.Presenter presenter;
    private MovieAPI movieAPI;
    private final static String PERSON = "person";
    private final static String MOVIE = "movie";
    private final static String TV = "tv";
    private final static String ALL = "all";

    SearchPageModel(SearchPagePresenter presenter) {
        this.presenter = presenter;
        this.movieAPI = RetrofitClient.getRetrofitMovieClient();
    }

    public void retrieveAllResults(String searchSubject) {
        Call<SearchListings> searchListings = movieAPI.getSearchAll(searchSubject);
        enqueueListings(searchListings, ALL);
    }

    public void retrievePeopleResults(String searchSubject) {
        Call<SearchListings> searchListings = movieAPI.getPeopleQueries(searchSubject);
        enqueueListings(searchListings, PERSON);
    }

    public void retrieveMovieResults(String searchSubject){
        Call<SearchListings> searchListings = movieAPI.getMovieQueries(searchSubject);
        enqueueListings(searchListings, MOVIE);
    }

    public void retrieveTvResults(String searchSubject){
        Call<SearchListings> searchListings = movieAPI.getTvQueries(searchSubject);
        enqueueListings(searchListings, TV);
    }

    private void enqueueListings(Call<SearchListings> searchListings, String mediaType){
        searchListings.enqueue(new Callback<SearchListings>() {
            @Override
            public void onResponse(@NonNull Call<SearchListings> call, @NonNull Response<SearchListings> response) {
                if (response.isSuccessful()) {
                    ArrayList<SearchItem> searchItems = response.body().getSearchItemListings();
                    for(SearchItem searchItem : searchItems) {
                        if (searchItem.getMediaType() == null) {
                            searchItem.setMediaType(mediaType);
                        }
                    }
                    presenter.onSearchResultsRetrievedSuccessful(searchItems);
                }
            }

            @Override
            public void onFailure(Call<SearchListings> call, Throwable t) {
                presenter.onSearchResultsRetrievedFailed(t);
            }
        });
    }
}
