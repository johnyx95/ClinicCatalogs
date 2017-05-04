package com.goldenie.devs.clinics_catalog.ui.fragmets;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.WindowManager;

import com.goldenie.devs.clinics_catalog.R;
import com.goldenie.devs.clinics_catalog.services.model.Pharmacy;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by EvilDev on 02.05.2017.
 */

public class PharmacyBottomSheet extends BottomSheetDialogFragment {

    public static final String PHARAMCY = "pharamcy";
    @BindView(R.id.name)
    AppCompatTextView name;
    @BindView(R.id.address)
    AppCompatTextView address;
    @BindView(R.id.phone)
    AppCompatTextView phone;
    @BindView(R.id.worktime)
    AppCompatTextView worktime;
    Unbinder unbinder;


    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
        }
    };

    public static PharmacyBottomSheet newInstance(Pharmacy pharmacy) {
        Bundle args = new Bundle();
        PharmacyBottomSheet fragment = new PharmacyBottomSheet();
        args.putParcelable(PHARAMCY, pharmacy);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            final WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            getDialog().getWindow().setAttributes(params);
        }
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.pharmacy_bottom_sheet_layout, null);

        ButterKnife.bind(this, contentView);
        dialog.setContentView(contentView);

        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();

        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
        }

        setUpData();
    }

    private void setUpData() {
        Pharmacy pharmacy = (Pharmacy) getArguments().get(PHARAMCY);

        name.setText(pharmacy.getName());
        address.setText(pharmacy.getAddress());
        phone.setText(pharmacy.getPhone());
        worktime.setText(pharmacy.getWorktimes(getResources()));
    }

    @OnClick(R.id.address_layout)
    public void onAddressLayoutClicked() {
        Pharmacy pharmacy = getArguments().getParcelable(PHARAMCY);

        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse(String.format("http://maps.google.com/maps?daddr=%s,%s", pharmacy.getLatitude(), pharmacy.getLongitude())));
        startActivity(intent);
    }

    @OnClick(R.id.phone_layout)
    public void onPhoneLayoutClicked() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + ((Pharmacy) getArguments().getParcelable(PHARAMCY)).getPhone()));
        startActivity(intent);
    }
}
