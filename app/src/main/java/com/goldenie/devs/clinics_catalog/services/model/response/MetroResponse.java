package com.goldenie.devs.clinics_catalog.services.model.response;

import com.goldenie.devs.clinics_catalog.services.model.Metro;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by kobec on 24.04.2017.
 */

public class MetroResponse extends BaseResponse {
    public ArrayList<Metro> getMetro() {
        Type listType = new TypeToken<ArrayList<Metro>>() {
        }.getType();
        return gson.fromJson(response.toString(), listType);
    }
}
