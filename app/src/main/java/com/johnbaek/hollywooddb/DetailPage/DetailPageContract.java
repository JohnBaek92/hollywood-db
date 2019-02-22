package com.johnbaek.hollywooddb.DetailPage;

import android.net.Uri;
import android.widget.ToggleButton;

import com.johnbaek.hollywooddb.model.SearchItem;

public interface DetailPageContract {
    interface Model {
        String setMediaType(SearchItem searchItem);
        String setID(SearchItem searchItem);
        void retrievePersonOverview(SearchItem searchItem);
        void retrieveTrailers(SearchItem searchItem);
    }

    interface View {
        void displayDetailView(SearchItem searchItem);
        void showToastMessage(String message);
    }

    interface Presenter {
        void onSearchResultsRetrievedFailed(Throwable throwable);
        void onPersonRetrievedSuccessful();
        String getMediaType();
        String getPosterURL();
        Uri getPosterURI();
        String getID();
        Float getVoteAverage();
        int getDatabaseId();
        void setFavoriteStatus(ToggleButton detailToggleFavorite);
    }
}
