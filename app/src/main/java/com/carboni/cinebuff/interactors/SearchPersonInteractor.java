package com.carboni.cinebuff.interactors;

import android.util.Log;
import android.widget.Toast;

import com.carboni.cinebuff.OnPersonInteractorFinishedListener;
import com.carboni.cinebuff.model.Result;
import com.carboni.cinebuff.network.TMdbAPI;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ericcarboni on 10/25/16.
 */

public class SearchPersonInteractor implements Callback<List<Result>> {
    private OnPersonInteractorFinishedListener listener;

    public SearchPersonInteractor(OnPersonInteractorFinishedListener listener) {
        this.listener = listener;
    }

    // TODO: Inject this using Dagger or other form
    private Retrofit initRestAdapter() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public void loadPerson(String query) {
        Retrofit call = initRestAdapter();
        call.create(TMdbAPI.class).listPerson(query, this);
    }

    @Override
    public void onResponse(Call<List<Result>> call, Response<List<Result>> response) {
        Log.i("TAG", "Success");
        try {
            listener.onNetworkSuccess(call.execute().body(), response);
        } catch (IOException e) {

        }
    }

    @Override
    public void onFailure(Call<List<Result>> call, Throwable t) {
        Log.i("TAG", "Failure");
        listener.onNetworkFailure();
    }


}
