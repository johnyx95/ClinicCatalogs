package com.goldenie.devs.clinics_catalog.config;

import com.goldenie.devs.clinics_catalog.services.webservice.CatalogWebService;
import com.goldenie.devs.clinics_catalog.services.webservice.PharmacyWebService;
import com.goldenie.devs.clinics_catalog.utils.WebServiceUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by kobec on 24.04.2017.
 */
@Module
public class WebServiceModule {
    @Provides
    @Singleton
    public CatalogWebService provideCatalogWebService(Retrofit.Builder retrofitBuilder) {
        Retrofit retrofit = retrofitBuilder.baseUrl(WebServiceUtils.ENDPOINT).build();
        return retrofit.create(CatalogWebService.class);
    }
    @Provides
    @Singleton
    public PharmacyWebService providePharmacyWebService(Retrofit.Builder retrofitBuilder) {
        Retrofit retrofit = retrofitBuilder.baseUrl(WebServiceUtils.HOST).build();
        return retrofit.create(PharmacyWebService.class);
    }

}
