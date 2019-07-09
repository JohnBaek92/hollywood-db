package com.johnbaek.hollywooddb.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SearchListings {
    @SerializedName("results")
    private ArrayList<SearchItem> searchItemListings;

    public SearchListings(ArrayList<SearchItem> searchItemListings) {
        this.searchItemListings = searchItemListings;
    }

    public ArrayList<SearchItem> getSearchItemListings() {
        return searchItemListings;
    }
}
