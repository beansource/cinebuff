package com.carboni.cinebuff.presenter;

import com.carboni.cinebuff.listeners.OnMovieInteractorFinishedListener;
import com.carboni.cinebuff.interactors.SearchMoviesInteractor;
import com.carboni.cinebuff.model.Movies;
import com.carboni.cinebuff.view.MoviesView;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by ericcarboni on 10/24/16.
 */

public class MoviePresenter implements Presenter, OnMovieInteractorFinishedListener {
    private MoviesView view;
    private SearchMoviesInteractor interactor;

    public MoviePresenter(MoviesView view) {
        this.view = view;
        this.interactor = new SearchMoviesInteractor(this);
    }

    @Override
    public void attemptSearch(String query) {
        interactor.loadMovies(query);
    }

    @Override
    public void onNetworkSuccess(Call<Movies> list, Response<Movies> response) {
        view.showSuccess(list, response);
    }

    @Override
    public void onNetworkFailure(Throwable error) {
        view.showFailure(error);
    }
}
