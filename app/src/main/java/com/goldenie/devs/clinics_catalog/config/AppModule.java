package com.goldenie.devs.clinics_catalog.config;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kobec on 24.04.2017.
 */

@Module
public class AppModule {
    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context provideContext(){
        return application;
    }
}
