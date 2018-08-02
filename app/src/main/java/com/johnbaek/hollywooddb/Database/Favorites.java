package com.johnbaek.hollywooddb.Database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "favorites")
public class Favorites {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private Integer favoriteId;
    private String identifier;
    private String mediaType;
    private String imageURI;
    private Float voteAverage;
    private String overview;

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getImageURI() {
        return imageURI;
    }

    public void setImageURI(String posterPath) {
        this.imageURI = posterPath;
    }

    public Float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Integer getFavoriteId(){
        return favoriteId ;
    }

    public void setFavoriteId(Integer favoriteId){
        this.favoriteId = favoriteId;
    }

    public void setIdentifier(String identifier){
        this.identifier = identifier;
    }

    public String getIdentifier(){
        return identifier;
    }
}
