package com.johnbaek.hollywooddb.SearchPage;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.johnbaek.hollywooddb.model.SearchItem;
import com.johnbaek.hollywooddb.model.SearchListings;
import com.johnbaek.hollywooddb.network.MovieAPI;
import com.johnbaek.hollywooddb.network.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPagePresenter implements SearchPageContract.Presenter {
    private SearchPageContract.View view;
    private String searchSubject;
    private SearchPageContract.Model model;
    private static String NO_RESULTS = "No Results";

    public SearchPagePresenter(SearchActivity view) {
        this.view = view;
        this.searchSubject = "";
        this.model = new SearchPageModel(this);
    }

    @Override
    public void fetchResults(String searchSubject) {
        view.clearData();
        model.retrieveResults(searchSubject);
    }

    public String getSearchSubject(){
        return this.searchSubject;
    }

    public void setSearchSubject(String searchSubject) {
        this.searchSubject = searchSubject;
        view.displaySearchResultText(searchSubject);
    }

    public void onSearchResultsRetrievedSuccessful(Response<SearchListings> response) {
        SearchListings unformattedResults = response.body();
        ArrayList<SearchItem> searchItems = unformattedResults.getSearchItemListings();
        if( searchItems.size() >= 1) {
            view.displayResults(searchItems);
        } else {
            view.showToastMessage(NO_RESULTS);
        }

    }

    public void onSearchResultsRetrievedFailed(Throwable throwable) {view.showToastMessage(throwable.getMessage());}
}
