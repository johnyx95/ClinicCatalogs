package com.goldenie.devs.clinics_catalog.services.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Clinic extends BaseDataModel implements Parcelable{

    @SerializedName("id")
    @Expose
    public Integer id;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.logoUrl);
        dest.writeString(this.name);
        dest.writeValue(this.rating);
        dest.writeString(this.shortDescription);
        dest.writeString(this.fullDescription);
        dest.writeValue(this.location);
        dest.writeValue(this.hasParkingLot);
        dest.writeValue(this.hasEpay);
        dest.writeValue(this.speakEnglish);
        dest.writeValue(this.hasWifi);
        dest.writeValue(this.hasPharamcy);
        dest.writeValue(this.hasChildrenRoom);
        dest.writeValue(this.hasInvalid);
    }

    protected Clinic(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.logoUrl = in.readString();
        this.name = in.readString();
        this.rating = (Integer) in.readValue(Integer.class.getClassLoader());
        this.shortDescription = in.readString();
        this.fullDescription = in.readString();
        this.location = (Integer) in.readValue(Integer.class.getClassLoader());
        this.hasParkingLot = (Integer) in.readValue(Integer.class.getClassLoader());
        this.hasEpay = (Integer) in.readValue(Integer.class.getClassLoader());
        this.speakEnglish = (Integer) in.readValue(Integer.class.getClassLoader());
        this.hasWifi = (Integer) in.readValue(Integer.class.getClassLoader());
        this.hasPharamcy = (Integer) in.readValue(Integer.class.getClassLoader());
        this.hasChildrenRoom = (Integer) in.readValue(Integer.class.getClassLoader());
        this.hasInvalid = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Creator<Clinic> CREATOR = new Creator<Clinic>() {
        @Override
        public Clinic createFromParcel(Parcel source) {
            return new Clinic(source);
        }

        @Override
        public Clinic[] newArray(int size) {
            return new Clinic[size];
        }
    };

}