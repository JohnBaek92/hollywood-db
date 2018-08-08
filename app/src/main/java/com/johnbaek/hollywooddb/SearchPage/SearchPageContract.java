package com.johnbaek.hollywooddb.SearchPage;

import com.johnbaek.hollywooddb.model.SearchItem;
import com.johnbaek.hollywooddb.model.SearchListings;

import java.util.ArrayList;

import retrofit2.Response;

public interface SearchPageContract {
    interface Model {
        void retrieveAllResults(String searchSubject, String mediaType);
        void retrievePeopleResults(String searchSubject, String mediaType);
        void retrieveMovieResults(String searchSubject, String mediaType);
        void retrieveTvResults(String searchSubject, String mediaType);
    }

    interface View {
        void displayResults(ArrayList<SearchItem> searchItems);
        void displaySearchResultText(String searchSubject);
        void clearData();
    }

    interface Presenter {
        void fetchResults();
        String getSearchSubject();
        void setSearchSubject(String searchSubject);
        void setMediaType(String mediaType);
        String getMediaType();
        void onSearchResultsRetrievedFailed(Throwable throwable);
        void onSearchResultsRetrievedSuccessful(ArrayList<SearchItem> searchItems);
    }
}
