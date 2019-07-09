package com.johnbaek.hollywooddb.CategorizedSearchFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import com.johnbaek.hollywooddb.R;
import com.johnbaek.hollywooddb.SearchPage.SearchActivity;

public class CategorizedSearchFragment extends Fragment implements View.OnClickListener, SearchView.OnQueryTextListener {

    ConstraintLayout searchSuggestions;
    TextView searchPeopleView;
    TextView searchMoviesView;
    TextView searchTvView;
    TextView searchAllView;
    SearchView searchView;
    private static String MEDIATYPE = "mediaType";
    private static String PERSON = "person";
    private static String MOVIE = "movie";
    private static String TV = "tv";
    private static String SEARCH = "search";
    private static String ALL = "all";

    public static CategorizedSearchFragment newInstance() {
        return new CategorizedSearchFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.categorized_search_fragment, container, false);
        searchSuggestions = view.findViewById(R.id.search_suggestions);
        searchView = view.findViewById(R.id.category_search_fragment_search_view);
        searchPeopleView = view.findViewById(R.id.category_search_fragment_search_people);
        searchMoviesView = view.findViewById(R.id.category_search_fragment_search_movies);
        searchTvView = view.findViewById(R.id.category_search_fragment_search_tv);
        searchAllView = view.findViewById(R.id.category_search_fragment_search_all);

        searchView.setOnQueryTextListener(this);
        searchView.setOnClickListener(this);
        searchPeopleView.setOnClickListener(this);
        searchMoviesView.setOnClickListener(this);
        searchTvView.setOnClickListener(this);
        searchAllView.setOnClickListener(this);
        searchSuggestions.setVisibility(View.GONE);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onClick(View view) {
        searchView.setIconified(false);
        switch (view.getId()) {
            case R.id.category_search_fragment_search_people:
                createSearchIntent(PERSON);
                break;
            case R.id.category_search_fragment_search_movies:
                createSearchIntent(MOVIE);
                break;
            case R.id.category_search_fragment_search_tv:
                createSearchIntent(TV);
                break;
            case R.id.category_search_fragment_search_all:
                createSearchIntent(ALL);
                break;
            default:
                break;
        }
    }

    public void createSearchIntent(String mediaType) {
        Intent intent = new Intent(getContext(), SearchActivity.class);
        intent.putExtra(SEARCH, searchView.getQuery().toString());
        intent.putExtra(MEDIATYPE, mediaType);
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String search) {
        Intent intent = new Intent(getContext(), SearchActivity.class);
        intent.putExtra(SEARCH, search);
        intent.putExtra(MEDIATYPE, ALL);
        getContext().startActivity(intent);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String search) {
        if (!search.isEmpty()) {
            searchSuggestions.setVisibility(View.VISIBLE);
            searchPeopleView.setText(getString(R.string.two_strings_with_space, search, getString(R.string.search_people)));
            searchMoviesView.setText(getString(R.string.two_strings_with_space, search, getString(R.string.search_movies)));
            searchTvView.setText(getString(R.string.two_strings_with_space, search, getString(R.string.search_tv)));
            searchAllView.setText(getString(R.string.two_strings_with_space, search, getString(R.string.search_all)));
        } else {
            searchSuggestions.setVisibility(View.GONE);
        }

        return false;
    }
}
