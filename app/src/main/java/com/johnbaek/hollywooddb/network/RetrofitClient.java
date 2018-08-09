package com.johnbaek.hollywooddb.network;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.converter.gson.GsonConverterFactory;
import okhttp3.logging.HttpLoggingInterceptor;

public class RetrofitClient {
    private static retrofit2.Retrofit retrofit;
    private static final String BASE_URL = "https://api.themoviedb.org/";
    private static final String API_KEY = "71ab1b19293efe581c569c1c79d0f004";
    private static String API_KEY_STRING = "api_key";

    private static HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(chain -> {
                Request original = chain.request();
                HttpUrl httpUrl = original.url();

                HttpUrl newHttpUrl = httpUrl.newBuilder().addQueryParameter(API_KEY_STRING, API_KEY).build();

                Request.Builder requestBuilder = original.newBuilder().url(newHttpUrl);

                Request request = requestBuilder.build();
                return chain.proceed(request);
            })
//            .addInterceptor(httpLoggingInterceptor)
            .build();

    public static retrofit2.Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
        }

    public static com.johnbaek.hollywooddb.network.MovieAPI getRetrofitMovieClient() {
        return getRetrofitInstance().create(com.johnbaek.hollywooddb.network.MovieAPI.class);
    }
}
