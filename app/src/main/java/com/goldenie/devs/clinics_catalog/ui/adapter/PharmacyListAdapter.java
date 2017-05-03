package com.goldenie.devs.clinics_catalog.ui.adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goldenie.devs.clinics_catalog.R;
import com.goldenie.devs.clinics_catalog.services.model.Pharmacy;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PharmacyListAdapter extends RecyclerView.Adapter<PharmacyListAdapter.CustomViewHolder> {
    private ArrayList<Pharmacy> pharmacyArrayList = new ArrayList<>();

    public PharmacyListAdapter() {

    }
     public void setData(ArrayList<Pharmacy> pharmacies) {
         pharmacyArrayList.clear();
         pharmacyArrayList.addAll(pharmacies);
         notifyDataSetChanged();
     }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, viewGroup, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        Pharmacy pharmacy = pharmacyArrayList.get(i);

        customViewHolder.name.setText(pharmacy.getName());
        customViewHolder.address.setText(String.format("%s: %s", pharmacy.getCity(), pharmacy.getAddress()));
        customViewHolder.phone.setText(pharmacy.getPhone());

        if(TextUtils.isEmpty(pharmacy.getWorktime()))
            customViewHolder.worktime.setVisibility(View.GONE);
        else
            customViewHolder.worktime.setVisibility(View.VISIBLE);

        customViewHolder.worktime.setText(pharmacy.getWorktimes(customViewHolder.itemView.getResources()));
    }

    @Override
    public int getItemCount() {
        return (null != pharmacyArrayList ? pharmacyArrayList.size() : 0);
    }

    public void addData(ArrayList<Pharmacy> pharmacy) {
        pharmacyArrayList.addAll(pharmacy);
        notifyDataSetChanged();
    }

    public void clear() {
        pharmacyArrayList.clear();
        notifyDataSetChanged();
    }


    class CustomViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        AppCompatTextView name;
        @BindView(R.id.address)
        AppCompatTextView address;
        @BindView(R.id.phone)
        AppCompatTextView phone;
        @BindView(R.id.worktime)
        AppCompatTextView worktime;


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

        private Pharmacy getPharmacy(){
            return pharmacyArrayList.get(getAdapterPosition());
        }

        public CustomViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}