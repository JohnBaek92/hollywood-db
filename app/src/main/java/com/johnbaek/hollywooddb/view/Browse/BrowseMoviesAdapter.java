package com.johnbaek.hollywooddb.view.Browse;

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
import com.johnbaek.hollywooddb.model.Movie;

import java.util.ArrayList;

public class BrowseMoviesAdapter extends RecyclerView.Adapter<BrowseMoviesAdapter.BrowseMovieViewHolder> {
    ArrayList<Movie> movies = new ArrayList<>();

    @NonNull
    @Override
    public BrowseMoviesAdapter.BrowseMovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.browse_movie_card_layout, viewGroup, false);

        return new BrowseMovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BrowseMovieViewHolder browseMovieViewHolder, int i) {
        String movieURI = getMovieURL(movies.get(i).getPosterPath());
        browseMovieViewHolder.browseMovieTitle.setText(movies.get(i).getTitle());

        Uri uri = Uri.parse(movieURI);
        browseMovieViewHolder.browseMoviePhoto.setImageURI(uri);

        Float movieVoteAverage = movies.get(i).getVoteAverage();
        movieVoteAverage = movieVoteAverage / 2;
        browseMovieViewHolder.browseMovieRating.setRating(Math.round(movieVoteAverage));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public String getMovieURL(String filePath){
            String BASE_URL = "https://image.tmdb.org/t/p";
            String POSTER_SIZE = "/w92";

            String movieURL = BASE_URL + POSTER_SIZE + filePath;

            return movieURL;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    class BrowseMovieViewHolder extends RecyclerView.ViewHolder {
        TextView browseMovieTitle;
        RatingBar browseMovieRating;
        ImageView browseMoviePhoto;
        public BrowseMovieViewHolder(View view) {
            super(view);
            browseMovieTitle = view.findViewById(R.id.browse_movie_title);
            browseMovieRating = view.findViewById(R.id.browse_movie_rating);
            browseMoviePhoto = view.findViewById(R.id.browse_movie_photo);
        }
    }
}
