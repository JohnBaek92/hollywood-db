package com.johnbaek.hollywooddb.DetailPage;

import android.net.Uri;
import android.os.AsyncTask;

import com.johnbaek.hollywooddb.Database.DatabaseInitializer;
import com.johnbaek.hollywooddb.Database.Favorites;
import com.johnbaek.hollywooddb.model.SearchItem;

import java.util.HashSet;
import java.util.List;

public class DetailPagePresenter implements DetailPageContract.Presenter {
    private DetailActivity view;
    private SearchItem detailSubject;
    private DetailPageContract.Model model;

    public DetailPagePresenter(DetailActivity view) {
        this.view = view;
        this.model = new DetailPageModel(this);
    }

    public void setDetailSubject(SearchItem searchItem){
        this.detailSubject = searchItem;
        view.displayDetailView();
    }

    public String getMediaType(){
        return model.setMediaType(detailSubject);
    }

    public Uri getPosterURI(){
        String mediaType = model.setMediaType(detailSubject);
        return model.createPosterURI(detailSubject, mediaType);
    }

    public String getID(){
        return model.setID(detailSubject);
    }

    public Integer getVoteAverage(){
        return model.setVoteAverage(detailSubject);
    }

    public String getOverview(){
        return detailSubject.getOverview();
    }

    public Boolean getFavoriteStatus(){
        new DatabaseInitializer.AsyncGetFavorites(favorites -> {
            HashSet<String> hashFavoriteNames = new HashSet<>();
            for (Favorites favorite : favorites) {
                String identifier = favorite.getIdentifier();
                hashFavoriteNames.add(identifier);
            }
            String detailId = detailSubject.getHollywoodName() != null ? detailSubject.getHollywoodName() : detailSubject.getHollywoodTitle();
            detailSubject.setFavorite(hashFavoriteNames.contains(detailId));
        }).execute();
        return detailSubject.isFavorite();
    }
}
