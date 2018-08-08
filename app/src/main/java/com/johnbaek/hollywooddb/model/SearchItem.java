package com.johnbaek.hollywooddb.model;

import com.google.gson.annotations.SerializedName;

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
    }

    public String getBiography(){ return this.biography; }

    public void setBiography(String biography){ this.biography = biography; }

    public int getDatabaseId(){ return databaseId; }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType){
        this.mediaType = mediaType;
    }

    public Float getVoteAverage() {
        return voteAverage;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getProfilePath() { return profilePath; }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview){ this.overview = overview; }

    public String getHollywoodTitle() { return hollywoodTitle; }

    public String getHollywoodName() { return hollywoodName; }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
