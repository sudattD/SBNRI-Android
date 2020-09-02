package sbnri.consumer.android.home;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import butterknife.BindView;
import sbnri.consumer.android.DependencyInjectorComponent;
import sbnri.consumer.android.R;
import sbnri.consumer.android.base.activity.BaseActivity;
import sbnri.consumer.android.base.contract.BaseView;

public class HomeActivity extends BaseActivity {


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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
