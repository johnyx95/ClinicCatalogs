package com.goldenie.devs.clinics_catalog.services.model.response;

import com.goldenie.devs.clinics_catalog.services.model.Service;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by kobec on 24.04.2017.
 */

public class ServicesResponse extends BaseResponse {
    public ArrayList<Service> getSrvices() {
        Type listType = new TypeToken<ArrayList<Service>>() {
        }.getType();
        return gson.fromJson(response.toString(), listType);
    }
}
