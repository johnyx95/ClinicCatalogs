package com.goldenie.devs.clinics_catalog.fragmets;

import android.os.Bundle;

import com.goldenie.devs.clinics_catalog.R;

/**
 * Created by kobec on 01.05.2017.
 */

public class ListPharmacyFragment extends BaseFragment {
    @Override
    protected int getContentView() {
        return R.layout.list_pharmacy;
    }
    public static ListPharmacyFragment newInstance() {

        Bundle args = new Bundle();

        ListPharmacyFragment fragment = new ListPharmacyFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
