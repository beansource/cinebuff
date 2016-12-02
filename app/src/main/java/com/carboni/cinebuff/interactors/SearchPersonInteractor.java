package com.carboni.cinebuff.interactors;

import com.carboni.cinebuff.Constants;
import com.carboni.cinebuff.listeners.OnPersonInteractorFinishedListener;
import com.carboni.cinebuff.model.Person;
import com.carboni.cinebuff.model.Result;
import com.carboni.cinebuff.network.TMdbAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ericcarboni on 10/25/16.
 */

public class SearchPersonInteractor implements Callback<Person> {
    private OnPersonInteractorFinishedListener listener;

    public SearchPersonInteractor(OnPersonInteractorFinishedListener listener) {
        this.listener = listener;
    }

    // TODO: Inject this using Dagger or other form
    private Retrofit initRestAdapter() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public void loadPerson(String query) {
        Retrofit rest = initRestAdapter();
        Call<Person> call = rest.create(TMdbAPI.class).searchPerson(query);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Person> call, Response<Person> response) {
        listener.onNetworkSuccess(call, response);
    }

    @Override
    public void onFailure(Call<Person> call, Throwable t) {
        listener.onNetworkFailure(t);
    }

}
