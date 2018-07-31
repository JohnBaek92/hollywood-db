package com.johnbaek.hollywooddb.BrowsePage;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.johnbaek.hollywooddb.DetailPage.DetailActivity;
import com.johnbaek.hollywooddb.model.SearchItem;
import com.johnbaek.hollywooddb.R;

import java.util.ArrayList;

public class BrowseActivity extends Activity implements BrowsePageContract.View, BrowseMoviesAdapter.BrowseListingsClickListener {
    private BrowseMoviesAdapter adapter;
    private BrowsePageContract.Presenter presenter;
    private static String IDENTIFIER ="id";
    private static String SEARCH_ITEM = "searchItem";

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browse_layout);

        presenter = new BrowsePagePresenter(this);
        presenter.fetchTopMovies();
        presenter.fetchUpcomingMovies();
        presenter.fetchNowPlayingMovies();
    }

    public void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void displayMovies(ArrayList<SearchItem> movies, String recyclerViewID) {
        adapter = new BrowseMoviesAdapter(this);
        adapter.setMovies(movies);

        Resources res = getResources();
        int id = res.getIdentifier(recyclerViewID, IDENTIFIER, getApplicationContext().getPackageName());
        RecyclerView recyclerView = findViewById(id);
        setAdapterToRecycler(adapter, recyclerView);
    }

    public void setAdapterToRecycler(BrowseMoviesAdapter adapter, RecyclerView recyclerView) {
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(BrowseActivity.this, LinearLayoutManager.HORIZONTAL, false));
    }

    @Override
    public void onBrowseMovieClick(SearchItem movie) {
        Intent intent = new Intent(BrowseActivity.this, DetailActivity.class);
        intent.putExtra(SEARCH_ITEM, movie);
        startActivity(intent);
    }
}
