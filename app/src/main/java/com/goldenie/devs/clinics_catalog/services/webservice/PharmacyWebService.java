package com.goldenie.devs.clinics_catalog.services.webservice;

import com.goldenie.devs.clinics_catalog.services.model.response.PharamcyResponse;

import io.reactivex.Observable;
import io.reactivex.annotations.Nullable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PharmacyWebService {

    @GET("/catalog/drugstores/map")
    Observable<PharamcyResponse> getPharmacyGeo(@Query("lat") Float lat,
                                                @Query("lon") Float lon);

    @GET("/catalog/drugstores")
    Observable<PharamcyResponse> getPharmacy(@Nullable @Query("name") String name);
}