package com.carboni.cinebuff.listeners;

import android.view.View;

/**
 * Created by ericcarboni on 12/15/16.
 */

public interface OnPersonClickListener<M> {
    void onItemClicked(M item, View view, int position);
}
