package com.johnbaek.hollywooddb.model;

import com.google.gson.annotations.SerializedName;

public class SearchItem {
    @SerializedName("media_type")
    private String mediaType;
    @SerializedName("vote_average")
    private Float voteAverage;
    @SerializedName("poster_path")
    private String posterPath;
    private String overview;
    @SerializedName("name")
    private String hollywoodName;
    @SerializedName("title")
    private String hollywoodTitle;

    public SearchItem(String mediaType, Float voteAverage, String posterPath, String overview, String hollywoodTitle, String hollywoodName) {
        this.mediaType = mediaType;
        this.voteAverage = voteAverage;
        this.posterPath = posterPath;
        this.overview = overview;
        this.hollywoodTitle = hollywoodTitle;
        this.hollywoodName = hollywoodName;
    }

    public String getMediaType() {
        return mediaType;
    }

    public Float getVoteAverage() {
        return voteAverage;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public String getHollywoodTitle() { return hollywoodTitle; }

    public String getHollywoodName() { return hollywoodName; }

    public String getPosterURL(String filePath,  String posterSize) {
        String BASE_URL = "https://image.tmdb.org/t/p";

        String movieURL = BASE_URL + posterSize + filePath;

        return movieURL;
    }
}