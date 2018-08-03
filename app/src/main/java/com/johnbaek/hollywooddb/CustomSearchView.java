package com.johnbaek.hollywooddb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.SearchView;

import com.johnbaek.hollywooddb.SearchPage.SearchActivity;

public class CustomSearchView extends SearchView {
    private static String SEARCH = "search";

    public CustomSearchView(Context context) {
        super(context);
        enterSearch();
    }

    public CustomSearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        enterSearch();
    }

    public CustomSearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        enterSearch();
    }

    public CustomSearchView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        enterSearch();
    }


    public void enterSearch(){
        final SearchView searchView = this;
        searchView.setOnClickListener(v -> searchView.setIconified(false));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String search) {
                if (!search.isEmpty()) {
                    Intent intent = new Intent(getContext() , SearchActivity.class);
                    intent.putExtra(SEARCH, search);
                    getContext().startActivity(intent);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String search) {
                return false;
            }
        });
    }
}
