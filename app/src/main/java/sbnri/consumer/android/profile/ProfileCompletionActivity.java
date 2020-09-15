package sbnri.consumer.android.profile;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import sbnri.consumer.android.DependencyInjectorComponent;
import sbnri.consumer.android.R;
import sbnri.consumer.android.accountflow.ShowBanksListActivity;
import sbnri.consumer.android.base.activity.BaseFragmentActivity;
import sbnri.consumer.android.base.contract.BaseView;
import sbnri.consumer.android.constants.Constants;
import sbnri.consumer.android.data.models.Bank;
import sbnri.consumer.android.data.models.SubBank;
import sbnri.consumer.android.util.FragmentUtils;

public class ProfileCompletionActivity extends BaseFragmentActivity {


    public static Intent createInstance(Context context)
    {
        Intent intent = new Intent(context, ProfileCompletionActivity.class);

        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_profile_completion);
        baseToolbar.setVisibility(View.GONE);

         FragmentManager  fragmentManager = getSupportFragmentManager();;

        FragmentUtils.addFragment(fragmentManager, ProfileNameAndCityFragment.newInstance(), getContainerId(), false);

    }

    @Override
    protected int getContainerId() {
        return R.id.fragmentContainer;
    }

    @Override
    protected BaseView getBaseView() {
        return null;
    }

    @Override
    protected void callDependencyInjector(DependencyInjectorComponent injectorComponent) {

    }
}
