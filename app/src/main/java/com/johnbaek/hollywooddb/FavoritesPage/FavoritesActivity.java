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
import com.johnbaek.hollywooddb.Database.DatabaseInitializer;
import com.johnbaek.hollywooddb.Database.Favorites;
import com.johnbaek.hollywooddb.DetailPage.DetailActivity;
import com.johnbaek.hollywooddb.R;
import com.johnbaek.hollywooddb.Util;
import com.johnbaek.hollywooddb.model.SearchItem;
import com.johnbaek.hollywooddb.network.MovieAPI;

import java.util.List;

public class FavoritesActivity extends AppCompatActivity implements FavoritesAdapter.FavoritesClickListener, CategorizedSearchFragment.OnFragmentInteractionListener{
    private RecyclerView recyclerView;
    private FavoritesAdapter adapter;
    private final static String SEARCHITEM = "searchItem";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorites_page_layout);

        fetchFavorites();

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = CategorizedSearchFragment.newInstance();
        fragmentManager.beginTransaction().add(R.id.search_fragment_container, fragment).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchFavorites();
    }

    public void fetchFavorites(){
        new DatabaseInitializer.AsyncGetFavorites(favorites -> {
            displayResults(favorites);
        }).execute();
    }

    public void clearData() {
        if (adapter != null) {
            adapter.clear();
        }
    }


    public void displayResults(List<Favorites> favorites){
        clearData();
        if(favorites.size() >= 1) {
            recyclerView = findViewById(R.id.favorites_recycler_view);
            adapter = new FavoritesAdapter(this);
            adapter.setFavorites(favorites);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(FavoritesActivity.this, LinearLayoutManager.VERTICAL, false));
        } else {
            Util.showToastMessage("No Results");
        }
    }

    @Override
    public void onFavoriteItemClick(SearchItem favorite) {
        Intent intent = new Intent(FavoritesActivity.this, DetailActivity.class);
        intent.putExtra(SEARCHITEM, favorite);
        startActivity(intent);
    }
}
