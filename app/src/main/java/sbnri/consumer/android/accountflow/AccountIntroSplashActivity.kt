package sbnri.consumer.android.accountflow

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import com.squareup.picasso.Picasso
import sbnri.consumer.android.DependencyInjectorComponent
import sbnri.consumer.android.R
import sbnri.consumer.android.base.activity.BaseActivity
import sbnri.consumer.android.base.contract.BaseView
import sbnri.consumer.android.constants.Constants
import sbnri.consumer.android.data.models.SubBank
import sbnri.consumer.android.util.extensions.hideView

class AccountIntroSplashActivity : BaseActivity()
{

    private  val SPLASH_DELAY: Long = 3000

    private  var subBank: SubBank? = null

    @BindView(R.id.iv_bank_logo)
    internal lateinit var iv_bank_logo:ImageView

    @BindView(R.id.tv_bank_name)
    internal lateinit var tv_bank_name : TextView

    @BindView(R.id.tv_bank_descr)
    internal lateinit var tv_bank_descr : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_sbm_splash)
        getIntentData()
        initView()
    }

    private fun getIntentData()
    {
        val intent = intent
        if (intent != null)
            subBank = intent.getParcelableExtra(Constants.CLICKED_BANK)
    }

    private fun initView() {

        baseToolbar.hideView()
        Picasso.get().load(subBank?.bankImage).into(iv_bank_logo)
        tv_bank_name.setText(subBank?.bankName)
        tv_bank_descr.setText(subBank?.bankHighlight)

        Handler().postDelayed({

            val intent = Intent(this, BankOnBoardingActivity::class.java)
            intent.putExtra(Constants.CLICKED_BANK, subBank)
            startActivity(intent)
            //startActivity(Intent(context, BankOnBoardingActivity::class.java))

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