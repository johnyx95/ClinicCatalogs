package com.goldenie.devs.clinics_catalog.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by kobec on 24.04.2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected abstract int getContentView();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
    }
}

