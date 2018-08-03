package com.johnbaek.hollywooddb.SearchPage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.johnbaek.hollywooddb.DetailPage.DetailActivity;
import com.johnbaek.hollywooddb.R;
import com.johnbaek.hollywooddb.model.SearchItem;
import com.johnbaek.hollywooddb.model.SearchListings;
import com.johnbaek.hollywooddb.network.MovieAPI;
import com.johnbaek.hollywooddb.network.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends Activity implements SearchPageContract.View, SearchListingsAdapter.SearchListingClickListener {
    private SearchListingsAdapter adapter;
    private RecyclerView recyclerView;
    private SearchPageContract.Presenter presenter;
    private static String SEARCH = "search";
    private static String SEARCHITEM = "searchItem";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);

        presenter = new SearchPagePresenter(this);

        String searchSubject = getIntent().getStringExtra(SEARCH);
        presenter.setSearchSubject(searchSubject);

        presenter.fetchResults(searchSubject);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.fetchResults(presenter.getSearchSubject());
    }

    public void displaySearchResultText(String searchSubject) {
        String searchSubjectText = String.format("Search Results for \"%s\"", searchSubject);
        TextView searchResultText = findViewById(R.id.search_result_text);
        searchResultText.setText(searchSubjectText);
    }

    public void clearData() {
        if (adapter != null) {
            adapter.clear();
        }
    }

    public void displayResults(ArrayList<SearchItem> searchItems) {
        recyclerView = findViewById(R.id.search_recycler_view);
        adapter = new SearchListingsAdapter(this);
        adapter.setSearchItemListings(searchItems);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onSearchItemClick(SearchItem searchItem) {
        Intent intent = new Intent(SearchActivity.this, DetailActivity.class);
        intent.putExtra(SEARCHITEM, searchItem);
        startActivity(intent);
    }
}
