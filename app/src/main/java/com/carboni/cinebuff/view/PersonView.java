package com.carboni.cinebuff.view;

import com.carboni.cinebuff.model.Result;

import java.util.List;

import retrofit2.Response;

/**
 * Created by ericcarboni on 10/24/16.
 */

public interface PersonView {
    void showSuccess(List<Result> list, Response response);

    void showFailure();

}
