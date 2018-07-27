package com.johnbaek.hollywooddb.SearchPage;

import android.support.annotation.NonNull;

import com.johnbaek.hollywooddb.model.SearchListings;
import com.johnbaek.hollywooddb.network.MovieAPI;
import com.johnbaek.hollywooddb.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPageModel implements SearchPageContract.Model {
    private SearchPageContract.Presenter presenter;
    private MovieAPI movieAPI;

    public  SearchPageModel(SearchPagePresenter presenter) {
        this.presenter = presenter;
        this.movieAPI = RetrofitClient.getRetrofitInstance().create(com.johnbaek.hollywooddb.network.MovieAPI.class);
    }

    public void retrieveResults(String searchSubject) {
        Call<SearchListings> searchListings = movieAPI.getSearchAll(searchSubject);
        searchListings.enqueue(new Callback<SearchListings>() {
            @Override
            public void onResponse(@NonNull Call<SearchListings> call, @NonNull Response<SearchListings> response) {
                if (response.isSuccessful()) {
                    presenter.onSearchResultsRetrievedSuccessful(response);
                }
            }

            @Override
            public void onFailure(Call<SearchListings> call, Throwable t) {
                presenter.onSearchResultsRetrievedFailed(t);
            }
        });
    }
}
