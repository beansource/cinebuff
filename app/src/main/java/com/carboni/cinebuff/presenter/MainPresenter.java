package com.carboni.cinebuff.presenter;

import com.carboni.cinebuff.Interactors.SearchPersonInteractor;
import com.carboni.cinebuff.view.MainView;

/**
 * Created by ericcarboni on 10/25/16.
 * MainPresenter has a reference to both the View and the Interactor
 * MainPresenter retrieves data from the model, and notifies the View to display it
 */

public class MainPresenter implements Presenter {

    private MainView view;
    private SearchPersonInteractor interactor;

    public MainPresenter(MainView mainView) {
        this.view = mainView;
        this.interactor = new SearchPersonInteractor();
    }

    /*
    Validate and pass to View
     */
    @Override
    public void attemptSearch(String query) {
        boolean isValid = interactor.validateQuery(query);
        if (isValid) {
            view.populatePeople();
        } else {
            view.searchFailed();
        }
    }
}
