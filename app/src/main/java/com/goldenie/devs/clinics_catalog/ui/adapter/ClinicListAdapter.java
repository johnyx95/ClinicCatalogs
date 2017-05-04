package com.goldenie.devs.clinics_catalog.ui.adapter;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.goldenie.devs.clinics_catalog.R;
import com.goldenie.devs.clinics_catalog.services.model.Clinic;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

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
        //logo
        customViewHolder.rate.setText(clinic.getRating());
        if(clinic.getHasParkingLot()==1) {
            customViewHolder.parking.setImageResource(R.drawable.ic_local_parking_white_24dp);
            customViewHolder.parkingText.setText(R.string.parking);
        }
        else {
            customViewHolder.parking.setVisibility(View.GONE);
            customViewHolder.parkingText.setVisibility(View.GONE);
        }
        if(clinic.getHasEpay()==1) {
            customViewHolder.epay.setImageResource(R.drawable.ic_payment_white_24dp);
            customViewHolder.epayText.setText(R.string.epay);
        }
        else {
            customViewHolder.epay.setVisibility(View.GONE);
            customViewHolder.epayText.setVisibility(View.GONE);
        }
        if(clinic.getSpeakEnglish()==1) {
            customViewHolder.speak.setImageResource(R.drawable.ic_language_white_24dp);
            customViewHolder.speakText.setText(R.string.speak);
        }
        else {
            customViewHolder.speak.setVisibility(View.GONE);
            customViewHolder.speakText.setVisibility(View.GONE);
        }
        if(clinic.getHasWifi()==1) {
            customViewHolder.wifi.setImageResource(R.drawable.ic_wifi_white_24dp);
            customViewHolder.wifiText.setText(R.string.wifi);
        }
        else {
            customViewHolder.wifi.setVisibility(View.GONE);
            customViewHolder.wifiText.setVisibility(View.GONE);
        }
        if(clinic.getHasPharamcy()==1) {
            customViewHolder.pharm.setImageResource(R.drawable.ic_local_pharmacy_white_24dp);
            customViewHolder.pharmText.setText(R.string.pharm);
        }
        else {
            customViewHolder.pharm.setVisibility(View.GONE);
            customViewHolder.parkingText.setVisibility(View.GONE);
        }
        if(clinic.getHasChildrenRoom()==1) {
            customViewHolder.child.setImageResource(R.drawable.ic_child_care_white_24dp);
            customViewHolder.childText.setText(R.string.child);
        }
        else {
            customViewHolder.child.setVisibility(View.GONE);
            customViewHolder.childText.setVisibility(View.GONE);
        }
        if(clinic.getHasInvalid()==1) {
            customViewHolder.invalid.setImageResource(R.drawable.ic_accessible_white_24dp);
            customViewHolder.invalidText.setText(R.string.invalid);
        }
        else {
            customViewHolder.invalid.setVisibility(View.GONE);
            customViewHolder.invalidText.setVisibility(View.GONE);
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

        @BindView(R.id.name)
        AppCompatTextView name;
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
