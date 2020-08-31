package sbnri.consumer.android.util;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.core.content.ContextCompat;
import sbnri.consumer.android.R;
import sbnri.consumer.android.base.BaseActivity;

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
    }

