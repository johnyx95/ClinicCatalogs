package com.goldenie.devs.clinics_catalog.fragmets;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.goldenie.devs.clinics_catalog.R;


import butterknife.BindView;


public class PharmacyFragment extends BaseFragment {

    @BindView(R.id.bottom_navigation)
    AHBottomNavigation bottomNavigation;

    public static PharmacyFragment newInstance() {

        Bundle args = new Bundle();

        PharmacyFragment fragment = new PharmacyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentView() {
        return R.layout.pharmacy_layout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_1, R.drawable.ic_local_pharmacy_white_24dp, R.color.color_tab_1);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_2, R.drawable.ic_map_white_24dp, R.color.color_tab_2);

        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);

        bottomNavigation.setOnTabSelectedListener((position, wasSelected) -> {

            switch (position) {
                case 0:
                    getChildFragmentManager().beginTransaction().replace(R.id.container, ListPharmacyFragment.newInstance()).commit();
                    break;
                case 1:
                    getChildFragmentManager().beginTransaction().replace(R.id.container, MapPharmacyFragment.newInstance()).commit();
                    break;
            }
            return true;
        });
    }

}