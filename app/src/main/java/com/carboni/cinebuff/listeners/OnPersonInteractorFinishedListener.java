package com.carboni.cinebuff.listeners;

import com.carboni.cinebuff.model.Person;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by ericcarboni on 10/26/16.
 */

public interface OnPersonInteractorFinishedListener {
    void onNetworkSuccess(Call<Person> list, Response<Person> response);

    void onNetworkFailure(Throwable error);
}
