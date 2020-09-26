package sbnri.consumer.android.accountflow

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.widget.Button
import android.widget.TextView
import butterknife.BindView
import com.orhanobut.hawk.Hawk
import sbnri.consumer.android.DependencyInjectorComponent
import sbnri.consumer.android.R
import sbnri.consumer.android.base.activity.BaseActivity
import sbnri.consumer.android.base.contract.BaseView
import sbnri.consumer.android.constants.Constants
import sbnri.consumer.android.data.models.SubBank
import sbnri.consumer.android.data.models.UserDetails
import sbnri.consumer.android.databinding.LayoutBankWelcomeScreenBinding
import sbnri.consumer.android.util.extensions.hideView

class BankOnBoardingActivity : BaseActivity()
{

    @BindView(R.id.tv_name_label)
    internal lateinit var tvNameLabel:TextView

    @BindView(R.id.btn_letsbegin)
    internal lateinit var btn_letsbegin: Button

    @BindView(R.id.tv_all_yout_docs)
    internal lateinit var tv_all_yout_docs: TextView

    @BindView(R.id.tv_header_one)
    internal lateinit var tvHeaderOne : TextView

    @BindView(R.id.tv_subheader_one)
    internal lateinit var tvSubheaderOne:TextView

    @BindView(R.id.tv_footer_one)
    internal lateinit var tvFooterOne:TextView


    @BindView(R.id.tv_header_two)
    internal lateinit var tvHeaderTwo : TextView

    @BindView(R.id.tv_subheader_two)
    internal lateinit var tvSubheaderTwo:TextView

    @BindView(R.id.tv_footer_two)
    internal lateinit var tvFooterTwo:TextView

    @BindView(R.id.tv_header_three)
    internal lateinit var tvHeaderThree : TextView

    @BindView(R.id.tv_subheader_three)
    internal lateinit var tvSubheaderThree:TextView

    @BindView(R.id.tv_footer_three)
    internal lateinit var tvFooterThree:TextView




    private lateinit var binding: LayoutBankWelcomeScreenBinding

    private  var subBank: SubBank? = null

    private var mUserDetails: UserDetails? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_bank_welcome_screen)
        getIntentData()
        initView();
    }
    private fun getIntentData()
    {
        val intent = intent
        if (intent != null)
            subBank = intent.getParcelableExtra(Constants.CLICKED_BANK)
    }

    private fun initView() {

        baseToolbar.hideView()
        mUserDetails = Hawk.get(Constants.SBNRI_USER_OBJ)
        val part_one = context.getString(R.string.your_docs_as_repo) + " "
        val part_two = context.getString(R.string.repo)
        val spannableString = SpannableString(part_one + context.getString(R.string.repo))

        val foregroundColorSpan = ForegroundColorSpan(context.resources.getColor(R.color.peachyPinkTwo))
        spannableString.setSpan(foregroundColorSpan,part_one.length,part_one.length+part_two.length,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        tv_all_yout_docs.setText(spannableString)

     tvNameLabel.setText(String.format(getString(R.string.hi_name),mUserDetails?.firstName))
        tvHeaderOne.setText(subBank?.fdProcess?.get(0)?.title)
       tvSubheaderOne.setText(subBank?.fdProcess?.get(0)?.subtitle)
    }

    override fun getBaseView(): BaseView? {
       return null
    }

    override fun callDependencyInjector(injectorComponent: DependencyInjectorComponent?) {
       injectorComponent?.injectDependencies(this)
    }

}