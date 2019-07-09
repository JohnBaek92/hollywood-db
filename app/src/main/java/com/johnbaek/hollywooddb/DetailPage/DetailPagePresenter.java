package com.johnbaek.hollywooddb.DetailPage;

import android.net.Uri;
import android.widget.ToggleButton;

import com.johnbaek.hollywooddb.Database.DatabaseInitializer;
import com.johnbaek.hollywooddb.Database.Favorites;
import com.johnbaek.hollywooddb.model.SearchItem;

import java.util.HashSet;

public class DetailPagePresenter implements DetailPageContract.Presenter {
    private DetailActivity view;
    private SearchItem detailSubject;
    private DetailPageContract.Model model;
    private static String PERSON = "person";

    DetailPagePresenter(DetailActivity view, SearchItem searchItem) {
        this.view = view;
        this.model = new DetailPageModel(this);
        this.detailSubject = searchItem;
        setDetailSubject();
    }

    private void setDetailSubject() {
        if (detailSubject.getMediaType().equals(PERSON)) {
            model.retrievePersonOverview(detailSubject);
        } else {
            model.retrieveTrailers(detailSubject);
        }
    }

    public void onSearchResultsRetrievedFailed(Throwable throwable) {
        view.showToastMessage(throwable.getMessage());
    }

    public String getMediaType() {
        return model.setMediaType(detailSubject);
    }

    public Uri getPosterURI() {
        String mediaType = model.setMediaType(detailSubject);
        return detailSubject.createPosterURI(mediaType);
    }

    public String getPosterURL() {
        if (detailSubject.getPosterPath() != null) {
            return detailSubject.getPosterPath();
        } else {
            return detailSubject.getProfilePath();
        }
    }

    public String getID() {
        return model.setID(detailSubject);
    }

    public int getDatabaseId() {
        return detailSubject.getDatabaseId();
    }

    public Float getVoteAverage() {
        return detailSubject.setVoteAverage();
    }

    public void onPersonRetrievedSuccessful() {
        view.displayDetailView(detailSubject);
    }

    public void setFavoriteStatus(ToggleButton detailToggleFavorite) {
        new DatabaseInitializer.AsyncGetFavorites(favorites -> {
            HashSet<String> hashFavoriteNames = new HashSet<>();
            for (Favorites favorite : favorites) {
                String identifier = favorite.getIdentifier();
                hashFavoriteNames.add(identifier);
            }
            String detailId = detailSubject.getHollywoodName() != null ? detailSubject.getHollywoodName() : detailSubject.getHollywoodTitle();

            detailSubject.setFavorite(hashFavoriteNames.contains(detailId));

            if (hashFavoriteNames.contains(detailId)) {
                detailToggleFavorite.setChecked(true);
            } else {
                detailToggleFavorite.setChecked(false);
            }

        }).execute();
    }
}
