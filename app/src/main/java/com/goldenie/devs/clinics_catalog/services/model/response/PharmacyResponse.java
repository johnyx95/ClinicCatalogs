package com.goldenie.devs.clinics_catalog.services.model.response;

import com.goldenie.devs.clinics_catalog.services.model.Pharmacy;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class PharmacyResponse extends BaseResponse {
    public ArrayList<Pharmacy> getPharmacy() {
        Type listType = new TypeToken<ArrayList<Pharmacy>>() {}.getType();
        return gson.fromJson(gson.toJson(response), listType);
    }
}