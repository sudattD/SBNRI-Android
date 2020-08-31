package sbnri.consumer.android.splash;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import sbnri.consumer.android.R;
import sbnri.consumer.android.base.BaseActivity;
import sbnri.consumer.android.home.HomeActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);




        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        //initToolbar(getString(R.string.app_name), R.color.toolbar_text, R.color.toolbar, R.drawable.arrow_back);


    }
}
