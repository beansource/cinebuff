package com.carboni.cinebuff.view;

import com.carboni.cinebuff.model.MovieDetail;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by ericcarboni on 11/27/16.
 */

public interface MovieDetailView {
    void showSuccess(Call<MovieDetail> list, Response<MovieDetail> response);

    void showFailure(Throwable error);
}
