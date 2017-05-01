package com.goldenie.devs.clinics_catalog.ui;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        ButterKnife.bind(this);
        if (toolbar != null)
            setSupportActionBar(toolbar);

    }
}

