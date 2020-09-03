package sbnri.consumer.android.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import butterknife.BindView;
import sbnri.consumer.android.DependencyInjectorComponent;
import sbnri.consumer.android.R;
import sbnri.consumer.android.base.activity.BaseActivity;
import sbnri.consumer.android.base.contract.BaseView;
import sbnri.consumer.android.home.HomeActivity;

public class SplashActivity extends BaseActivity implements SplashContract.View{

    @Inject
    protected SplashPresenter mPresenter;


/*
    @BindView(R.id.tvResp)
    TextView tvResp;
*/


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_fragment);

        initView();

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
