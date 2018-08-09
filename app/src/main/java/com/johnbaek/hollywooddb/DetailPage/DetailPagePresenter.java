package com.johnbaek.hollywooddb.DetailPage;

import android.net.Uri;
import android.widget.ToggleButton;

import com.johnbaek.hollywooddb.Database.DatabaseInitializer;
import com.johnbaek.hollywooddb.Database.Favorites;
import com.johnbaek.hollywooddb.Util;
import com.johnbaek.hollywooddb.model.SearchItem;

import java.nio.file.Path;
import java.util.HashSet;

public class DetailPagePresenter implements DetailPageContract.Presenter {
    private DetailActivity view;
    private SearchItem detailSubject;
    private DetailPageContract.Model model;
    private static String PERSON ="person";

    public DetailPagePresenter(DetailActivity view) {
        this.view = view;
        this.model = new DetailPageModel(this);
    }

    public void setDetailSubject(SearchItem searchItem){
        this.detailSubject = searchItem;
        if(detailSubject.getMediaType().equals(PERSON)){
            model.retrievePersonOverview(detailSubject);
        } else {
            model.retrieveTrailers(detailSubject);
        }
    }

    public void onSearchResultsRetrievedFailed(Throwable throwable) {
        view.showToastMessage(throwable.getMessage());
    }

    public String getMediaType(){
        return model.setMediaType(detailSubject);
    }

    public Uri getPosterURI(){
        String mediaType = model.setMediaType(detailSubject);
        return model.createPosterURI(detailSubject, mediaType);
    }

    public String getPosterURL(){
        if(detailSubject.getPosterPath() != null){
            return detailSubject.getPosterPath();
        } else {
            return detailSubject.getProfilePath();
        }
    }

    public String getID(){
        return model.setID(detailSubject);
    }

    public int getDatabaseId(){ return detailSubject.getDatabaseId(); }

    public Float getVoteAverage(){
        return model.setVoteAverage(detailSubject);
    }

    public String getTrailer() { return detailSubject.getTrailerKey(); }

    public String getOverview(){
        return detailSubject.getOverview();
    }

    public void onPersonRetrievedSuccessful(){
        view.displayDetailView();
    };

    public void setFavoriteStatus(ToggleButton detailToggleFavorite){
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
