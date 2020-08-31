package sbnri.consumer.android.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewStub;
import android.widget.RelativeLayout;
import android.widget.TextView;

import javax.inject.Inject;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import sbnri.consumer.android.AppState;
import sbnri.consumer.android.R;
import sbnri.consumer.android.qualifiers.ApplicationContext;
import sbnri.consumer.android.util.ActivityUtils;

public abstract class BaseActivity extends AppCompatActivity {


    @BindView(R.id.tbBaseToolbar)
    public Toolbar baseToolbar;

    @BindView(R.id.tvToolbarTitle)
    public TextView tvToolbarTitle;


    private RelativeLayout baseLayout;

    public int previousState = AppState.getAppState();

    @ApplicationContext
    @Inject
    public Context appContext;


    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        baseLayout = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.base_layout, null);
        setContentView(baseLayout);
        stubLayoutRes(layoutResID);
        ButterKnife.bind(this);
        initialiseBaseActivityComponent();
        initialiseDaggerDependencies();
        initActionBar();
    }

    private void initActionBar()
    {
        setSupportActionBar(baseToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }

    }
    private void initialiseDaggerDependencies() {
    }

    private void initialiseBaseActivityComponent() {
    }

    private void stubLayoutRes(@LayoutRes int layoutResID) {
        ViewStub stub = baseLayout.findViewById(R.id.container);
        stub.setLayoutResource(layoutResID);
        stub.setFilterTouchesWhenObscured(true);
        stub.inflate();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    protected void initToolbar(String title, int titleColor, int toolbarColor, int navigationIcon) {
        ActivityUtils.initToolbar(this, title, titleColor, toolbarColor, navigationIcon);
    }

}
