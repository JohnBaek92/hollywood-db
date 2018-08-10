package com.johnbaek.hollywooddb.BrowsePage;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.johnbaek.hollywooddb.CategorizedSearchFragment.CategorizedSearchFragment;
import com.johnbaek.hollywooddb.DetailPage.DetailActivity;
import com.johnbaek.hollywooddb.Util;
import com.johnbaek.hollywooddb.model.SearchItem;
import com.johnbaek.hollywooddb.R;

import java.util.ArrayList;

public class BrowseActivity extends AppCompatActivity implements BrowsePageContract.View, BrowseMoviesAdapter.BrowseListingsClickListener, CategorizedSearchFragment.OnFragmentInteractionListener {
    private BrowseMoviesAdapter adapter;
    private BrowsePageContract.Presenter presenter;
    private static String IDENTIFIER ="id";
    private static String SEARCH_ITEM = "searchItem";

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browse_layout);

        presenter = new BrowsePagePresenter(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = CategorizedSearchFragment.newInstance();
        fragmentManager.beginTransaction().add(R.id.search_fragment_container, fragment).commit();
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

    public void showToastMessage(String message){
        Util.showToastMessage(message, this);
    }

    @Override
    public void onBrowseMovieClick(SearchItem movie) {
        Intent intent = new Intent(BrowseActivity.this, DetailActivity.class);
        intent.putExtra(SEARCH_ITEM, movie);
        startActivity(intent);
    }
}
