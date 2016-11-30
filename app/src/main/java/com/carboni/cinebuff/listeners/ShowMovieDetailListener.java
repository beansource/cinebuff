package com.carboni.cinebuff.listeners;

import com.carboni.cinebuff.model.MovieDetailAndCredits;

/**
 * Created by ericcarboni on 11/27/16.
 */

public interface ShowMovieDetailListener {
    void onNetworkSuccess(MovieDetailAndCredits o);

    void onNetworkFailure(Throwable error);
}
