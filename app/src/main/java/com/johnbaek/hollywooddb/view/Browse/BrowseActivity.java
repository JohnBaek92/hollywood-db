package com.johnbaek.hollywooddb.view.Browse;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

    private RecyclerView browseTopMoviesRecyclerView;
    private RecyclerView browseUpcomingMoviesRecyclerView;
    private RecyclerView browseNowPlayingMoviesRecyclerView;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browse_layout);

        MovieAPI MovieAPI = retrofit.create(com.johnbaek.hollywooddb.MovieAPI.class);


        browseTopMoviesRecyclerView = findViewById(R.id.browse_top_movies_recycler);
        final BrowseMoviesAdapter browseTopMoviesAdapter = new BrowseMoviesAdapter();
        final Call<MovieListings> getTopMovies = MovieAPI.getTopMovies();

        browseUpcomingMoviesRecyclerView = findViewById(R.id.browse_upcoming_movies_recycler);
        final BrowseMoviesAdapter browseUpcomingMoviesAdapter = new BrowseMoviesAdapter();
        final Call<MovieListings> getUpcomingMovies = MovieAPI.getUpcomingMovies();

        browseNowPlayingMoviesRecyclerView = findViewById(R.id.now_playing_movies_recycler);
        final BrowseMoviesAdapter browseNowPlayingMoviesAdapter = new BrowseMoviesAdapter();
        final Call<MovieListings> getNowPlayingMovies = MovieAPI.getNowPlayingMovies();

        addMovies(getTopMovies, browseTopMoviesAdapter, browseTopMoviesRecyclerView);
        addMovies(getUpcomingMovies, browseUpcomingMoviesAdapter, browseUpcomingMoviesRecyclerView);
        addMovies(getNowPlayingMovies, browseNowPlayingMoviesAdapter, browseNowPlayingMoviesRecyclerView);
    }

    private void addMovies(Call<MovieListings> movieListings, final BrowseMoviesAdapter adapter, final RecyclerView recyclerView) {
        movieListings.enqueue(new Callback<MovieListings>() {
            @Override
            public void onResponse(Call<MovieListings> call, Response<MovieListings> response) {
                MovieListings unformattedMovies = response.body();
                ArrayList<Movie> movies = unformattedMovies.getMovies();

                adapter.setMovies(movies);

                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(BrowseActivity.this, LinearLayoutManager.HORIZONTAL, false));
            }

            @Override
            public void onFailure(Call<MovieListings> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
