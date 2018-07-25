package com.johnbaek.hollywooddb.view.Search;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.johnbaek.hollywooddb.R;

public class SearchActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);

        String searchSubject = getIntent().getStringExtra("SEARCH");
        searchSubject = String.format("SearchItem Results for \"%\"", searchSubject);
        TextView searchResultText = findViewById(R.id.search_result_text);
        searchResultText.setText(searchSubject);
    }
}
