package com.goldenie.devs.clinics_catalog.services.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;
import com.google.maps.android.clustering.ClusterItem;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;

public class Pharmacy extends BaseDataModel{

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

    public MapModel getLocationModel() {

        return new MapModel(getPosition(), name, id);
    }

    public LatLng getPosition() {
        return  new LatLng(latitude,longitude);
    }

    @Data
    public class MapModel implements ClusterItem {
        private LatLng position;
        private String title;
        private Integer id;

        public MapModel(LatLng latLng, String name, Integer id) {
            this.position = latLng;
            this.title = name;
            this.id = id;
        }
    }
}