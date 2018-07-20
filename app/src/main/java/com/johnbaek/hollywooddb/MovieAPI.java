package com.johnbaek.hollywooddb;

import com.johnbaek.hollywooddb.model.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieAPI {
    String ROOT_URL = "https://api.themoviedb.org";
    String API_KEY = "71ab1b19293efe581c569c1c79d0f004";

    @GET("/3/movie/top_rated")
    Call<List<Movie>> getTopMovies();
}
