package com.johnbaek.hollywooddb;

import com.johnbaek.hollywooddb.model.MovieListings;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieAPI {
    String ROOT_URL = "https://api.themoviedb.org";
    String API_KEY = "71ab1b19293efe581c569c1c79d0f004";

    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    HttpUrl httpUrl = original.url();

                    HttpUrl newHttpUrl = httpUrl.newBuilder().addQueryParameter("api_key", API_KEY).build();

                    Request.Builder requestBuilder = original.newBuilder().url(newHttpUrl);

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            })
            .build();

    @GET("/3/movie/top_rated")
    Call<MovieListings> getTopMovies();
}
