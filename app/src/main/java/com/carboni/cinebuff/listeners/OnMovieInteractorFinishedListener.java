package com.carboni.cinebuff.listeners;

import com.carboni.cinebuff.model.Movies;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by ericcarboni on 11/19/16.
 */

public interface OnMovieInteractorFinishedListener {
    void onNetworkSuccess(Call<Movies> list, Response<Movies> response);

    void onNetworkFailure(Throwable error);
}
