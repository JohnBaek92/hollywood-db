package com.johnbaek.hollywooddb.view.Browse;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.johnbaek.hollywooddb.MovieAPI;
import com.johnbaek.hollywooddb.R;
import com.johnbaek.hollywooddb.model.Movie;
import com.johnbaek.hollywooddb.model.MovieListings;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BrowseActivity extends Activity {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(MovieAPI.ROOT_URL)
            .client(MovieAPI.okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private RecyclerView browseMoviesRecyclerView;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browse_layout);

        MovieAPI MovieAPI = retrofit.create(com.johnbaek.hollywooddb.MovieAPI.class);

        browseMoviesRecyclerView = findViewById(R.id.browse_movies_recycler);

        final BrowseMoviesAdapter browseMoviesAdapter = new BrowseMoviesAdapter();
        
        final Call<MovieListings> getTopMovies = MovieAPI.getTopMovies();

        getTopMovies.enqueue(new Callback<MovieListings>() {
            @Override
            public void onResponse(Call<MovieListings> call, Response<MovieListings> response) {
                MovieListings unformattedTopMovies = response.body();
                ArrayList<Movie> topMovies = unformattedTopMovies.getMovies();

                for(Movie movie: topMovies) {
                    browseMoviesAdapter.movies.add(movie);
                }
                
                browseMoviesRecyclerView.setAdapter(browseMoviesAdapter);
                browseMoviesRecyclerView.setLayoutManager(new LinearLayoutManager(BrowseActivity.this, LinearLayoutManager.HORIZONTAL, false));
            }

            @Override
            public void onFailure(Call<MovieListings> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
