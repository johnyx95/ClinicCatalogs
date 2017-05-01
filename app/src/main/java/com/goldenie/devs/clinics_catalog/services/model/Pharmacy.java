package com.goldenie.devs.clinics_catalog.services.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;
import com.google.maps.android.clustering.ClusterItem;

import lombok.Data;

@Data
public class Pharmacy extends BaseDataModel implements Parcelable {

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
        return new LatLng(latitude, longitude);
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeValue(this.longitude);
        dest.writeValue(this.latitude);
        dest.writeString(this.address);
        dest.writeString(this.phone);
        dest.writeString(this.worktime);
        dest.writeString(this.city);
    }

    protected Pharmacy(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.longitude = (Float) in.readValue(Float.class.getClassLoader());
        this.latitude = (Float) in.readValue(Float.class.getClassLoader());
        this.address = in.readString();
        this.phone = in.readString();
        this.worktime = in.readString();
        this.city = in.readString();
    }

    public static final Creator<Pharmacy> CREATOR = new Creator<Pharmacy>() {
        @Override
        public Pharmacy createFromParcel(Parcel source) {
            return new Pharmacy(source);
        }

        @Override
        public Pharmacy[] newArray(int size) {
            return new Pharmacy[size];
        }
    };
}