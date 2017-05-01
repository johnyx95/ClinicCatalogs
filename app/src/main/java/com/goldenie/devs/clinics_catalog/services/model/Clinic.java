package com.goldenie.devs.clinics_catalog.services.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Clinic {

    @SerializedName("logo_url")
    @Expose
    public String logoUrl;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("rating")
    @Expose
    public Integer rating;
    @SerializedName("short_description")
    @Expose
    public String shortDescription;
    @SerializedName("full_description")
    @Expose
    public String fullDescription;
    @SerializedName("location")
    @Expose
    public Integer location;
    @SerializedName("has_parking_lot")
    @Expose
    public Integer hasParkingLot;
    @SerializedName("has_epay")
    @Expose
    public Integer hasEpay;
    @SerializedName("speak_english")
    @Expose
    public Integer speakEnglish;
    @SerializedName("has_wifi")
    @Expose
    public Integer hasWifi;
    @SerializedName("has_pharamcy")
    @Expose
    public Integer hasPharamcy;
    @SerializedName("has_children_room")
    @Expose
    public Integer hasChildrenRoom;
    @SerializedName("has_invalid")
    @Expose
    public Integer hasInvalid;

}