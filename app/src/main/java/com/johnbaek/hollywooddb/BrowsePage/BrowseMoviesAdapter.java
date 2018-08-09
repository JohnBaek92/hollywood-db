package com.johnbaek.hollywooddb.BrowsePage;

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
import com.johnbaek.hollywooddb.Util;
import com.johnbaek.hollywooddb.model.SearchItem;

import java.util.ArrayList;

public class BrowseMoviesAdapter extends RecyclerView.Adapter<BrowseMoviesAdapter.BrowseMovieViewHolder> {
    public interface BrowseListingsClickListener {
        void onBrowseMovieClick(SearchItem movie);
    }

    private ArrayList<SearchItem> movies = new ArrayList<>();
    private BrowseMoviesAdapter.BrowseListingsClickListener clickListener;
    private static String POSTER_SIZE_92 = "/w92";
    private static String MOVIE = "movie";

    BrowseMoviesAdapter(BrowseListingsClickListener clickListener){
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public BrowseMoviesAdapter.BrowseMovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.browse_movie_card_layout, viewGroup, false);

        return new BrowseMovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BrowseMovieViewHolder browseMovieViewHolder, int i) {
        SearchItem movie = movies.get(i);
        browseMovieViewHolder.movie = movie;

        movie.setMediaType(MOVIE);

        String moviePosterURI = movie.getPosterPath();
        String moviePosterURL = Util.getPosterURL(moviePosterURI, POSTER_SIZE_92);
        Uri uri = Uri.parse(moviePosterURL);
        browseMovieViewHolder.browseMoviePhoto.setImageURI(uri);

        browseMovieViewHolder.browseMovieTitle.setText(movie.getHollywoodTitle());

        Float movieVoteAverage = movie.getVoteAverage();
        browseMovieViewHolder.browseMovieRating.setRating(Math.round(movieVoteAverage/2));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    void setMovies(ArrayList<SearchItem> movies) {
        this.movies = movies;
    }

    class BrowseMovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView browseMovieTitle;
        RatingBar browseMovieRating;
        ImageView browseMoviePhoto;
        SearchItem movie;

        BrowseMovieViewHolder(View view) {
            super(view);
            browseMovieTitle = view.findViewById(R.id.browse_movie_title);
            browseMovieRating = view.findViewById(R.id.browse_movie_rating);
            browseMoviePhoto = view.findViewById(R.id.browse_movie_photo);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onBrowseMovieClick(movie);
        }
    }
}
