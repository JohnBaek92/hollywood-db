package com.johnbaek.hollywooddb.SearchPage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import com.johnbaek.hollywooddb.CategorizedSearchFragment.CategorizedSearchFragment;
import com.johnbaek.hollywooddb.DetailPage.DetailActivity;
import com.johnbaek.hollywooddb.R;
import com.johnbaek.hollywooddb.Util;
import com.johnbaek.hollywooddb.model.SearchItem;
import java.util.ArrayList;


public class SearchActivity extends AppCompatActivity implements SearchPageContract.View, SearchListingsAdapter.SearchListingClickListener {
    private SearchListingsAdapter adapter;
    private RecyclerView recyclerView;
    private SearchPageContract.Presenter presenter;
    private String mediaType;
    private final static String SEARCH = "search";
    private final static String SEARCH_ITEM = "searchItem";
    private final static String MEDIA_TYPE = "mediaType";
    private final static String PERSON = "person";
    private final static String MOVIE = "movie";
    private final static String TV = "tv";
    private final static String ALL = "all";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);


        String searchSubject = getIntent().getStringExtra(SEARCH);
        mediaType = getIntent().getStringExtra(MEDIA_TYPE);

        presenter = new SearchPagePresenter(this, searchSubject);
        presenter.setMediaType(mediaType);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = CategorizedSearchFragment.newInstance();
        fragmentManager.beginTransaction().add(R.id.search_fragment_container, fragment).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.fetchResults();
    }

    public void displaySearchResultText(String searchSubject) {
        String searchSubjectText = String.format(getString(R.string.search_results_for), searchSubject, formatMediaType());
        TextView searchResultText = findViewById(R.id.search_result_text);
        searchResultText.setText(searchSubjectText);
    }

    private String formatMediaType() {
        String formattedMedia = "";
        switch (mediaType) {
            case ALL:
                formattedMedia = "All Categories";
                break;
            case PERSON:
                formattedMedia = "People";
                break;
            case MOVIE:
                formattedMedia = "Movies";
                break;
            case TV:
                formattedMedia = "TV";
                break;
            default:
                break;
        }
        return formattedMedia;
    }

    public void clearData() {
        if (adapter != null) {
            adapter.clear();
        }
    }

    public void showToastMessage(String message){
        Util.showToastMessage(message, this);
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
        intent.putExtra(SEARCH_ITEM, searchItem);
        startActivity(intent);
    }
}
