package com.carboni.cinebuff.interactors;

import android.util.Log;

import com.carboni.cinebuff.OnPersonInteractorFinishedListener;
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
        Log.i("SearchPersonInteractor", "Interactor constructor called");
    }

    // TODO: Inject this using Dagger or other form
    private Retrofit initRestAdapter() {
        Log.i("SearchPersonInteractor", "Initializing Rest");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public void loadPerson(String query) {
        Retrofit rest = initRestAdapter();
        Call<Person> call = rest.create(TMdbAPI.class).searchPerson(query);
        call.enqueue(this);
        Log.i("SearchPersonInteractor", "Retrofit calling API class");
    }

    @Override
    public void onResponse(Call<Person> call, Response<Person> response) {
        Log.i("SearchPersonInteractor", "Success from OnSearchInteractorFinishedListener");
        List<Result> people = response.body().getResults();
        for (Result person : people) {
            Log.i("SPAM", person.getId() + " " + person.getName());
        }
        listener.onNetworkSuccess(call, response);
    }

    @Override
    public void onFailure(Call<Person> call, Throwable t) {
        Log.i("SearchPersonInteractor ", "Failure from OnSearchInteractorFinishedListener");
        listener.onNetworkFailure(t);
    }

}
