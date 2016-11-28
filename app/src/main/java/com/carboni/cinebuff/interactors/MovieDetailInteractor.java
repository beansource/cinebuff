package com.carboni.cinebuff.interactors;

import android.util.Log;

import com.carboni.cinebuff.listeners.OnMovieClickListener;
import com.carboni.cinebuff.listeners.ShowMovieDetailListener;
import com.carboni.cinebuff.model.MovieCredits;
import com.carboni.cinebuff.model.MovieDetail;
import com.carboni.cinebuff.network.TMdbAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ericcarboni on 11/27/16.
 */

public class MovieDetailInteractor implements Callback<MovieDetail> {
    private ShowMovieDetailListener listener;

    public MovieDetailInteractor(ShowMovieDetailListener listener) {
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

    public void loadMovie(int movie_id) {
        Retrofit rest = initRestAdapter();
        Call<MovieDetail> call = rest.create(TMdbAPI.class).getMovieDetail(movie_id);
        call.enqueue(this);
    }

    public void loadMovieCredits(int movie_id) {
        Retrofit rest = initRestAdapter();
        Call<MovieCredits> call = rest.create(TMdbAPI.class).getMovieCredits(movie_id);
    }

    @Override
    public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
        Log.i("TAG", "On movie detail rest response success");
        listener.onNetworkSuccess(call, response);
    }

    @Override
    public void onFailure(Call<MovieDetail> call, Throwable t) {
        listener.onNetworkFailure(t);
    }
}
