package com.johnbaek.hollywooddb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.johnbaek.hollywooddb.BrowsePage.BrowseActivity;
import com.johnbaek.hollywooddb.Database.FavoritesDatabase;
import com.johnbaek.hollywooddb.SearchPage.SearchActivity;

public class MainActivity extends AppCompatActivity {
    private static String SEARCH = "SEARCH";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);

        enterBrowse();
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
