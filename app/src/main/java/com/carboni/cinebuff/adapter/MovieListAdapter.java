package com.carboni.cinebuff.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.carboni.cinebuff.Constants;
import com.carboni.cinebuff.listeners.OnMovieClickListener;
import com.carboni.cinebuff.R;
import com.carboni.cinebuff.model.ResultMovies;

import java.util.List;

/**
 * Created by ericcarboni on 11/20/16.
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {
    private List<ResultMovies> list;
    private final OnMovieClickListener listener;
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

    public MovieListAdapter(List<ResultMovies> list, Context context, @NonNull OnMovieClickListener listener) {
        this.list = list;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movies_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final ResultMovies movie = list.get(position);
        holder.movieImage.setTransitionName("backdrop_" + movie.getId());
        holder.movieTitle.setText(movie.getTitle());
        holder.movieDate.setText(movie.getReleaseDate());
        holder.movieRating.setText(movie.getVoteAverage() + "");
        Glide
                .with(context)
                .load(Constants.IMAGE_LARGE + movie.getBackdropPath())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(R.drawable.material_flat)
                .crossFade()
                .into(holder.movieImage);
        setAnimation(holder.itemView, position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClicked(movie, view, position);
            }
        });
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
