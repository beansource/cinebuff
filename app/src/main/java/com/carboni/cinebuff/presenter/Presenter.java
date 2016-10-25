package com.carboni.cinebuff.presenter;

import android.view.View;

/**
 * Created by ericcarboni on 10/24/16.
 */

public interface Presenter<T extends View> {
    void onCreate();

    void onStart();

    void onStop();

    void onPause();

    void attachView(T view);
}
