package sbnri.consumer.android.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import butterknife.BindView;
import sbnri.consumer.android.DependencyInjectorComponent;
import sbnri.consumer.android.R;
import sbnri.consumer.android.base.activity.BaseActivity;
import sbnri.consumer.android.base.contract.BaseView;
import sbnri.consumer.android.data.models.UserDetails;
import sbnri.consumer.android.home.HomeActivity;
import sbnri.consumer.android.onboarding.OnBoardingActivity;
import sbnri.consumer.android.onboarding.OnBoardingContract;
import sbnri.consumer.android.onboarding.OnBoardingPresenterImpl;
import sbnri.consumer.android.util.Optional;

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


    }

    @Override
    public void initView() {

        baseToolbar.setVisibility(View.GONE);

        new Handler().postDelayed(() -> {


            //TODO REFACTOR BELOW CONDITIONS
            if(Hawk.get("UserDetails")!=null && !TextUtils.isEmpty( (((UserDetails) Hawk.get("UserDetails")).getToken())))
            {
                startActivity(HomeActivity.createInstance(context));
            }
            else
            {
                Intent intent = new Intent(SplashActivity.this,OnBoardingActivity.class);
                startActivity(intent);
            }

            finish();
        },SPLASH_DELAY);
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
