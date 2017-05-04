package com.goldenie.devs.clinics_catalog.ui.fragmets;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goldenie.devs.clinics_catalog.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by kobec on 01.05.2017.
 */

public abstract class BaseFragment extends Fragment {

    private Unbinder unbinder;
    private ProgressDialog dialog;

    protected abstract int getContentView();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getContentView(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dialog = new ProgressDialog(getContext());
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage(getString(R.string.wait_for_a_while));
        dialog.setCancelable(false);
    }

    public void showProgressDialog() {
        if (isAdded())
            if (dialog != null && !dialog.isShowing())
                dialog.show();
    }

    public void hideProgressDialog() {
        if (isAdded())
            if (dialog != null && dialog.isShowing())
                dialog.dismiss();
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

}
