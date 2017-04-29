package com.goldenie.devs.clinics_catalog.services.model;

import com.google.gson.annotations.SerializedName;

public class Pharmacy {

    @SerializedName("id")
    
    public Integer id;
    @SerializedName("name")
    
    public String name;
    @SerializedName("longitude")
    
    public Float longitude;
    @SerializedName("latitude")
    
    public Float latitude;
    @SerializedName("address")
    
    public String address;
    @SerializedName("phone")
    
    public String phone;
    @SerializedName("worktime")
    
    public String worktime;
    @SerializedName("city")
    
    public String city;
}