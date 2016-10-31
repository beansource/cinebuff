package com.carboni.cinebuff;

import com.carboni.cinebuff.model.Result;

import java.util.List;

import retrofit2.Response;

/**
 * Created by ericcarboni on 10/26/16.
 */

public interface OnPersonInteractorFinishedListener {
    void onNetworkSuccess(List<Result> list, Response response);

    void onNetworkFailure();
}
