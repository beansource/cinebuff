package com.carboni.cinebuff;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ericcarboni on 10/25/16.
 */

@Module
public class AppModule {
    private Application app;

    public AppModule(Application application) {
        app = application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return app;
    }
}
