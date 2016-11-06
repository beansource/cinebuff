package com.carboni.cinebuff.view;

import com.carboni.cinebuff.model.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by ericcarboni on 10/24/16.
 */

public interface PersonView {
    void showSuccess(Call<Result> list, Response response);

    void showFailure(Throwable error);

}
