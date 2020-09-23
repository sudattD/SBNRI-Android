package sbnri.consumer.android.accountflow

import android.os.Bundle
import android.view.WindowManager
import androidx.core.content.ContextCompat
import sbnri.consumer.android.DependencyInjectorComponent
import sbnri.consumer.android.R
import sbnri.consumer.android.base.activity.BaseActivity
import sbnri.consumer.android.base.contract.BaseView
import sbnri.consumer.android.util.extensions.hideView

class BankOnBoardingActivity : BaseActivity()
{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_bank_welcome_screen)
        initView();
    }

    private fun initView() {

        baseToolbar.hideView()
        // clear FLAG_TRANSLUCENT_STATUS flag:
/*        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        window.setStatusBarColor(ContextCompat.getColor(context,R.color.darkBlue));*/
    }

    override fun getBaseView(): BaseView? {
       return null
    }

    override fun callDependencyInjector(injectorComponent: DependencyInjectorComponent?) {
       injectorComponent?.injectDependencies(this)
    }

}