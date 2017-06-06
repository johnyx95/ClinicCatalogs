package com.goldenie.devs.clinics_catalog.ui.adapter;

import android.content.Intent;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.goldenie.devs.clinics_catalog.R;
import com.goldenie.devs.clinics_catalog.services.model.Clinic;
import com.goldenie.devs.clinics_catalog.ui.activity.ClinicPage;

import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kobec on 03.05.2017.
 */

public class ClinicListAdapter extends RecyclerView.Adapter<ClinicListAdapter.CustomViewHolder> {

    private ArrayList<Clinic> clinicArrayList = new ArrayList<>();

    public ClinicListAdapter () {

    }

    public void setData(ArrayList<Clinic> clinics) {
        clinicArrayList.clear();
        clinicArrayList.addAll(clinics);
        notifyDataSetChanged();
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row_clinic, viewGroup, false);
        return new CustomViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ClinicListAdapter.CustomViewHolder customViewHolder, int i) {
        Clinic clinic = clinicArrayList.get(i);

        customViewHolder.name.setText(clinic.getName());
        Glide.with(customViewHolder.itemView.getContext()).load(clinic.getLogoUrl()).into(customViewHolder.logo);
        customViewHolder.shortD.setText(clinic.getShortDescription());
        customViewHolder.rate.setText(Integer.toString(clinic.getRating()));

        customViewHolder.parkingLayout.setVisibility(View.VISIBLE);
        if(clinic.getHasParkingLot()!=1) {
            customViewHolder.parkingLayout.setVisibility(View.GONE);
        }
        customViewHolder.epayLayout.setVisibility(View.VISIBLE);
        if(clinic.getHasEpay()!=1) {
            customViewHolder.epayLayout.setVisibility(View.GONE);
        }
        customViewHolder.speakLayout.setVisibility(View.VISIBLE);
        if(clinic.getSpeakEnglish()!=1) {
            customViewHolder.speakLayout.setVisibility(View.GONE);
        }
        customViewHolder.wifiLayout.setVisibility(View.VISIBLE);
        if(clinic.getHasWifi()!=1) {
            customViewHolder.wifiLayout.setVisibility(View.GONE);
        }
        customViewHolder.pharmLayout.setVisibility(View.VISIBLE);
        if(clinic.getHasPharamcy()!=1) {
            customViewHolder.pharmLayout.setVisibility(View.GONE);
        }
        customViewHolder.childLayout.setVisibility(View.VISIBLE);
        if(clinic.getHasChildrenRoom()!=1) {
            customViewHolder.childLayout.setVisibility(View.GONE);
        }
        customViewHolder.invalidLayout.setVisibility(View.VISIBLE);
        if(clinic.getHasInvalid()!=1) {
            customViewHolder.invalidLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return (null != clinicArrayList ? clinicArrayList.size() : 0);
    }

    public void addData(ArrayList<Clinic> clinic) {
        clinicArrayList.addAll(clinic);
        notifyDataSetChanged();
    }

    public void clear() {
        clinicArrayList.clear();
        notifyDataSetChanged();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.parking_layout)
        LinearLayout parkingLayout;
        @BindView(R.id.epay_layout)
        LinearLayout epayLayout;
        @BindView(R.id.speak_layout)
        LinearLayout speakLayout;
        @BindView(R.id.wifi_layout)
        LinearLayout wifiLayout;
        @BindView(R.id.pharm_layout)
        LinearLayout pharmLayout;
        @BindView(R.id.child_layout)
        LinearLayout childLayout;
        @BindView(R.id.invalid_layout)
        LinearLayout invalidLayout;
        @BindView(R.id.name)
        AppCompatTextView name;
        @BindView(R.id.logo)
        AppCompatImageView logo;
        @BindView(R.id.short_d)
        AppCompatTextView shortD;
        @BindView(R.id.rate)
        AppCompatTextView rate;
        @BindView(R.id.parking)
        AppCompatImageView parking;
        @BindView(R.id.parking_text)
        AppCompatTextView parkingText;
        @BindView(R.id.epay)
        AppCompatImageView epay;
        @BindView(R.id.epay_text)
        AppCompatTextView epayText;
        @BindView(R.id.speak)
        AppCompatImageView speak;
        @BindView(R.id.speak_text)
        AppCompatTextView speakText;
        @BindView(R.id.wifi)
        AppCompatImageView wifi;
        @BindView(R.id.wifi_text)
        AppCompatTextView wifiText;
        @BindView(R.id.pharm)
        AppCompatImageView pharm;
        @BindView(R.id.pharm_text)
        AppCompatTextView pharmText;
        @BindView(R.id.child)
        AppCompatImageView child;
        @BindView(R.id.child_text)
        AppCompatTextView childText;
        @BindView(R.id.invalid)
        AppCompatImageView invalid;
        @BindView(R.id.invalid_text)
        AppCompatTextView invalidText;


    @OnClick(R.id.name_layout)
    public void onClinicLayoutClicked() {

        Intent intent = new Intent(itemView.getContext(),ClinicPage.class);
        intent.putExtra("ClinicId",this.getClinic().getId());
        itemView.getContext().startActivity(intent);
    }

/* на страницу клиники
        @OnClick(R.id.address_layout)
        public void onAddressLayoutClicked() {
            Pharmacy pharmacy = getPharmacy();

            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse(String.format("http://maps.google.com/maps?daddr=%s,%s", pharmacy.getLatitude(), pharmacy.getLongitude())));
            itemView.getContext().startActivity(intent);
        }

        @OnClick(R.id.phone_layout)
        public void onPhoneLayoutClicked() {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + getPharmacy().getPhone()));
            itemView.getContext().startActivity(intent);
        }
*/
        private Clinic getClinic(){
            return clinicArrayList.get(getAdapterPosition());
        }

        public CustomViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
