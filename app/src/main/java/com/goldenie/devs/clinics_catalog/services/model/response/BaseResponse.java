package com.goldenie.devs.clinics_catalog.services.model.response;

import com.goldenie.devs.clinics_catalog.CatalogApplication;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import javax.inject.Inject;

/**
 * Created by kobec on 24.04.2017.
 */

public abstract class BaseResponse {
    @Inject
    protected Gson gson;
    @SerializedName("data")
    @Expose
    protected Object response;

    public BaseResponse() {
        CatalogApplication.appComponent().inject(this);
    }
}
