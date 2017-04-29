package com.goldenie.devs.clinics_catalog.services.model.response;

import com.goldenie.devs.clinics_catalog.services.model.Clinic;
import com.goldenie.devs.clinics_catalog.services.model.District;
import com.goldenie.devs.clinics_catalog.services.model.Gallery;
import com.goldenie.devs.clinics_catalog.services.model.Location;
import com.goldenie.devs.clinics_catalog.services.model.Metro;
import com.goldenie.devs.clinics_catalog.services.model.Service;
import com.goldenie.devs.clinics_catalog.services.model.Worktime;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import lombok.Data;

@Data
public class ClinicResponse {

@SerializedName("clinic")
@Expose
public Clinic clinic;
@SerializedName("district")
@Expose
public District district;
@SerializedName("services")
@Expose
public ArrayList<Service> services = new ArrayList<>();
@SerializedName("location")
@Expose
public Location location;
@SerializedName("metro")
@Expose
public Metro metro;
@SerializedName("worktime")
@Expose
public ArrayList<Worktime> worktime = new ArrayList<>();
@SerializedName("gallery")
@Expose
public ArrayList<Gallery> gallery = new ArrayList<>();

}