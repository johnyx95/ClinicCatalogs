package com.goldenie.devs.clinics_catalog.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.WindowManager;

import com.goldenie.devs.clinics_catalog.R;

/**
 * Created by kobec on 01.05.2017.
 */

public class ViewUtils {
    private static ProgressDialog dialog;

    public static void showProgressDialog(Context context) {
        dialog = new ProgressDialog(context);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage(context.getString(R.string.wait_for_a_while));
        dialog.setCancelable(false);

        ((Activity) context).runOnUiThread(() -> {
            try {
                if (dialog != null && !dialog.isShowing())
                    dialog.show();
            } catch (WindowManager.BadTokenException e) {
                e.printStackTrace();
            }
        });
    }

    public static void hideProgressDialog(Context context) {
        ((Activity) context).runOnUiThread(() -> {
            try {
                if (dialog != null && dialog.isShowing())
                    dialog.dismiss();
            } catch (WindowManager.BadTokenException e) {
                e.printStackTrace();
            }
        });
    }
}
