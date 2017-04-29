package com.goldenie.devs.clinics_catalog.services.model.response;

import com.goldenie.devs.clinics_catalog.services.model.Clinic;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by kobec on 24.04.2017.
 */
public class ClinicSearchResponse extends BaseResponse{
   public ArrayList<Clinic> getClinics(){
       Type listType = new TypeToken<ArrayList<Clinic>>() {}.getType();
       return gson.fromJson(response.toString(),listType);
   }
}
