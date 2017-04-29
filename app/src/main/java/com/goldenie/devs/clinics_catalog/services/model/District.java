package com.goldenie.devs.clinics_catalog.services.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class District {

@SerializedName("name")
@Expose
public String name;

}