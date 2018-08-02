package com.johnbaek.hollywooddb.SearchPage;

import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.johnbaek.hollywooddb.Database.Favorites;
import com.johnbaek.hollywooddb.Database.FavoritesDatabase;
import com.johnbaek.hollywooddb.R;
import com.johnbaek.hollywooddb.Util;
import com.johnbaek.hollywooddb.model.SearchItem;

import java.util.ArrayList;

public class SearchListingsAdapter extends RecyclerView.Adapter<SearchListingsAdapter.SearchListingViewHolder> {
    public interface SearchListingClickListener {
        void onSearchItemClick(SearchItem searchItem);
    }

    private SearchListingsAdapter.SearchListingClickListener clickListener;
    private ArrayList<SearchItem> searchItemListings = new ArrayList<>();
    private static String MOVIE ="movie";
    private static String TV ="tv";
    private static String POSTER_SIZE_185 = "/w185";
    private static String RED = "#cc1108";
    private static String GREEN = "#0a912b";
    private static String BLUE = "#0832af";
    private Util util = new Util();

    SearchListingsAdapter(SearchListingClickListener clickListener){
        this.clickListener = clickListener;
    }


    @NonNull
    @Override
    public SearchListingsAdapter.SearchListingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_card_layout, viewGroup, false);

        return new SearchListingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SearchListingsAdapter.SearchListingViewHolder searchListingViewHolder, int i) {
        SearchItem searchItem = searchItemListings.get(i);
        searchListingViewHolder.searchItem = searchItem;
        final Favorites favorite = new Favorites();


        String mediaType = searchItem.getMediaType();
        favorite.setMediaType(mediaType);
        searchListingViewHolder.searchMediaType.setText(mediaType.toUpperCase());

        if (mediaType.equals(MOVIE) || mediaType.equals(TV)) {
            if (mediaType.equals(MOVIE)) {
                String identifier = searchItem.getHollywoodTitle();
                searchListingViewHolder.searchName.setText(identifier);
                searchListingViewHolder.searchName.setBackgroundColor(Color.parseColor(RED));
                favorite.setIdentifier(identifier);
            } else {
                String identifier = searchItem.getHollywoodName();
                searchListingViewHolder.searchName.setText(identifier);
                searchListingViewHolder.searchName.setBackgroundColor(Color.parseColor(GREEN));
                favorite.setIdentifier(identifier);
            }

            String posterURI = searchItem.getPosterPath();
            favorite.setImageURI(posterURI);
            String posterURL = searchItem.getPosterURL(posterURI, POSTER_SIZE_185);
            Uri uri = Uri.parse(posterURL);
            searchListingViewHolder.searchBackground.setImageURI(uri);

            Float voteAverage = searchItem.getVoteAverage();
            favorite.setVoteAverage(voteAverage);
            searchListingViewHolder.searchRating.setRating(Math.round(voteAverage));
        } else {
            String identifier = searchItem.getHollywoodName();
            searchListingViewHolder.searchName.setText(identifier);
            favorite.setIdentifier(identifier);
            searchListingViewHolder.searchName.setBackgroundColor(Color.parseColor(BLUE));
            String profileURI = searchItem.getProfilePath();
            favorite.setImageURI(profileURI);
            String profileURL = searchItem.getPosterURL(profileURI, POSTER_SIZE_185);
            Uri uri = Uri.parse(profileURL);
            searchListingViewHolder.searchBackground.setImageURI(uri);
            searchListingViewHolder.searchRating.setVisibility(View.GONE);
        }

        String overview = favorite.getOverview();
        favorite.setOverview(overview);

        final ToggleButton favoriteToggle = searchListingViewHolder.favoriteToggle;

        favoriteToggle.setChecked(searchItem.isFavorite());

        favoriteToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                util.onFavoriteClick(favoriteToggle, favorite);
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchItemListings.size();
    }

    public void clear() {
        final int size = searchItemListings.size();
        if (size > 0){
            searchItemListings.clear();
            notifyItemRangeRemoved(0, size);
        }
    }

    public void setSearchItemListings(ArrayList<SearchItem> searchItemListings){
        this.searchItemListings = searchItemListings;
    }

    public class SearchListingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView searchBackground;
        TextView searchMediaType;
        TextView searchName;
        RatingBar searchRating;
        SearchItem searchItem;
        ToggleButton favoriteToggle;


        public SearchListingViewHolder(@NonNull View view) {
            super(view);
            searchBackground = view.findViewById(R.id.search_background);
            searchMediaType = view.findViewById(R.id.search_media_type);
            searchName = view.findViewById(R.id.search_name);
            searchRating = view.findViewById(R.id.search_rating);
            favoriteToggle = view.findViewById(R.id.favorite_toggle);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onSearchItemClick(searchItem);
        }
    }
}
