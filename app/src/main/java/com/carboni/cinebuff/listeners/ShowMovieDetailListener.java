package com.carboni.cinebuff.listeners;

import com.carboni.cinebuff.model.MovieDetail;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by ericcarboni on 11/27/16.
 */

public interface ShowMovieDetailListener {
    void onNetworkSuccess(Call<MovieDetail> call, Response<MovieDetail> response);

    void onNetworkFailure(Throwable error);
}
