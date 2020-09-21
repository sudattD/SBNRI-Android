package sbnri.consumer.android.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import sbnri.consumer.android.DependencyInjectorComponent
import sbnri.consumer.android.R
import sbnri.consumer.android.base.activity.BaseFragment
import sbnri.consumer.android.base.contract.BaseView

class ProfileBiometricAuthorizeFragment : BaseFragment()
{


    companion object
    {
        fun newInstance(): ProfileBiometricAuthorizeFragment? {
            val fragment = ProfileBiometricAuthorizeFragment()
            val args = Bundle()
            //args.putString(Constants.FLOW, flow);
            fragment.arguments = args
            return fragment
        }

    }

    override fun onResume() {
        super.onResume()
        (getActivity() as ProfileCompletionActivity?)!!.setToolBarTitle("Set your Fingerprint")
    }

    override fun callDependencyInjector(injectorComponent: DependencyInjectorComponent?) {
        injectorComponent?.injectDependencies(this)
    }

    override fun onCreateKnifeView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return LayoutInflater.from(container!!.context).inflate(R.layout.fragment_fingerprint_info, container, false)
    }

    override fun getBaseView(): BaseView? {
       return null
    }

}