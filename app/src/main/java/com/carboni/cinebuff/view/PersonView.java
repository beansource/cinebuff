package com.carboni.cinebuff.view;

import com.carboni.cinebuff.model.Person;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by ericcarboni on 10/24/16.
 */

public interface PersonView {
    void showSuccess(Call<Person> list, Response<Person> response);

    void showFailure(Throwable error);

}
