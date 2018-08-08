package com.johnbaek.hollywooddb.network;

import com.johnbaek.hollywooddb.model.SearchItem;
import com.johnbaek.hollywooddb.model.SearchListings;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieAPI {
    String QUERY = "query";

    @GET("/3/movie/top_rated")
    Call<SearchListings> getTopMovies();

    @GET("/3/movie/upcoming")
    Call<SearchListings> getUpcomingMovies();

    @GET("/3/movie/now_playing")
    Call<SearchListings> getNowPlayingMovies();

    @GET("/3/search/multi")
    Call<SearchListings> getSearchAll(@Query(QUERY) String query);

    @GET("/3/search/tv")
    Call<SearchListings> getTvQueries(@Query(QUERY) String query);

    @GET("/3/search/movie")
    Call<SearchListings> getMovieQueries(@Query(QUERY) String query);

    @GET("/3/search/person")
    Call<SearchListings> getPeopleQueries(@Query(QUERY) String query);

    @GET("/3/person/{id}")
    Call<SearchItem> getPersonQuery(@Path("id") int id);
}
