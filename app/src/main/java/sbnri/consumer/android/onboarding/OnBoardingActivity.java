package sbnri.consumer.android.onboarding;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import sbnri.consumer.android.R;
import sbnri.consumer.android.base.BaseActivity;

public class OnBoardingActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.);
        baseToolbar.setVisibility(View.GONE);
    }
}
