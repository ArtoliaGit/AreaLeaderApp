package com.bsoft.arealeaderapp.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

/**
 * @author Artolia Pendragon
 */
public class ToastUtil {

    private static Toast mToast = null;

    private static final Handler sMainHandler = new Handler(Looper.getMainLooper());

    public static void show(final Context context, String text, int duration) {
        if (isMainLooper()) {
            showToast(context, text, duration);
        } else {
            sMainHandler.post(new Runnable() {
                @Override
                public void run() {
                    showToast(context, text, duration);
                }
            });
        }
    }

    public static void show(final Context context, final String text) {
        show(context, text, Toast.LENGTH_SHORT);
    }

    public static void showLong(final Context context, String text) {
        show(context, text, Toast.LENGTH_LONG);
    }

    private static boolean isMainLooper() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    private static void showToast(final Context context, String text, int duration) {
        if (mToast == null) {
            mToast = Toast.makeText(context, text, duration);
        } else {
            mToast.setText(text);
        }
        mToast.show();
    }
}
