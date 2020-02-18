package com.xp.mvp_retrofit.utils;

import android.app.ProgressDialog;
import android.content.Context;

import com.xp.mvp_retrofit.App;

public class DialogUtil {

    public static ProgressDialog getWaittingDialog(Context context,String message) {
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage(message);
        return dialog;

    }


}
