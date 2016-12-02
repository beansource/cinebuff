package com.carboni.cinebuff.interactors;

import com.carboni.cinebuff.Constants;
import com.carboni.cinebuff.listeners.OnMovieInteractorFinishedListener;
import com.carboni.cinebuff.model.Movies;
import com.carboni.cinebuff.network.TMdbAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ericcarboni on 11/19/16.
 */

public class SearchMoviesInteractor implements Callback<Movies> {
    private OnMovieInteractorFinishedListener listener;

    public SearchMoviesInteractor(OnMovieInteractorFinishedListener listener) {
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

    public void loadMovies(String query) {
        Retrofit rest = initRestAdapter();
        Call<Movies> call = rest.create(TMdbAPI.class).getMovies(query);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Movies> call, Response<Movies> response) {
        listener.onNetworkSuccess(call, response);
    }

    @Override
    public void onFailure(Call<Movies> call, Throwable t) {
        listener.onNetworkFailure(t);
    }
}
