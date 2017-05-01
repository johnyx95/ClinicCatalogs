package com.goldenie.devs.clinics_catalog;

import android.app.Application;
import android.content.Context;

import com.goldenie.devs.clinics_catalog.config.AppComponent;
import com.goldenie.devs.clinics_catalog.config.AppModule;
import com.goldenie.devs.clinics_catalog.config.DaggerAppComponent;
import com.goldenie.devs.clinics_catalog.config.NetWorkingModule;
import com.goldenie.devs.clinics_catalog.config.WebServiceModule;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by kobec on 24.04.2017.
 */

public class CatalogApplication extends Application {
    @Getter
    @Setter
    private static Context context;

    private AppComponent appComponent;

    public static AppComponent appComponent() {
        return ((CatalogApplication) getContext().getApplicationContext()).appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .netWorkingModule(new NetWorkingModule())
                .webServiceModule(new WebServiceModule())
                .build();

        setContext(this);
    }
}
