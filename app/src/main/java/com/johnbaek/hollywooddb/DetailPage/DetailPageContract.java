package com.johnbaek.hollywooddb.DetailPage;

import android.net.Uri;
import android.widget.ToggleButton;

import com.johnbaek.hollywooddb.model.SearchItem;

public interface DetailPageContract {
    interface Model {
        String setMediaType(SearchItem searchItem);
        Uri createPosterURI(SearchItem searchItem, String mediaType);
        String setID(SearchItem searchItem);
        Integer setVoteAverage(SearchItem searchItem);
    }

    interface View {
        void displayDetailView();
    }

    interface Presenter {
        void setDetailSubject(SearchItem detailSubject);
        String getMediaType();
        Uri getPosterURI();
        String getID();
        Integer getVoteAverage();
        String getOverview();
        void setFavoriteStatus(ToggleButton detailToggleFavorite);
    }
}
