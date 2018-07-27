package com.johnbaek.hollywooddb.SearchPage;

import com.johnbaek.hollywooddb.model.SearchItem;
import com.johnbaek.hollywooddb.model.SearchListings;

import java.util.ArrayList;

import retrofit2.Response;

public interface SearchPageContract {
    interface Model {
        void retrieveResults(String searchSubject);
    }

    interface View {
        void displayResults(ArrayList<SearchItem> searchItems);
        void displaySearchResultText();
        void showToastMessage(String message);
        void clearData();
    }

    interface Presenter {
        void fetchResults(final String searchSubject);
        void setSearchSubject(String searchSubject);
        String getSearchSubject();
        void onSearchResultsRetrievedFailed(Throwable throwable);
        void onSearchResultsRetrievedSuccessful(Response<SearchListings> response);
    }
}