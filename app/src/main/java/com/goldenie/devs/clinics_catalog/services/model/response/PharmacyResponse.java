package com.goldenie.devs.clinics_catalog.services.model.response;

import com.goldenie.devs.clinics_catalog.services.model.Pharmacy;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import javax.inject.Inject;

import lombok.Data;

@Data
public class PharmacyResponse {

    @Inject
    protected Gson gson;
    @SerializedName("data")
    @Expose
    protected Object response;

    public ArrayList<Pharmacy> getPharmacy() {
        Type listType = new TypeToken<ArrayList<Pharmacy>>() {
        }.getType();
        return gson.fromJson(response.toString(), listType);
    }
}