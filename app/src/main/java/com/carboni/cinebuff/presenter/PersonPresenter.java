package com.carboni.cinebuff.presenter;

import android.util.Log;

import com.carboni.cinebuff.listeners.OnPersonInteractorFinishedListener;
import com.carboni.cinebuff.interactors.SearchPersonInteractor;
import com.carboni.cinebuff.model.Person;
import com.carboni.cinebuff.view.PersonView;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by ericcarboni on 10/24/16.
 */

public class PersonPresenter implements Presenter, OnPersonInteractorFinishedListener {
    private PersonView view;
    private SearchPersonInteractor interactor;

    public PersonPresenter(PersonView view) {
        this.view = view;
        this.interactor = new SearchPersonInteractor(this);
        Log.i("PersonPresenter", "PersonPresenter constructor called");
    }

    @Override
    public void attemptSearch(String query) {
        Log.i("PersonPresenter", "PersonPresenter attemptSearch() called");
        interactor.loadPerson(query);
    }

    @Override
    public void onNetworkSuccess(Call<Person> list, Response<Person> response) {
        Log.i("PersonPresenter", "PersonPresenter got success from Interactor");
        view.showSuccess(list, response);
    }

    @Override
    public void onNetworkFailure(Throwable error) {
        Log.i("PersonPresenter", "PersonPresenter got failure from Interactor");
        view.showFailure(error);
    }
}
