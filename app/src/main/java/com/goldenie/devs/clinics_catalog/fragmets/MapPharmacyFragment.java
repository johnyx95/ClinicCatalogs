package com.goldenie.devs.clinics_catalog.fragmets;

import android.os.Bundle;

import com.goldenie.devs.clinics_catalog.R;

/**
 * Created by kobec on 01.05.2017.
 */

public class MapPharmacyFragment extends BaseFragment {

    @Override
    protected int getContentView() {
        return R.layout.map_layout;
    }
    public static MapPharmacyFragment newInstance() {

        Bundle args = new Bundle();

        MapPharmacyFragment fragment = new MapPharmacyFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
