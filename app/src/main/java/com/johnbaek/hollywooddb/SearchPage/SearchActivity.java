package com.johnbaek.hollywooddb.SearchPage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import com.johnbaek.hollywooddb.CategorizedSearchFragment.CategorizedSearchFragment;
import com.johnbaek.hollywooddb.DetailPage.DetailActivity;
import com.johnbaek.hollywooddb.R;
import com.johnbaek.hollywooddb.model.SearchItem;
import java.util.ArrayList;


public class SearchActivity extends AppCompatActivity implements SearchPageContract.View, SearchListingsAdapter.SearchListingClickListener, CategorizedSearchFragment.OnFragmentInteractionListener {
    private SearchListingsAdapter adapter;
    private RecyclerView recyclerView;
    private SearchPageContract.Presenter presenter;
    private final static String SEARCH = "search";
    private final static String SEARCHITEM = "searchItem";
    private final static String MEDIATYPE = "mediaType";
    private final static String PERSON = "person";
    private final static String MOVIE = "movie";
    private final static String TV = "tv";
    private final static String ALL = "all";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);

        presenter = new SearchPagePresenter(this);

        String searchSubject = getIntent().getStringExtra(SEARCH);
        String mediaType = getIntent().getStringExtra(MEDIATYPE);

        presenter.setSearchSubject(searchSubject);
        presenter.setMediaType(mediaType);

        presenter.fetchResults();

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = CategorizedSearchFragment.newInstance();
        fragmentManager.beginTransaction().add(R.id.search_fragment_container, fragment).commit();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(adapter != null) {
            presenter.fetchResults();
        }
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
