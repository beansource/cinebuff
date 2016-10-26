package com.carboni.cinebuff.interactors;

/**
 * Created by ericcarboni on 10/25/16.
 */

public class SearchPersonInteractor implements Interactor {
    public SearchPersonInteractor() {

    }

    /*
    Validate user input
     */
    @Override
    public boolean validateQuery(String query) {
        return (query.length() > 0);
    }
}
