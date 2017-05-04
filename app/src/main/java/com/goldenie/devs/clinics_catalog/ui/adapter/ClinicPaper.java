package com.goldenie.devs.clinics_catalog.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.goldenie.devs.clinics_catalog.ui.fragmets.ListClinicFragment;

import java.util.ArrayList;

/**
 * Created by kobec on 03.05.2017.
 */

public class ClinicPaper extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private Fragment currentFragment;

    public ClinicPaper(FragmentManager fm) {
        super(fm);

        fragments.clear();
        fragments.add(ListClinicFragment.newInstance());
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        if (getCurrentFragment() != object) {
            currentFragment = ((Fragment) object);
        }
        super.setPrimaryItem(container, position, object);
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public Fragment getCurrentFragment() {
        return currentFragment;
    }
}
