package com.carboni.cinebuff.interactors;

import com.carboni.cinebuff.listeners.ShowMovieDetailListener;
import com.carboni.cinebuff.model.MovieCredits;
import com.carboni.cinebuff.model.MovieDetail;
import com.carboni.cinebuff.model.MovieDetailAndCredits;
import com.carboni.cinebuff.network.TMdbAPI;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Created by ericcarboni on 11/27/16.
 */

public class MovieDetailInteractor {
    private ShowMovieDetailListener listener;

    public MovieDetailInteractor(ShowMovieDetailListener listener) {
        this.listener = listener;
    }

    // TODO: Inject this using Dagger or other form
    private Retrofit initRestAdapter() {
        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(rxAdapter)
                .build();
        return retrofit;
    }

    public void rxLoadMovieDetailAndCast(int movie_id) {
        Retrofit rest = initRestAdapter();

        Observable<MovieDetail> movieDetailObservable = rest
                .create(TMdbAPI.class)
                .rxGetMovieDetail(movie_id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

        Observable<MovieCredits> movieCastObservable = rest
                .create(TMdbAPI.class)
                .rxGetMovieCredits(movie_id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

        Observable<MovieDetailAndCredits> rxCombined = Observable.zip(movieDetailObservable, movieCastObservable, new Func2<MovieDetail, MovieCredits, MovieDetailAndCredits>() {
            @Override
            public MovieDetailAndCredits call(MovieDetail movieDetail, MovieCredits movieCredits) {
                return new MovieDetailAndCredits(movieDetail, movieCredits);
            }
        });

        rxCombined.subscribe(new Subscriber<MovieDetailAndCredits>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                listener.onNetworkFailure(e);
            }

            @Override
            public void onNext(MovieDetailAndCredits o) {
                listener.onNetworkSuccess(o);
            }
        });
    }

}
