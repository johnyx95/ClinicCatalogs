package com.goldenie.devs.clinics_catalog.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.goldenie.devs.clinics_catalog.fragmets.ListPharmacyFragment;
import com.goldenie.devs.clinics_catalog.fragmets.MapPharmacyFragment;

import java.util.ArrayList;

public class PharmacyPager extends FragmentPagerAdapter {

	private ArrayList<Fragment> fragments = new ArrayList<>();
    private Fragment currentFragment;

	public PharmacyPager(FragmentManager fm) {
		super(fm);

		fragments.clear();
		fragments.add(ListPharmacyFragment.newInstance());
		fragments.add(MapPharmacyFragment.newInstance());
	}

	@Override
	public Fragment getItem(int position) {
		return fragments.get(position);
	}

	@Override
	public int getCount() {
		return fragments.size();
	}

	@Override
	public void setPrimaryItem(ViewGroup container, int position, Object object) {
		if (getCurrentFragment() != object) {
			currentFragment = ((Fragment) object);
		}
		super.setPrimaryItem(container, position, object);
	}

	/**
	 * Get the current fragment
	 */
	public Fragment getCurrentFragment() {
		return currentFragment;
	}
}