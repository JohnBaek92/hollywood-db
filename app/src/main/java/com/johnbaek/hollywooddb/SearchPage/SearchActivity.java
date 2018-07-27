package com.johnbaek.hollywooddb.SearchPage;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class SearchActivity extends Activity implements SearchPageContract.View {
    private SearchListingsAdapter adapter;
    private RecyclerView recyclerView;
    private SearchPageContract.Presenter presenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);

        presenter = new SearchPagePresenter(this);

        String searchSubject = getIntent().getStringExtra("SEARCH");
        presenter.setSearchSubject(searchSubject);

        presenter.fetchResults(searchSubject);
    }

    public void displaySearchResultText() {
        String searchSubjectText = String.format("Search Results for \"%s\"", presenter.getSearchSubject());
        TextView searchResultText = findViewById(R.id.search_result_text);
        searchResultText.setText(searchSubjectText);
    }

    public void showToastMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    public void clearData() {
        if (adapter != null) {
            adapter.clear();
        }
    }

    public void displayResults(ArrayList<SearchItem> searchItems) {
        recyclerView = findViewById(R.id.search_recycler_view);
        adapter = new SearchListingsAdapter();
        adapter.setSearchItemListings(searchItems);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.VERTICAL, false));
    }
}
