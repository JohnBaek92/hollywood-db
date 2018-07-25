package com.johnbaek.hollywooddb.view.Search;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
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
    ArrayList<SearchItem> searchItemListings = new ArrayList<>();

    @NonNull
    @Override
    public SearchListingsAdapter.SearchListingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_card_layout, viewGroup, false);

        return new SearchListingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchListingsAdapter.SearchListingViewHolder searchListingViewHolder, int i) {
        SearchItem searchItem = searchItemListings.get(i);

        String posterURI = searchItem.getPosterPath();
        String posterURL = searchItem.getPosterURL(posterURI, "/w185");
        Uri uri = Uri.parse(posterURL);
        searchListingViewHolder.searchBackground.setImageURI(uri);

        String mediaType = searchItem.getMediaType();
        searchListingViewHolder.searchMediaType.setText(mediaType);

        if (mediaType.equals("movie")) {
            searchListingViewHolder.searchName.setText(searchItem.getHollywoodTitle());
        } else {
            searchListingViewHolder.searchName.setText(searchItem.getHollywoodName());
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

    public class SearchListingViewHolder extends RecyclerView.ViewHolder {
        ImageView searchBackground;
        TextView searchMediaType;
        TextView searchName;
        RatingBar searchRating;


        public SearchListingViewHolder(@NonNull View view) {
            super(view);
            searchBackground = view.findViewById(R.id.search_background);
            searchMediaType = view.findViewById(R.id.search_media_type);
            searchName = view.findViewById(R.id.search_name);
            searchRating = view.findViewById(R.id.search_rating);
        }
    }
}
