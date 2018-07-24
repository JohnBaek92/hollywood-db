package com.johnbaek.hollywooddb.model;

import com.google.gson.annotations.SerializedName;

public class Movie {
    private String title;
    @SerializedName("vote_average")
    private Float voteAverage;
    @SerializedName("poster_path")
    private String posterPath;

    public Movie(Float voteAverage, String posterPath, String title) {
        this.voteAverage = voteAverage;
        this.posterPath = posterPath;
        this.title = title;
    }

    public Float getVoteAverage() {
        return voteAverage;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getTitle(){
        return title;
    }
}
