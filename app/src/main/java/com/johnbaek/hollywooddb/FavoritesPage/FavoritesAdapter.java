package com.johnbaek.hollywooddb.FavoritesPage;

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
import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder> {
    public interface FavoritesClickListener {
        void onFavoriteItemClick(SearchItem favorite);
    }

    private static String W185 = "/w185";
    private static String MOVIE = "movie";
    private static String TV ="tv";

    private FavoritesAdapter.FavoritesClickListener clickListener;
    private List<Favorites> favorites = new ArrayList<>();

    FavoritesAdapter(FavoritesClickListener clickListener){
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public FavoritesAdapter.FavoritesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.favorites_card_layout, viewGroup, false);

        return new FavoritesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesAdapter.FavoritesViewHolder favoritesViewHolder, int i) {
        Favorites favorite = favorites.get(i);

        String identifier = favorite.getIdentifier();
        String overview = favorite.getOverview();
        Float voteAverage = null;
        SearchItem favoriteItem;

        ToggleButton favoriteToggle = favoritesViewHolder.favoriteToggle;
        favoriteToggle.setChecked(true);

        favoritesViewHolder.favoriteName.setText(identifier);

        String mediaType = favorite.getMediaType();
        favoritesViewHolder.favoriteMediaType.setText(mediaType.toUpperCase());

        String imageURI = favorite.getImageURI();
        String imageURL = Util.getPosterURL(imageURI, W185);
        Uri uri = Uri.parse(imageURL);

        favoritesViewHolder.favoriteBackground.setImageURI(uri);

        final Favorites favoriteDBCheck = new Favorites(identifier, mediaType, imageURI, favorite.getVoteAverage(), overview, favorite.getDatabaseId());


        if(mediaType.equals(MOVIE) || mediaType.equals(TV)) {
            favoritesViewHolder.favoriteRating.setRating(Math.round(favorite.getVoteAverage()/2));
            favoriteItem = new SearchItem(mediaType, favorite.getVoteAverage()+0.1f, favorite.getImageURI(), overview, identifier, null, null, favorite.getDatabaseId());
        } else {
            favoritesViewHolder.favoriteRating.setVisibility(View.GONE);
            favoriteItem = new SearchItem(mediaType, null, null, overview, null, identifier, favorite.getImageURI(), favorite.getDatabaseId());
        }

        favoritesViewHolder.favoriteItem = favoriteItem;

        favoritesViewHolder.favoriteToggle.setOnClickListener(view -> toggleFavoriteAndRemove(favoriteToggle, favoriteDBCheck, i));
    }

    public void toggleFavoriteAndRemove(ToggleButton favoriteToggle, Favorites favorite, int i){
        Util.onFavoriteClick(favoriteToggle, favorite);
        favorites.remove(i);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return favorites.size();
    }

    public void setFavorites(List<Favorites> favorites){
        this.favorites = favorites;
    }

    public void clear() {
        final int size = favorites.size();
        if (size > 0){
            favorites.clear();
            notifyItemRangeRemoved(0, size);
        }
    }


    public class FavoritesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView favoriteBackground;
        ToggleButton favoriteToggle;
        TextView favoriteMediaType;
        TextView favoriteName;
        RatingBar favoriteRating;
        SearchItem favoriteItem;

        public FavoritesViewHolder(@NonNull View view) {
            super(view);

            favoriteBackground = view.findViewById(R.id.favorite_background);
            favoriteToggle = view.findViewById(R.id.favorite_toggle);
            favoriteMediaType = view.findViewById(R.id.favorite_media_type);
            favoriteName = view.findViewById(R.id.favorite_name);
            favoriteRating = view.findViewById(R.id.favorite_rating);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onFavoriteItemClick(favoriteItem);
        }
    }
}