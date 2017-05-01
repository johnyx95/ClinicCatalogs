package com.goldenie.devs.clinics_catalog.services.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Location {

    @SerializedName("address")
    @Expose
    public String address;
    @SerializedName("longitude")
    @Expose
    public Double longitude;
    @SerializedName("latitude")
    @Expose
    public Double latitude;

}