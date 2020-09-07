package sbnri.consumer.android.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import butterknife.BindView;
import sbnri.consumer.android.DependencyInjectorComponent;
import sbnri.consumer.android.R;
import sbnri.consumer.android.base.activity.BaseActivity;
import sbnri.consumer.android.base.contract.BaseView;
import sbnri.consumer.android.home.HomeActivity;
import sbnri.consumer.android.onboarding.OnBoardingActivity;

public class SplashActivity extends BaseActivity implements SplashContract.View{

    private static final long SPLASH_DELAY = 1000;
    @Inject
    protected SplashPresenter mPresenter;


/*
    @BindView(R.id.tvResp)
    TextView tvResp;
*/


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initView();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mPresenter.getAllNews();

    }


    @Override
    protected BaseView getBaseView() {
        return this;
    }

    @Override
    protected void callDependencyInjector(DependencyInjectorComponent injectorComponent) {

        injectorComponent.injectDependencies(this);
    }

    @Override
    public void navigateToPlayStore(String response) {

        //tvResp.setText(response);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this,OnBoardingActivity.class);
            startActivity(intent);
            finish();
        },SPLASH_DELAY);
    }

    @Override
    public void initView() {

        baseToolbar.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showToastMessage(String toastMessage, boolean isErrortoast) {

    }

    @Override
    public void accessTokenExpired() {

    }
}
