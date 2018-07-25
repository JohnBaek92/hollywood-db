package com.johnbaek.hollywooddb.view.Search;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        String posterURL =
    }

    @Override
    public int getItemCount() {
        return searchItemListings.size();
    }

    public void setSearchItemListings(ArrayList<SearchItem> searchItemListings){
        this.searchItemListings = searchItemListings;
    }

    public class SearchListingViewHolder extends RecyclerView.ViewHolder {
        TextView searchMediaType;
        TextView searchName;
        RatingBar searchRating;

        public SearchListingViewHolder(@NonNull View view) {
            super(view);
            searchMediaType = view.findViewById(R.id.media_type);
            searchName = view.findViewById(R.id.search_name);
            searchRating = view.findViewById(R.id.search_rating);
        }
    }
}
