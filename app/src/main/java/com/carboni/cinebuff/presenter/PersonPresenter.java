package com.carboni.cinebuff.presenter;

import com.carboni.cinebuff.OnPersonInteractorFinishedListener;
import com.carboni.cinebuff.interactors.SearchPersonInteractor;
import com.carboni.cinebuff.model.Result;
import com.carboni.cinebuff.view.PersonView;

import java.util.List;

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
    }

    @Override
    public void attemptSearch(String query) {
        interactor.loadPerson(query);
    }

    @Override
    public void onNetworkSuccess(List<Result> list, Response response) {
        view.showSuccess(list, response);
    }

    @Override
    public void onNetworkFailure() {
        view.showFailure();
    }
}
