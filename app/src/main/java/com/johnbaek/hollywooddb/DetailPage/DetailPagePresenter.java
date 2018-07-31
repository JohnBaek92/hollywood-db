package com.johnbaek.hollywooddb.DetailPage;

import android.net.Uri;

import com.johnbaek.hollywooddb.model.SearchItem;

public class DetailPagePresenter implements DetailPageContract.Presenter {
    private DetailActivity view;
    private SearchItem detailSubject;
    private DetailPageContract.Model model;

    public DetailPagePresenter(DetailActivity view) {
        this.view = view;
        this.model = new DetailPageModel(this);
    }

    public void setDetailSubject(SearchItem detailSubject){
        this.detailSubject = detailSubject;
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
}
