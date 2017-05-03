package com.goldenie.devs.clinics_catalog.services.model;

import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.goldenie.devs.clinics_catalog.R;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;
import com.google.maps.android.clustering.ClusterItem;

import lombok.Data;

@Data
public class Pharmacy extends BaseDataModel implements Parcelable {

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("longitude")
    private Float longitude;

    @SerializedName("latitude")
    private Float latitude;

    @SerializedName("address")
    private String address;

    @SerializedName("phone")
    private String phone;

    @SerializedName("worktime")
    private String worktime;

    @SerializedName("city")
    private String city;

    public MapModel getLocationModel() {
        return new MapModel(getPosition(), name, id);
    }

    public LatLng getPosition() {
        return new LatLng(latitude, longitude);
    }

    public String getWorktimes(Resources resources) {

        String temp = "";

        if (TextUtils.isEmpty(worktime))
            return temp;

        String[] worktimes = worktime.split(";");

        for (int i = 0; i < worktimes.length; i++) {

            temp += String.format("%s: %s\n", resources.getStringArray(R.array.days)[i], worktimes[i]);
        }
        return temp;

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