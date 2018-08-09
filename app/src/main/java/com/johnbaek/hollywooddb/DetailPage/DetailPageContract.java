package com.johnbaek.hollywooddb.DetailPage;

import android.net.Uri;
import android.widget.ToggleButton;

import com.johnbaek.hollywooddb.model.SearchItem;

public interface DetailPageContract {
    interface Model {
        String setMediaType(SearchItem searchItem);
        Uri createPosterURI(SearchItem searchItem, String mediaType);
        String setID(SearchItem searchItem);
        Float setVoteAverage(SearchItem searchItem);
        void retrievePersonOverview(SearchItem searchItem);
        void retrieveTrailers(SearchItem searchItem);
    }

    interface View {
        void displayDetailView();
        void showToastMessage(String throwable);
    }

    interface Presenter {
        void onSearchResultsRetrievedFailed(Throwable throwable);
        void onPersonRetrievedSuccessful();
        void setDetailSubject(SearchItem detailSubject);
        String getMediaType();
        String getPosterURL();
        Uri getPosterURI();
        String getID();
        Float getVoteAverage();
        String getOverview();
        int getDatabaseId();
        void setFavoriteStatus(ToggleButton detailToggleFavorite);
        String getTrailer();
    }
}
