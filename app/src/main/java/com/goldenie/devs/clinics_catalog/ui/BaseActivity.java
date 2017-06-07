package com.goldenie.devs.clinics_catalog.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.goldenie.devs.clinics_catalog.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by kobec on 24.04.2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Nullable
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    protected abstract int getContentView();

    private ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        ButterKnife.bind(this);
        if (toolbar != null)
            setSupportActionBar(toolbar);

        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage(getString(R.string.wait_for_a_while));
        dialog.setCancelable(false);

    }

    public void showProgressDialog() {
            if (dialog != null && !dialog.isShowing())
                dialog.show();
    }

    public void hideProgressDialog() {
            if (dialog != null && dialog.isShowing())
                dialog.dismiss();
    }
}

