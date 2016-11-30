package com.carboni.cinebuff.view;

import com.carboni.cinebuff.model.MovieDetailAndCredits;

/**
 * Created by ericcarboni on 11/27/16.
 */

public interface MovieDetailView {
    void showSuccess(MovieDetailAndCredits o);

    void showFailure(Throwable error);
}
