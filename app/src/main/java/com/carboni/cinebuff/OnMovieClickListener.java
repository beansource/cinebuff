package com.carboni.cinebuff;

/**
 * Created by ericcarboni on 11/27/16.
 */

public interface OnMovieClickListener<M, V> {
    void onItemClicked(M item, V view);
}