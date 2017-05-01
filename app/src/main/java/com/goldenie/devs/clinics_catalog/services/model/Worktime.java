package com.goldenie.devs.clinics_catalog.services.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Worktime {

    @SerializedName("time_interval")
    @Expose
    public String timeInterval;
    @SerializedName("day_interval")
    @Expose
    public String dayInterval;

}