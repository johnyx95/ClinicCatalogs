package com.goldenie.devs.clinics_catalog.services.webservice;

import com.goldenie.devs.clinics_catalog.services.model.response.PharmacyResponse;


import io.reactivex.annotations.Nullable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface PharmacyWebService {

    @GET("/catalog/drugstores/map")
    Observable<PharmacyResponse> getPharmacyGeo(@Query("lat") double lat,
                                                @Query("lon") double lon);

    @GET("/catalog/drugstores")
    Observable<PharmacyResponse> getPharmacy(@Nullable @Query("name") String name);
}