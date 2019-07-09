package com.johnbaek.hollywooddb.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TrailerListings {
    @SerializedName("results")
    private ArrayList<TrailerItem> trailerListings;

    public TrailerListings(ArrayList<TrailerItem> trailerListings) {
        this.trailerListings = trailerListings;
    }

    public ArrayList<TrailerItem> getTrailerListings() {
        return trailerListings;
    }
}
