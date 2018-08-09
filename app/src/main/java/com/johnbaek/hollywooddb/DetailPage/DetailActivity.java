package com.johnbaek.hollywooddb.DetailPage;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.johnbaek.hollywooddb.Database.Favorites;
import com.johnbaek.hollywooddb.R;
import com.johnbaek.hollywooddb.Util;
import com.johnbaek.hollywooddb.model.SearchItem;
import com.johnbaek.hollywooddb.network.YouTubeClient;

public class DetailActivity extends YouTubeBaseActivity implements DetailPageContract.View {
    private DetailPageContract.Presenter presenter;
    private static String PERSON = "person";
    private static String SEARCHITEM = "searchItem";
    private YouTubePlayer.OnInitializedListener onInitializedListener;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);


        SearchItem searchItem = (SearchItem) getIntent().getSerializableExtra(SEARCHITEM);

        presenter = new DetailPagePresenter(this);
        presenter.setDetailSubject(searchItem);
    }

    public void showToastMessage(String throwable){
        Util.showToastMessage(throwable, this);
    }

    public void displayDetailView(){
        TextView detailMediaTypeView = findViewById(R.id.detail_media_type);
        TextView detailIDView = findViewById(R.id.detail_id);
        SimpleDraweeView detailImageView = findViewById(R.id.detail_image);
        RatingBar detailRatingView = findViewById(R.id.detail_rating);
        TextView detailOverviewView = findViewById(R.id.detail_overview);
        ToggleButton detailToggleFavorite = findViewById(R.id.detail_favorite_toggle);
        YouTubePlayerView youTubePlayerView = findViewById(R.id.youTubePlayer);

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    youTubePlayer.cueVideo(presenter.getTrailer());
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        if (presenter.getTrailer() != null){
            youTubePlayerView.initialize(YouTubeClient.getApiKey(), onInitializedListener);
        } else {
            youTubePlayerView.setVisibility(View.GONE);
        }

        String mediaType = presenter.getMediaType();
        detailMediaTypeView.setText(mediaType.toUpperCase());

        String overview = presenter.getOverview();
        detailOverviewView.setText(overview);

        String ID = presenter.getID();
        detailIDView.setText(ID);

        Uri uri = presenter.getPosterURI();
        detailImageView.setImageURI(uri);

        Float voteAverage = null;

        if (!mediaType.equals(PERSON)) {
            voteAverage = presenter.getVoteAverage();
            detailRatingView.setRating(Math.round(voteAverage/2));
        } else {
//            youTubePlayerView.setVisibility(View.GONE);
            detailRatingView.setVisibility(View.GONE);
        }

        presenter.setFavoriteStatus(detailToggleFavorite);

        final Favorites favorite = new Favorites(ID, mediaType, presenter.getPosterURL(), voteAverage, overview, presenter.getDatabaseId());

        detailToggleFavorite.setOnClickListener(view -> Util.onFavoriteClick(detailToggleFavorite, favorite));
    }
}
