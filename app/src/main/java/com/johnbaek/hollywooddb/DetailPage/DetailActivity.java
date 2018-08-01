package com.johnbaek.hollywooddb.DetailPage;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.johnbaek.hollywooddb.R;
import com.johnbaek.hollywooddb.model.SearchItem;

public class DetailActivity extends Activity implements DetailPageContract.View {
    private DetailPageContract.Presenter presenter;
    private static String PERSON = "person";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);

        presenter = new DetailPagePresenter(this);

        SearchItem searchItem = (SearchItem) getIntent().getSerializableExtra("searchItem");

        presenter.setDetailSubject(searchItem);
    }

    public void displayDetailView(){
        TextView detailMediaTypeView = findViewById(R.id.detail_media_type);
        TextView detailIDView = findViewById(R.id.detail_id);
        SimpleDraweeView detailImageView = findViewById(R.id.detail_image);
        RatingBar detailRatingView = findViewById(R.id.detail_rating);
        TextView detailOverviewView = findViewById(R.id.detail_overview);

        String mediaType = presenter.getMediaType();

        detailMediaTypeView.setText(mediaType.toUpperCase());

        String overview = presenter.getOverview();
        detailOverviewView.setText(overview);

        String ID = presenter.getID();
        detailIDView.setText(ID);

        Uri uri = presenter.getPosterURI();
        detailImageView.setImageURI(uri);

        if (!mediaType.equals(PERSON)) {
            Integer voteAverage = presenter.getVoteAverage();
            detailRatingView.setRating(voteAverage);
        } else {
            detailRatingView.setVisibility(View.GONE);
        }
    }
}
