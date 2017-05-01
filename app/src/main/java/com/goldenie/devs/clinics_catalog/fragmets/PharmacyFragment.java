package com.goldenie.devs.clinics_catalog.fragmets;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.goldenie.devs.clinics_catalog.R;
import com.goldenie.devs.clinics_catalog.ui.adapter.PharmacyPager;

import butterknife.BindView;


public class PharmacyFragment extends BaseFragment {

    @BindView(R.id.bottom_navigation)
    protected AHBottomNavigation bottomNavigation;
    @BindView(R.id.pager)
    protected ViewPager pager;

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

        pager.setAdapter(new PharmacyPager(getChildFragmentManager()));

        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_1, R.drawable.ic_local_pharmacy_white_24dp, R.color.color_tab_1);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_2, R.drawable.ic_map_white_24dp, R.color.color_tab_2);

        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);

        bottomNavigation.setOnTabSelectedListener((position, wasSelected) -> {
            pager.setCurrentItem(position, true);
            return true;
        });

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigation.setCurrentItem(position, false);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}