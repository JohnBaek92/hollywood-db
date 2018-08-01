package com.johnbaek.hollywooddb.Database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Favorites {
    @NonNull
    @PrimaryKey
    private Integer favoriteId;
    private String identifier;
    private String mediaType;
    private String posterPath;
    private Float voteAverage;
    private String overview;

    public Favorites(){

    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
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

    @NonNull
    public Integer getFavoriteId(){
        return favoriteId ;
    }

    public void setFavoriteId(@NonNull Integer favoriteId){
        this.favoriteId = favoriteId;
    }

    public void setIdentifier(String identifier){
        this.identifier = identifier;
    }

    public String getIdentifier(){
        return identifier;
    }

}
