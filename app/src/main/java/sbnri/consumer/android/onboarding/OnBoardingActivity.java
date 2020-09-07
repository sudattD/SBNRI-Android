package sbnri.consumer.android.onboarding;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import sbnri.consumer.android.DependencyInjectorComponent;
import sbnri.consumer.android.R;
import sbnri.consumer.android.base.activity.BaseActivity;
import sbnri.consumer.android.base.contract.BaseView;

public class OnBoardingActivity extends BaseActivity {

    @BindView(R.id.tab_indicator)
    TabLayout tabIndicator;

    @BindView(R.id.signUpGoogle)
    Button btnSignUpGoogle;

    @BindView(R.id.screen_viewpager)
    ViewPager screenViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_onboarding);
        baseToolbar.setVisibility(View.GONE);


        final List<OnBoardingItem> mList = new ArrayList<>();
        mList.add(new OnBoardingItem("Fresh Food","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua, consectetur  consectetur adipiscing elit"));
        mList.add(new OnBoardingItem("Fast Delivery","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua, consectetur  consectetur adipiscing elit"));
        mList.add(new OnBoardingItem("Easy Payment","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua, consectetur  consectetur adipiscing elit"));


        OnBoardingViewPagerAdapter onBoardingViewPagerAdapter =
        new OnBoardingViewPagerAdapter(this,mList);


        screenViewPager.setAdapter(onBoardingViewPagerAdapter);
        tabIndicator.setupWithViewPager(screenViewPager);


    }

    @Override
    protected BaseView getBaseView() {
        return null;
    }

    @Override
    protected void callDependencyInjector(DependencyInjectorComponent injectorComponent) {

        injectorComponent.injectDependencies(this);
    }


}
