package com.johnbaek.hollywooddb.model;

import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.annotations.Expose;
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
    private boolean isFavorite;

    private static String BASE_URL = "https://image.tmdb.org/t/p";

    public SearchItem(String mediaType, Float voteAverage, String posterPath, String overview,
                      String hollywoodTitle, String hollywoodName, String profilePath) {
        this.mediaType = mediaType;
        this.voteAverage = voteAverage;
        this.posterPath = posterPath;
        this.overview = overview;
        this.hollywoodTitle = hollywoodTitle;
        this.hollywoodName = hollywoodName;
        this.profilePath = profilePath;
    }

    public String getMediaType() {
        return mediaType;
    }

    public Float getVoteAverage() {
        return voteAverage/2;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getProfilePath() { return profilePath; }

    public String getOverview() {
        return overview;
    }

    public String getHollywoodTitle() { return hollywoodTitle; }

    public String getHollywoodName() { return hollywoodName; }

    public String getPosterURL(String filePath,  String posterSize) {
        String posterURL = BASE_URL + posterSize + filePath;

        return posterURL;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
