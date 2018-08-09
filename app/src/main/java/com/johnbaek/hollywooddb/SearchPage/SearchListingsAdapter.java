package com.johnbaek.hollywooddb.SearchPage;

import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.johnbaek.hollywooddb.Database.Favorites;
import com.johnbaek.hollywooddb.R;
import com.johnbaek.hollywooddb.Util;
import com.johnbaek.hollywooddb.model.SearchItem;

import java.util.ArrayList;

public class SearchListingsAdapter extends RecyclerView.Adapter<SearchListingsAdapter.SearchListingViewHolder> {
    private SearchPageContract.Presenter presenter;

    public interface SearchListingClickListener {
        void onSearchItemClick(SearchItem searchItem);
    }

    private SearchListingsAdapter.SearchListingClickListener clickListener;
    private ArrayList<SearchItem> searchItemListings = new ArrayList<>();
    private static String MOVIE ="movie";
    private static String TV ="tv";
    private static String PERSON = "person";
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

        String mediaType = searchItem.getMediaType() != null ? searchItem.getMediaType() : PERSON;
        searchListingViewHolder.searchMediaType.setText(mediaType.toUpperCase());

        String identifier;
        Uri uri;
        Integer voteAverage = null;
        String profileURI = null;
        String posterURI = null;
        Favorites favorite;

        String overview = searchItem.getOverview();

        if (mediaType.equals(MOVIE) || mediaType.equals(TV)) {
            if (mediaType.equals(MOVIE)) {
                identifier = searchItem.getHollywoodTitle();
                searchListingViewHolder.searchName.setText(identifier);
                searchListingViewHolder.searchName.setBackgroundColor(Color.parseColor(RED));
            } else {
                identifier = searchItem.getHollywoodName();
                searchListingViewHolder.searchName.setText(identifier);
                searchListingViewHolder.searchName.setBackgroundColor(Color.parseColor(GREEN));
            }

            posterURI = searchItem.getPosterPath();
            String posterURL = Util.getPosterURL(posterURI, POSTER_SIZE_185);
            uri = Uri.parse(posterURL);
            searchListingViewHolder.searchBackground.setImageURI(uri);

            voteAverage = Math.round(searchItem.getVoteAverage());
            searchListingViewHolder.searchRating.setRating(Math.round(voteAverage/2));

            favorite = new Favorites(identifier, mediaType, posterURI, searchItem.getVoteAverage(), overview, searchItem.getDatabaseId());
        } else {
            identifier = searchItem.getHollywoodName();
            searchListingViewHolder.searchName.setText(identifier);
            searchListingViewHolder.searchName.setBackgroundColor(Color.parseColor(BLUE));
            profileURI = searchItem.getProfilePath();
            String profileURL = Util.getPosterURL(profileURI, POSTER_SIZE_185);
            uri = Uri.parse(profileURL);
            searchListingViewHolder.searchBackground.setImageURI(uri);
            searchListingViewHolder.searchRating.setVisibility(View.GONE);

            favorite = new Favorites(identifier, mediaType, profileURI, null, overview, searchItem.getDatabaseId());
        }

        final ToggleButton favoriteToggle = searchListingViewHolder.favoriteToggle;


        favoriteToggle.setChecked(searchItem.isFavorite());

        favoriteToggle.setOnClickListener(view -> Util.onFavoriteClick(favoriteToggle, favorite));
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
