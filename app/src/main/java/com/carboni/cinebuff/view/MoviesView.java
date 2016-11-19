package com.carboni.cinebuff.view;

import com.carboni.cinebuff.model.Movies;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by ericcarboni on 10/24/16.
 */

public interface MoviesView {
    void showSuccess(Call<Movies> list, Response<Movies> response);

    void showFailure(Throwable error);

}
