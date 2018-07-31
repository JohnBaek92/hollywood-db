package com.johnbaek.hollywooddb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.johnbaek.hollywooddb.BrowsePage.BrowseActivity;
import com.johnbaek.hollywooddb.SearchPage.SearchActivity;

public class MainActivity extends AppCompatActivity {
    private static String SEARCH = "SEARCH";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);

        enterSearch();
        enterBrowse();
    }

    public void enterSearch(){
        final SearchView searchView = findViewById(R.id.landing_page_search_bar);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setIconified(false);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String search) {
                if (!search.isEmpty()) {
                    Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                    intent.putExtra(SEARCH, search);
                    startActivity(intent);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    public void enterBrowse(){
        TextView browseLink = findViewById(R.id.browse_link);
        browseLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BrowseActivity.class));
            }
        });
    }
}
