package com.goldenie.devs.clinics_catalog.services.webservice;

import com.goldenie.devs.clinics_catalog.services.model.response.PharmacyResponse;


import io.reactivex.annotations.Nullable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface PharmacyWebService {

    @GET("/drugstores/map/")
    Observable<PharmacyResponse> getPharmacyGeo(@Query("latitude") double lat,
                                                @Query("longitude") double lon);

    @GET("/catalog/drugstores")
    Observable<PharmacyResponse> getPharmacy(@Nullable @Query("name") String name);
}