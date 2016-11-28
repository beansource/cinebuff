package com.carboni.cinebuff.presenter;

import android.util.Log;

import com.carboni.cinebuff.interactors.MovieDetailInteractor;
import com.carboni.cinebuff.listeners.ShowMovieDetailListener;
import com.carboni.cinebuff.model.MovieDetail;
import com.carboni.cinebuff.view.MovieDetailView;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by ericcarboni on 11/27/16.
 */

public class MovieDetailPresenter implements Presenter, ShowMovieDetailListener {
    MovieDetailView view;
    MovieDetailInteractor interactor;

    public MovieDetailPresenter(MovieDetailView view) {
        this.view = view;
        interactor = new MovieDetailInteractor(this);
    }

    @Override
    public void attemptSearch(String id) {
        interactor.loadMovie(Integer.parseInt(id));
        Log.i("TAG", "Loading movie with id " + id);
    }

    @Override
    public void onNetworkSuccess(Call<MovieDetail> call, Response<MovieDetail> response) {
        view.showSuccess(call, response);
    }

    @Override
    public void onNetworkFailure(Throwable error) {
        view.showFailure(error);
    }

}
