package com.johnbaek.hollywooddb.FavoritesPage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.johnbaek.hollywooddb.CategorizedSearchFragment.CategorizedSearchFragment;
import com.johnbaek.hollywooddb.Database.Favorites;
import com.johnbaek.hollywooddb.DetailPage.DetailActivity;
import com.johnbaek.hollywooddb.R;
import com.johnbaek.hollywooddb.Util;
import com.johnbaek.hollywooddb.model.SearchItem;

import java.util.List;

public class FavoritesActivity extends AppCompatActivity implements FavoritesAdapter.FavoritesClickListener, FavoritesPageContract.View {
    private RecyclerView recyclerView;
    private FavoritesAdapter adapter;
    private final static String SEARCH_ITEM = "searchItem";
    private static String NO_FAVORITES = "No Favorites";
    private FavoritesPageContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorites_page_layout);

        presenter = new FavoritesPagePresenter(this);

        adapter = new FavoritesAdapter(this);

        recyclerView = findViewById(R.id.favorites_recycler_view);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(FavoritesActivity.this, LinearLayoutManager.VERTICAL, false));

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = CategorizedSearchFragment.newInstance();
        fragmentManager.beginTransaction().add(R.id.search_fragment_container, fragment).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.fetchFavorites();
    }

    private void clearData() {
        if (adapter != null) {
            adapter.clear();
        }
    }

    public void showToastMessage(String message) {
        Util.showToastMessage(message, this);
    }


    public void displayResults(List<Favorites> favorites) {
        clearData();
        if (favorites.size() >= 1) {
            adapter.setFavorites(favorites);
            adapter.notifyDataSetChanged();
        } else {
            showToastMessage(NO_FAVORITES);
        }
    }

    @Override
    public void onFavoriteItemClick(SearchItem favorite) {
        Intent intent = new Intent(FavoritesActivity.this, DetailActivity.class);
        intent.putExtra(SEARCH_ITEM, favorite);
        startActivity(intent);
    }
}
