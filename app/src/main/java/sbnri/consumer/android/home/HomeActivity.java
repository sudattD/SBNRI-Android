package sbnri.consumer.android.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import sbnri.consumer.android.DependencyInjectorComponent;
import sbnri.consumer.android.R;
import sbnri.consumer.android.base.activity.BaseActivity;
import sbnri.consumer.android.base.contract.BaseView;
import sbnri.consumer.android.constants.Constants;
import sbnri.consumer.android.util.FragmentUtils;

public class HomeActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener{


    @BindView(R.id.bottomNav)
    BottomNavigationView bottomNav;

    public final static int HOME_FRAG = R.id.navHome;
    public final static int SERVICES_FRAG = R.id.navServices;
    private final static int ASK_FRAG = R.id.navAsk;
    private final static int NEWS_FRAG = R.id.navNews;
    private final static int PROFILE_FRAG= R.id.navProfile;



    @Retention(RetentionPolicy.SOURCE)
    @IntDef({HOME_FRAG, SERVICES_FRAG, ASK_FRAG, NEWS_FRAG,PROFILE_FRAG})
    @interface HomeFragmentIDs {
    }

    @BindView(R.id.home_container)
    FrameLayout homeContainer;

    private FragmentManager fragmentManager;
    private int previousSelectedPosition = 0;



    public static Intent createInstance(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }
    public static Intent createInstance(Context context, @HomeActivity.HomeFragmentIDs int fragId) {
        Intent intent = createInstance(context);
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.FRAG_ID, fragId);
        intent.putExtras(bundle);
        return intent;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        baseToolbar.setVisibility(View.GONE);

        fragmentManager = getSupportFragmentManager();
        bottomNav.setOnNavigationItemSelectedListener(this);
        bottomNav.setSelectedItemId(getFragmentID());
    }

    private int getFragmentID() {
        return getIntent().getIntExtra(Constants.FRAG_ID, HOME_FRAG);
    }


    @Override
    protected BaseView getBaseView() {
        return null;
    }

    @Override
    protected void callDependencyInjector(DependencyInjectorComponent injectorComponent) {

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (previousSelectedPosition == item.getItemId())
            return false;
        switch (item.getItemId()) {
            case HOME_FRAG:
                selectNavigationItem(HOME_FRAG, false);
                return true;
            case SERVICES_FRAG:
                selectNavigationItem(SERVICES_FRAG, false);
                return true;
            case ASK_FRAG:
                selectNavigationItem(ASK_FRAG, false);
                return true;
            case NEWS_FRAG:
                selectNavigationItem(NEWS_FRAG, false);
                return true;

            case PROFILE_FRAG:
                selectNavigationItem(PROFILE_FRAG, false);

                return true;
        }
        return false;
    }


    private void selectNavigationItem(@HomeActivity.HomeFragmentIDs int frag, boolean shouldSetChecked) {
        previousSelectedPosition = frag;
        if (shouldSetChecked)
            bottomNav.getMenu().findItem(frag).setChecked(true);
        switch (frag) {
            case HOME_FRAG:
                loadFragment(HomeFragment.Companion.newInstance());
                break;
            case SERVICES_FRAG:

               // loadFragment(ServicesFragment.newInstance("", Constants.TYPE_ADD_DEVICE, TAG, CleverTapConstants.FLOW_HOME));

                break;
            case ASK_FRAG:
               // loadFragment(AskFragment.newInstance(CleverTapConstants.FLOW_READ_NOTIFICATION));
                break;
            case PROFILE_FRAG:

              //  loadFragment(ProfileFragment.newInstance(CleverTapConstants.FLOW_USER_PROFILE));

                break;
            case NEWS_FRAG:
             //   loadFragment(NewsFragment.newInstance(CleverTapConstants.FLOW_READ_NOTIFICATION));
                break;
        }
    }


    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //transaction.addToBackStack(fragment.getClass().getName());
        transaction.replace(getContainerId(), fragment);
        transaction.commit();
    }

    private int getContainerId() {
        return R.id.home_container;
    }
}
