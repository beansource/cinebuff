package com.carboni.cinebuff.presenter;

import com.carboni.cinebuff.view.MoviesView;

/**
 * Created by ericcarboni on 10/24/16.
 */

public class MoviePresenter implements Presenter {
    public MoviesView view;

    public MoviePresenter(MoviesView view) {
        this.view = view;
    }

    @Override
    public void attemptSearch(String query) {

    }
}
