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

import com.johnbaek.hollywooddb.R;
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
    public void onBindViewHolder(@NonNull SearchListingsAdapter.SearchListingViewHolder searchListingViewHolder, int i) {
        SearchItem searchItem = searchItemListings.get(i);
        searchListingViewHolder.searchItem = searchItem;

        String mediaType = searchItem.getMediaType();
        searchListingViewHolder.searchMediaType.setText(mediaType.toUpperCase());

        if (mediaType.equals(MOVIE) || mediaType.equals(TV)) {
            if (mediaType.equals(MOVIE)) {
                searchListingViewHolder.searchName.setText(searchItem.getHollywoodTitle());
                searchListingViewHolder.searchName.setBackgroundColor(Color.parseColor(RED));
            } else {
                searchListingViewHolder.searchName.setText(searchItem.getHollywoodName());
                searchListingViewHolder.searchName.setBackgroundColor(Color.parseColor(GREEN));
            }

            String posterURI = searchItem.getPosterPath();
            String posterURL = searchItem.getPosterURL(posterURI, POSTER_SIZE_185);
            Uri uri = Uri.parse(posterURL);
            searchListingViewHolder.searchBackground.setImageURI(uri);

            Float voteAverage = searchItem.getVoteAverage();
            searchListingViewHolder.searchRating.setRating(Math.round(voteAverage));
        } else {
            searchListingViewHolder.searchName.setText(searchItem.getHollywoodName());
            searchListingViewHolder.searchName.setBackgroundColor(Color.parseColor(BLUE));
            String profileURI = searchItem.getProfilePath();
            String profileURL = searchItem.getPosterURL(profileURI, POSTER_SIZE_185);
            Uri uri = Uri.parse(profileURL);
            searchListingViewHolder.searchBackground.setImageURI(uri);
            searchListingViewHolder.searchRating.setVisibility(View.GONE);
        }
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


        public SearchListingViewHolder(@NonNull View view) {
            super(view);
            searchBackground = view.findViewById(R.id.search_background);
            searchMediaType = view.findViewById(R.id.search_media_type);
            searchName = view.findViewById(R.id.search_name);
            searchRating = view.findViewById(R.id.search_rating);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onSearchItemClick(searchItem);
        }
    }
}
