package com.johnbaek.hollywooddb.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.johnbaek.hollywooddb.MovieAPI;
import com.johnbaek.hollywooddb.R;
import com.johnbaek.hollywooddb.model.Movie;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BrowseActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browse_layout);

        final String API_KEY = "71ab1b19293efe581c569c1c79d0f004";

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
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MovieAPI.ROOT_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieAPI MovieAPI = retrofit.create(com.johnbaek.hollywooddb.MovieAPI.class);

        final Call<List<Movie>> getTopMovies = MovieAPI.getTopMovies();
        System.out.println("movies" + getTopMovies);

        getTopMovies.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                Gson gson = new Gson();
                Type collectionType = new TypeToken<Collection<Movie>>(){}.getType();
//                Collection<Movie> enums = gson.fromJson(getTopMovies, collectionType);
                List<Movie> topMovies = response.body();
                System.out.println("response" + response.body());

                for(Movie movie: topMovies) {
                    Log.d("title", movie.getTitle());
                }

            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
