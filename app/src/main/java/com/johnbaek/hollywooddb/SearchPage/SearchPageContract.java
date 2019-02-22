package com.johnbaek.hollywooddb.SearchPage;

import com.johnbaek.hollywooddb.model.SearchItem;
import com.johnbaek.hollywooddb.model.SearchListings;

import java.util.ArrayList;

import retrofit2.Response;

public interface SearchPageContract {
    interface Model {
        void retrieveAllResults(String searchSubject);
        void retrievePeopleResults(String searchSubject);
        void retrieveMovieResults(String searchSubject);
        void retrieveTvResults(String searchSubject);
    }

    interface View {
        void displayResults(ArrayList<SearchItem> searchItems);
        void displaySearchResultText(String searchSubject);
        void clearData();
        void showToastMessage(String throwable);
    }

    interface Presenter {
        void fetchResults();
        void setMediaType(String mediaType);
        String getMediaType();
        void onSearchResultsRetrievedFailed(Throwable throwable);
        void onSearchResultsRetrievedSuccessful(ArrayList<SearchItem> searchItems);
    }
}
