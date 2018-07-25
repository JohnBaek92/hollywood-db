package com.johnbaek.hollywooddb.view.Search;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.johnbaek.hollywooddb.R;
import com.johnbaek.hollywooddb.model.SearchItem;
import com.johnbaek.hollywooddb.model.SearchListings;
import com.johnbaek.hollywooddb.network.MovieAPI;
import com.johnbaek.hollywooddb.network.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends Activity {
    private SearchListingsAdapter adapter;
    private RecyclerView recyclerView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);

        String searchSubject = getIntent().getStringExtra("SEARCH");
        String searchSubjectText = String.format("Search Results for \"%s\"", searchSubject);
        TextView searchResultText = findViewById(R.id.search_result_text);
        searchResultText.setText(searchSubjectText);

        fetchResults(searchSubject);
    }

    public void fetchResults(final String searchSubject) {
        MovieAPI MovieAPI = RetrofitClient.getRetrofitInstance().create(com.johnbaek.hollywooddb.network.MovieAPI.class);

        if (adapter != null) {
            adapter.clear();
        }
        Call<SearchListings> searchListings = MovieAPI.getSearchAll(searchSubject);
        searchListings.enqueue(new Callback<SearchListings>() {
            @Override
            public void onResponse(Call<SearchListings> call, Response<SearchListings> response) {
                SearchListings unformattedResults = response.body();
                ArrayList<SearchItem> searchItems = unformattedResults.getSearchItemListings();
                if( searchItems.size() >= 1) {
                    displayResults(searchItems);
                } else {
                    Toast.makeText(getApplicationContext(), "No Results", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<SearchListings> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void displayResults(ArrayList<SearchItem> searchItems) {
        adapter = new SearchListingsAdapter();
        adapter.setSearchItemListings(searchItems);
        RecyclerView recyclerView = findViewById(R.id.search_recycler_view);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.VERTICAL, false));
    }
}
