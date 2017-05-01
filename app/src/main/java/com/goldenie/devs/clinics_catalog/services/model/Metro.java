package com.goldenie.devs.clinics_catalog.services.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Metro {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("line")
    @Expose
    public String line;

}