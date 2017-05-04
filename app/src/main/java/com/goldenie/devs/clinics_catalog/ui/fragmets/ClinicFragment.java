package com.goldenie.devs.clinics_catalog.ui.fragmets;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.goldenie.devs.clinics_catalog.R;
import com.goldenie.devs.clinics_catalog.ui.adapter.ClinicPaper;

import butterknife.BindView;

/**
 * Created by kobec on 03.05.2017.
 */

public class ClinicFragment extends BaseFragment {

    @BindView(R.id.bottom_navigation_clinic)
    protected AHBottomNavigation bottomNavigationClinic;
    @BindView(R.id.pager_clinic)
    protected ViewPager pagerClinic;

    public static ClinicFragment newInstance() {

        Bundle args = new Bundle();

        ClinicFragment fragment = new ClinicFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentView() {
        return R.layout.clinic_layout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pagerClinic.setAdapter(new ClinicPaper(getChildFragmentManager()));

        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_3, R.drawable.ic_local_pharmacy_white_24dp, R.color.color_tab_1);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_2, R.drawable.ic_map_white_24dp, R.color.color_tab_2);

        bottomNavigationClinic.addItem(item1);
        bottomNavigationClinic.addItem(item2);

        bottomNavigationClinic.setOnTabSelectedListener((position, wasSelected) -> {
            pagerClinic.setCurrentItem(position, true);
            return true;
        });
    }
}
