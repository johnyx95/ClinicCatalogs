package com.goldenie.devs.clinics_catalog.fragmets;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;


import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.goldenie.devs.clinics_catalog.R;
import butterknife.BindView;


public class PharmacyFragment extends BaseFragment {

    @BindView(R.id.bottom_navigation)
    com.aurelhubert.ahbottomnavigation.AHBottomNavigation bottomNavigation;

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
                    break;
                case 1:
                    break;
            }
            return true;
        });
    }

}