package sbnri.consumer.android.home;

import android.os.Bundle;

import androidx.annotation.Nullable;
import sbnri.consumer.android.R;
import sbnri.consumer.android.base.activity.BaseActivity;

public class HomeActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
}
