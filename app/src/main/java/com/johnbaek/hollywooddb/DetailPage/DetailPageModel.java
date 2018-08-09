package com.johnbaek.hollywooddb.DetailPage;

import android.net.Uri;

import com.johnbaek.hollywooddb.Util;
import com.johnbaek.hollywooddb.model.SearchItem;
import com.johnbaek.hollywooddb.model.SearchListings;
import com.johnbaek.hollywooddb.model.TrailerListings;
import com.johnbaek.hollywooddb.network.MovieAPI;
import com.johnbaek.hollywooddb.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPageModel implements DetailPageContract.Model {
    DetailPageContract.Presenter presenter;
    private static String MOVIE = "movie";
    private static String TV = "tv";
    private static String PERSON = "person";
    private static String POSTER_SIZE_185 = "/w185";
    private MovieAPI movieAPI;

    public DetailPageModel(DetailPagePresenter presenter){
        this.movieAPI = RetrofitClient.getRetrofitMovieClient();
        this.presenter = presenter;
    }

    public String setMediaType(SearchItem searchItem) {
        String mediaType = searchItem.getMediaType() != null ? searchItem.getMediaType() : MOVIE;
        return mediaType;
    }

    public String setID(SearchItem searchItem){
        String ID = searchItem.getHollywoodName() != null ? ID = searchItem.getHollywoodName() : searchItem.getHollywoodTitle();
        return ID;
    }

    public void setPersonOverview(String overview, SearchItem searchItem){
        searchItem.setOverview(overview);
    }

    public void setTrailerKey(String trailerKey, SearchItem searchItem){
        searchItem.setTrailerKey(trailerKey);
    }

    public void retrieveTrailers(SearchItem searchItem){
        String mediaType = searchItem.getMediaType();
        int databaseId = searchItem.getDatabaseId();
        Call<TrailerListings> trailers = movieAPI.getTrailers(mediaType, databaseId);
        trailers.enqueue(new Callback<TrailerListings>() {
            @Override
            public void onResponse(Call<TrailerListings> call, Response<TrailerListings> response) {
                if(response.isSuccessful() && !response.body().getTrailerListings().isEmpty()){
                    String trailerKey = response.body().getTrailerListings().get(0).getKey();
                    setTrailerKey(trailerKey, searchItem);
                }
                presenter.onPersonRetrievedSuccessful();
            }

            @Override
            public void onFailure(Call<TrailerListings> call, Throwable t) {
                presenter.onSearchResultsRetrievedFailed(t);
            }
        });
    }

    public void retrievePersonOverview(SearchItem searchItem){
        Call<SearchItem> searchListing = movieAPI.getPersonQuery(searchItem.getDatabaseId());
        searchListing.enqueue(new Callback<SearchItem>(){

            @Override
            public void onResponse(Call<SearchItem> call, Response<SearchItem> response) {
                if(response.isSuccessful()){
                    setPersonOverview(response.body().getBiography(), searchItem);
                }
                presenter.onPersonRetrievedSuccessful();
            }

            @Override
            public void onFailure(Call<SearchItem> call, Throwable t) {
                presenter.onSearchResultsRetrievedFailed(t);
            }
        });
    }

    public Uri createPosterURI(SearchItem searchItem, String mediaType){
        Uri uri;
        if (mediaType.equals(MOVIE) || mediaType.equals(TV)) {
            String posterURI = searchItem.getPosterPath();
            String posterURL = Util.getPosterURL(posterURI, POSTER_SIZE_185);
            uri = Uri.parse(posterURL);
        } else {
            String profileURI = searchItem.getProfilePath();
            String profileURL = Util.getPosterURL(profileURI, POSTER_SIZE_185);
            uri = Uri.parse(profileURL);
        }

        return uri;
    }

    public Float setVoteAverage(SearchItem searchItem){
        if (searchItem.getMediaType() == null || !searchItem.getMediaType().equals(PERSON)) {
            Float voteAverage = searchItem.getVoteAverage();
            return voteAverage;
        }
       else {
            return 0.1f;
        }
    }
}
