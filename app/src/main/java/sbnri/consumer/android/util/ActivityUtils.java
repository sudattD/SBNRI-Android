package sbnri.consumer.android.util;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.math.BigInteger;

import androidx.core.content.ContextCompat;
import sbnri.consumer.android.R;
import sbnri.consumer.android.adapters.ItemEncapsulator;
import sbnri.consumer.android.base.activity.BaseActivity;
import sbnri.consumer.android.dialogue.BottomsheetReplacementDialog;

public class ActivityUtils {


    public static void initToolbar(BaseActivity activity, String title, int titleColor, int toolbarColor, int navigationIcon) {
        activity.baseToolbar.setBackgroundColor(ContextCompat.getColor(activity, toolbarColor));
        activity.tvToolbarTitle.setTextColor(ContextCompat.getColor(activity, titleColor));
        activity.tvToolbarTitle.setText(title);
        activity.baseToolbar.setVisibility(View.VISIBLE);

        if (activity.getSupportActionBar() == null) {
            return;
        }

        Drawable homeUpButton = ContextCompat.getDrawable(activity, navigationIcon);
            if (homeUpButton != null) {
                    homeUpButton.mutate().setColorFilter(ContextCompat.getColor(activity, R.color.toolbar_back), PorterDuff.Mode.SRC_IN);
                }

            activity.getSupportActionBar().setHomeAsUpIndicator(homeUpButton);
        }
    public static int dpToPx(float dp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }


    public static <T> ItemEncapsulator getItemEncapsulatorFromObject(T obj) {
        return new ItemEncapsulator(new BigInteger(obj.getClass().getSimpleName().getBytes()).intValue(), obj);
    }


    public static boolean isNetworkOnline(Context context) {
        boolean status = false;
        if (context != null) {
            ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connManager != null) {
                NetworkInfo mWifi = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    mWifi = connManager.getNetworkInfo(NetworkCapabilities.TRANSPORT_WIFI);
                } else {
                    mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                }
                if (mWifi != null && mWifi.isConnected()) {
                    return true;
                }
                try {
                    NetworkInfo netInfo = connManager.getNetworkInfo(0);
                    if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED) {
                        status = true;
                    } else {
                        netInfo = connManager.getNetworkInfo(1);
                        if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED)
                            status = true;
                    }
                } catch (Exception e) {
                    //Logger.d(e.getLocalizedMessage());
                    return false;
                }
            }
        }
        return status;
    }

    // Use if you want to create normal dialogs.
    public static Dialog getFlavourSpecificDialog(Context context, int layout) {
        return getFlavourSpecificDialog(context, 0, layout, true, true, false);
    }
    public static Dialog getFlavourSpecificDialog(Context context, int style, int layout, boolean isCancelable, boolean isCancelableOutside, boolean isSoftInputStateVisible) {
        Dialog dialog = getDialog(context, style);
        if (layout != 0)
            dialog.setContentView(layout);
        Window window = dialog.getWindow();
        if (window != null && isSoftInputStateVisible) {
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        }
        setFlavourSpecificWindowAttributes(context, window);
        dialog.setCancelable(isCancelable);
        dialog.setCanceledOnTouchOutside(isCancelableOutside);
        return dialog;
    }

    private static Dialog getDialog(Context context, int style) {

         return new BottomsheetReplacementDialog(context, style);
    }

    public static void setFlavourSpecificWindowAttributes(Context context, Window window) {
        if (window != null) {
            WindowManager.LayoutParams wmlp = window.getAttributes();
                window.setGravity(Gravity.BOTTOM);
                wmlp.width = android.view.WindowManager.LayoutParams.MATCH_PARENT;
                wmlp.height = android.view.WindowManager.LayoutParams.WRAP_CONTENT;

            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    public static Bitmap getResizedBitmap(Bitmap bitmap, int compressTo) {
        float bmpHeight = bitmap.getHeight();
        float bmpWidth = bitmap.getWidth();
        float ratio = bmpHeight / bmpWidth;

        int bitmapWidth = compressTo;
        float ratioHeight = compressTo * ratio;
        int bitmapHeight = Math.round(ratioHeight);

        return Bitmap.createScaledBitmap(bitmap, bitmapWidth, bitmapHeight, true);
    }

    }

