package com.johnbaek.hollywooddb.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieListings {
    @SerializedName("results")
    private ArrayList<Movie> movies;

    public MovieListings(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public ArrayList<Movie> getMovies(){
        return movies;
    }

}
