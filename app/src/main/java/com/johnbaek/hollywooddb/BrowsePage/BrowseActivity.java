package com.johnbaek.hollywooddb.BrowsePage;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.johnbaek.hollywooddb.model.SearchItem;
import com.johnbaek.hollywooddb.network.MovieAPI;
import com.johnbaek.hollywooddb.R;
import com.johnbaek.hollywooddb.network.RetrofitClient;
import com.johnbaek.hollywooddb.model.SearchListings;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BrowseActivity extends Activity implements BrowsePageContract.View {
    private RecyclerView browseTopMoviesRecyclerView;
    private RecyclerView browseUpcomingMoviesRecyclerView;
    private RecyclerView browseNowPlayingMoviesRecyclerView;
    private BrowseMoviesAdapter adapter;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browse_layout);

        MovieAPI movieAPI = RetrofitClient.getRetrofitInstance().create(com.johnbaek.hollywooddb.network.MovieAPI.class);

        browseTopMoviesRecyclerView = findViewById(R.id.browse_top_movies_recycler);
        final BrowseMoviesAdapter browseTopMoviesAdapter = new BrowseMoviesAdapter();
        final Call<SearchListings> getTopMovies = movieAPI.getTopMovies();

        browseUpcomingMoviesRecyclerView = findViewById(R.id.browse_upcoming_movies_recycler);
        final BrowseMoviesAdapter browseUpcomingMoviesAdapter = new BrowseMoviesAdapter();
        final Call<SearchListings> getUpcomingMovies = movieAPI.getUpcomingMovies();

        browseNowPlayingMoviesRecyclerView = findViewById(R.id.now_playing_movies_recycler);
        final BrowseMoviesAdapter browseNowPlayingMoviesAdapter = new BrowseMoviesAdapter();
        final Call<SearchListings> getNowPlayingMovies = movieAPI.getNowPlayingMovies();

        addMovies(getTopMovies, browseTopMoviesAdapter, browseTopMoviesRecyclerView);
        addMovies(getUpcomingMovies, browseUpcomingMoviesAdapter, browseUpcomingMoviesRecyclerView);
        addMovies(getNowPlayingMovies, browseNowPlayingMoviesAdapter, browseNowPlayingMoviesRecyclerView);
    }

    public void showToastMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    private void addMovies(Call<SearchListings> SearchListings, final BrowseMoviesAdapter adapter, final RecyclerView recyclerView) {
        SearchListings.enqueue(new Callback<SearchListings>() {
            @Override
            public void onResponse(Call<SearchListings> call, Response<SearchListings> response) {
                SearchListings unformattedMovies = response.body();
                ArrayList<SearchItem> movies = unformattedMovies.getSearchItemListings();

                adapter.setMovies(movies);

                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(BrowseActivity.this, LinearLayoutManager.HORIZONTAL, false));
            }

            @Override
            public void onFailure(Call<SearchListings> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void displayMovies(ArrayList<SearchItem> movies, String recyclerViewID) {
        adapter = new BrowseMoviesAdapter();
        adapter.setMovies(movies);

        Resources res = getResources();
        int id = res.getIdentifier(recyclerViewID, "id", getApplicationContext().getPackageName());
        RecyclerView recyclerView = findViewById(id);
        setAdapterToRecycler(adapter, recyclerView);
    }

    public void setAdapterToRecycler(BrowseMoviesAdapter adapter, RecyclerView recyclerView) {
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(BrowseActivity.this, LinearLayoutManager.HORIZONTAL, false));
    }
}
