package com.carboni.cinebuff.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.carboni.cinebuff.R;
import com.carboni.cinebuff.model.ResultMovies;

import java.util.List;

/**
 * Created by ericcarboni on 11/20/16.
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {
    private List<ResultMovies> list;
    Context context;
    int lastPosition = -1;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView movieImage;
        public TextView movieTitle, movieDate, movieRating;

        public ViewHolder(View itemView) {
            super(itemView);
            movieImage = (ImageView) itemView.findViewById(R.id.movie_list_image);
            movieTitle = (TextView) itemView.findViewById(R.id.movie_list_title);
            movieDate = (TextView) itemView.findViewById(R.id.movie_list_date);
            movieRating = (TextView) itemView.findViewById(R.id.movie_list_rating);
        }
    }

    public MovieListAdapter(List<ResultMovies> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movies_list_backdrop, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ResultMovies movie = list.get(position);
        holder.movieTitle.setText(movie.getTitle());
        holder.movieDate.setText(movie.getReleaseDate());
        holder.movieRating.setText(movie.getVoteAverage() + "");
        Glide
                .with(context)
                .load("https://image.tmdb.org/t/p/w500" + movie.getBackdropPath())
                .placeholder(R.mipmap.ic_launcher)
                .crossFade()
                .centerCrop()
                .override(500, 320) // width, height
                .into(holder.movieImage);
        setAnimation(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private void setAnimation(View view, int pos) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (pos > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
            view.startAnimation(animation);
            lastPosition = pos;
        }
    }

}
