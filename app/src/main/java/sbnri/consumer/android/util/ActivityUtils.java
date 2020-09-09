package sbnri.consumer.android.util;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.util.TypedValue;
import android.view.View;

import java.math.BigInteger;

import androidx.core.content.ContextCompat;
import sbnri.consumer.android.R;
import sbnri.consumer.android.adapters.ItemEncapsulator;
import sbnri.consumer.android.base.activity.BaseActivity;

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
    }

