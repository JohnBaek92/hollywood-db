package com.johnbaek.hollywooddb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.johnbaek.hollywooddb.BrowsePage.BrowseActivity;
import com.johnbaek.hollywooddb.CategorizedSearchFragment.CategorizedSearchFragment;
import com.johnbaek.hollywooddb.FavoritesPage.FavoritesActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterBrowse();
        enterFavorites();

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = CategorizedSearchFragment.newInstance();
        fragmentManager.beginTransaction().add(R.id.search_fragment_container, fragment).commit();
    }

    private void enterBrowse() {
        TextView browseLink = findViewById(R.id.browse_link);
        browseLink.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, BrowseActivity.class)));
    }

    private void enterFavorites() {
        TextView favoritesLink = findViewById(R.id.my_favorites);
        favoritesLink.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, FavoritesActivity.class)));
    }
}
