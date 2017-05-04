package com.goldenie.devs.clinics_catalog.config;

import com.goldenie.devs.clinics_catalog.ui.fragmets.ListClinicFragment;
import com.goldenie.devs.clinics_catalog.ui.fragmets.ListPharmacyFragment;
import com.goldenie.devs.clinics_catalog.ui.fragmets.MapPharmacyFragment;
import com.goldenie.devs.clinics_catalog.services.model.response.BaseResponse;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by kobec on 24.04.2017.
 */

@Singleton
@Component(modules = {AppModule.class, WebServiceModule.class, NetWorkingModule.class})
public interface AppComponent {
    void inject(BaseResponse baseResponse);

    void inject(MapPharmacyFragment mapPharmacyFragment);

    void inject(ListPharmacyFragment listPharmacyFragment);

    void inject(ListClinicFragment listClinicFragment);
}
