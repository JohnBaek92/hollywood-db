package com.johnbaek.hollywooddb.model;

import com.google.gson.annotations.SerializedName;

public class Movie {
    public Movie(String voteAverage, String posterPath, String title) {
        this.voteAverage = voteAverage;
        this.posterPath = posterPath;
        this.title = title;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getTitle() {
        return title;
    }

    @SerializedName("vote_average")
    private String voteAverage;
    @SerializedName("poster_path")
    private String posterPath;
    private String title;

}
