package com.carboni.cinebuff.listeners;

import android.view.View;

/**
 * Created by ericcarboni on 11/27/16.
 */

public interface OnMovieClickListener<M> {
    void onItemClicked(M item, View view, int position);
}
