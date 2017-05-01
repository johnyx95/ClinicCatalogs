package com.goldenie.devs.clinics_catalog.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;

import com.goldenie.devs.clinics_catalog.R;
import com.goldenie.devs.clinics_catalog.services.event.LocationDisabledEvent;

import com.hwangjr.rxbus.RxBus;

/**
 * Created by kobec on 01.05.2017.
 */

public class Utils {
    public static boolean ifLocationEnabled(LocationManager lm, final Context context) {
        if (!lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setTitle(context.getResources().getString(R.string.location_not_enabled));
            dialog.setMessage(context.getResources().getString(R.string.gps_network_not_enabled));
            dialog.setPositiveButton(context.getResources().getString(android.R.string.yes), (paramDialogInterface, paramInt) -> {
                // TODO Auto-generated method stub
                Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(myIntent);
            });
            dialog.setNegativeButton(context.getString(android.R.string.no), (paramDialogInterface, paramInt)
                    -> RxBus.get().post(new LocationDisabledEvent())).show();
            return false;
        } else return true;
    }
}
