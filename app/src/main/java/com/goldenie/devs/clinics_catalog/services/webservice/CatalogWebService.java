package com.goldenie.devs.clinics_catalog.services.webservice;

import com.goldenie.devs.clinics_catalog.services.model.response.ClinicResponse;
import com.goldenie.devs.clinics_catalog.services.model.response.ClinicSearchResponse;
import com.goldenie.devs.clinics_catalog.services.model.response.DistrictResponse;
import com.goldenie.devs.clinics_catalog.services.model.response.MetroResponse;
import com.goldenie.devs.clinics_catalog.services.model.response.ServicesResponse;

import io.reactivex.annotations.Nullable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by kobec on 24.04.2017.
 */

public interface CatalogWebService {

    @GET("/clinic_full_info")
    Observable<ClinicResponse> getClinicFullInfo(@Query("clinic_id") Integer clinicID);

    @GET("/clinics_services_districts")
    Observable<ClinicSearchResponse> getClinicForServicesDistricts(@Query("service_id") Integer serviceId,
                                                                   @Query("district_id") Integer districtId,
                                                                   @Nullable @Query("num_page") Integer numPage);

    @GET("/clinics_services_rating")
    Observable<ClinicSearchResponse> getClinicForServicesRating(@Query("service_id") Integer specializationId,
                                                                @Query("rating_id") Integer rating,
                                                                @Nullable @Query("num_page") Integer numPage);

    @GET("/clinics_metro_name")
    Observable<ClinicSearchResponse> getClinicForMetro(@Query("metro_id") Integer metroId,
                                                       @Nullable @Query("num_page") Integer numPage);

    @GET("/services")
    Observable<ServicesResponse> getServices();

    @GET("/districts")
    Observable<DistrictResponse> getDistricts();

    @GET("/metro")
    Observable<MetroResponse> getMetro();

}
