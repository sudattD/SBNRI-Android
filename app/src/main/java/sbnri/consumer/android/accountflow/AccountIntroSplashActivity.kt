package sbnri.consumer.android.accountflow

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import sbnri.consumer.android.DependencyInjectorComponent
import sbnri.consumer.android.R
import sbnri.consumer.android.base.activity.BaseActivity
import sbnri.consumer.android.base.contract.BaseView
import sbnri.consumer.android.util.extensions.hideView

class AccountIntroSplashActivity : BaseActivity()
{

    private  val SPLASH_DELAY: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_sbm_splash)
        initView();
    }

    private fun initView() {

        baseToolbar.hideView()

        Handler().postDelayed({

            startActivity(Intent(context, BankOnBoardingActivity::class.java))

            finish()
        }, SPLASH_DELAY)

    }

    override fun getBaseView(): BaseView ?{
        return null
    }

    override fun callDependencyInjector(injectorComponent: DependencyInjectorComponent?) {
       injectorComponent?.injectDependencies(this)
    }

}