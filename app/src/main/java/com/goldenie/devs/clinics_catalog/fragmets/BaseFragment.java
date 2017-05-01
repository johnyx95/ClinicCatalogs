package com.goldenie.devs.clinics_catalog.fragmets;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goldenie.devs.clinics_catalog.utils.ViewUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by kobec on 01.05.2017.
 */

public abstract class BaseFragment extends Fragment {

    private Unbinder unbinder;

    protected abstract int getContentView();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getContentView(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }



    public void showProgressDialog() {
        if (isAdded())
            ViewUtils.showProgressDialog(getActivity());
    }

    public void hideProgressDialog() {
        if (isAdded())
            ViewUtils.hideProgressDialog(getActivity());
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

}
