package com.johnbaek.hollywooddb.model;

import android.net.Uri;

import com.google.gson.annotations.SerializedName;
import com.johnbaek.hollywooddb.Util;

import java.io.Serializable;

public class SearchItem implements Serializable {
    @SerializedName("media_type")
    private String mediaType;
    @SerializedName("vote_average")
    private Float voteAverage;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("profile_path")
    private String profilePath;
    private String overview;
    @SerializedName("name")
    private String hollywoodName;
    @SerializedName("title")
    private String hollywoodTitle;
    @SerializedName("id")
    private int databaseId;
    private boolean isFavorite;
    private String biography;
    private String trailerKey;

    private static String MOVIE = "movie";
    private static String TV = "tv";
    private static String PERSON = "person";
    private static String POSTER_SIZE_185 = "/w185";

    public SearchItem(String mediaType, Float voteAverage, String posterPath, String overview,
                      String hollywoodTitle, String hollywoodName, String profilePath, int databaseId) {
        this.mediaType = mediaType;
        this.voteAverage = voteAverage;
        this.posterPath = posterPath;
        this.overview = overview;
        this.hollywoodTitle = hollywoodTitle;
        this.hollywoodName = hollywoodName;
        this.profilePath = profilePath;
        this.databaseId = databaseId;
        this.biography = "";
        this.trailerKey = null;
    }

    public String getTrailerKey() {
        return trailerKey;
    }

    public String getBiography() {
        return this.biography;
    }

    public int getDatabaseId() {
        return databaseId;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public Float setVoteAverage() {
        if (this.getMediaType() == null || !this.getMediaType().equals(PERSON)) {
            return this.getVoteAverage();
        } else {
            return 0.1f;
        }
    }

    public Float getVoteAverage() {
        return voteAverage;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public String getOverview() {
        return overview;
    }

    public void setPersonOverview(String overview) {
        this.overview = overview;
    }

    public void setTrailerKey(String trailerKey) {
        this.trailerKey = trailerKey;
    }

    public String getHollywoodTitle() {
        return hollywoodTitle;
    }

    public String getHollywoodName() {
        return hollywoodName;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public Uri createPosterURI(String mediaType) {
        Uri uri;
        if (mediaType.equals(MOVIE) || mediaType.equals(TV)) {
            String posterURI = this.getPosterPath();
            String posterURL = Util.getPosterURL(posterURI, POSTER_SIZE_185);
            uri = Uri.parse(posterURL);
        } else {
            String profileURI = this.getProfilePath();
            String profileURL = Util.getPosterURL(profileURI, POSTER_SIZE_185);
            uri = Uri.parse(profileURL);
        }

        return uri;
    }
}
