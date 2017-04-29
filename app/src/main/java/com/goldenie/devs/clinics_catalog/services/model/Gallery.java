package com.goldenie.devs.clinics_catalog.services.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Gallery {

@SerializedName("url")
@Expose
public String url;

}