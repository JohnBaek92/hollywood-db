package com.johnbaek.hollywooddb.BrowsePage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.johnbaek.hollywooddb.CategorizedSearchFragment.CategorizedSearchFragment;
import com.johnbaek.hollywooddb.DetailPage.DetailActivity;
import com.johnbaek.hollywooddb.R;
import com.johnbaek.hollywooddb.Util;
import com.johnbaek.hollywooddb.model.SearchItem;

import java.util.ArrayList;

public class BrowseActivity extends AppCompatActivity implements BrowsePageContract.View, BrowseMoviesAdapter.BrowseListingsClickListener {
    private BrowseMoviesAdapter adapter;
    private BrowsePageContract.Presenter presenter;
    private static final String SEARCH_ITEM = "searchItem";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browse_layout);

        presenter = new BrowsePagePresenter(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = CategorizedSearchFragment.newInstance();
        fragmentManager.beginTransaction().add(R.id.search_fragment_container, fragment).commit();
    }

    public void displayMovies(ArrayList<SearchItem> movies, int recyclerViewID) {
        adapter = new BrowseMoviesAdapter(this, movies);

        RecyclerView recyclerView = findViewById(recyclerViewID);
        setAdapterToRecycler(adapter, recyclerView);
    }

    private void setAdapterToRecycler(BrowseMoviesAdapter adapter, RecyclerView recyclerView) {
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(BrowseActivity.this, LinearLayoutManager.HORIZONTAL, false));
    }

    public void showToastMessage(String message) {
        Util.showToastMessage(message, this);
    }

    @Override
    public void onBrowseMovieClick(SearchItem movie) {
        Intent intent = new Intent(BrowseActivity.this, DetailActivity.class);
        intent.putExtra(SEARCH_ITEM, movie);
        startActivity(intent);
    }
}
