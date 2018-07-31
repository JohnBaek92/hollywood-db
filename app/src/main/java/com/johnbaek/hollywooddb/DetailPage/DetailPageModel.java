package com.johnbaek.hollywooddb.DetailPage;

import android.net.Uri;

import com.johnbaek.hollywooddb.model.SearchItem;

public class DetailPageModel implements DetailPageContract.Model {
    DetailPageContract.Presenter presenter;
    private static String MOVIE = "movie";
    private static String TV = "tv";
    private static String PERSON = "person";
    private static String POSTER_SIZE_185 = "/w185";

    public DetailPageModel(DetailPagePresenter presenter){
        this.presenter = presenter;
    }

    public String setMediaType(SearchItem searchItem) {
        String mediaType = searchItem.getMediaType() != null ? searchItem.getMediaType() : MOVIE;
        return mediaType;
    }

    public String setID(SearchItem searchItem){
        String ID = searchItem.getHollywoodName() != null ? ID = searchItem.getHollywoodName() : searchItem.getHollywoodTitle();
        return ID;
    }

    public Uri createPosterURI(SearchItem searchItem, String mediaType){
        Uri uri;
        if (mediaType.equals(MOVIE) || mediaType.equals(TV)) {
            String posterURI = searchItem.getPosterPath();
            String posterURL = searchItem.getPosterURL(posterURI, POSTER_SIZE_185);
            uri = Uri.parse(posterURL);
        } else {
            String profileURI = searchItem.getProfilePath();
            String profileURL = searchItem.getPosterURL(profileURI, POSTER_SIZE_185);
            uri = Uri.parse(profileURL);
        }

        return uri;
    }

    public Integer setVoteAverage(SearchItem searchItem){
        if (searchItem.getMediaType() == null || !searchItem.getMediaType().equals(PERSON)) {
            Float voteAverage = searchItem.getVoteAverage();
            return Math.round(voteAverage);
        }
       else {
            return 0;
        }
    }
}
