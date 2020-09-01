package sbnri.consumer.android.onboarding;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import sbnri.consumer.android.DependencyInjectorComponent;
import sbnri.consumer.android.R;
import sbnri.consumer.android.base.activity.BaseActivity;
import sbnri.consumer.android.base.contract.BaseView;

public class OnBoardingActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.);
        baseToolbar.setVisibility(View.GONE);
    }

    @Override
    protected BaseView getBaseView() {
        return null;
    }

    @Override
    protected void callDependencyInjector(DependencyInjectorComponent injectorComponent) {

    }
}
