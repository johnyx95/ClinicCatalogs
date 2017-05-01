package com.goldenie.devs.clinics_catalog.services.model.response;

import com.goldenie.devs.clinics_catalog.services.model.District;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by kobec on 24.04.2017.
 */

public class DistrictResponse extends BaseResponse {
    public ArrayList<District> getDistricts() {
        Type listType = new TypeToken<ArrayList<District>>() {
        }.getType();
        return gson.fromJson(response.toString(), listType);
    }
}
