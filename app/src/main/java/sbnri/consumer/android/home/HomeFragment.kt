package sbnri.consumer.android.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.orhanobut.hawk.Hawk
import com.squareup.picasso.Picasso
import sbnri.consumer.android.DependencyInjectorComponent
import sbnri.consumer.android.R
import sbnri.consumer.android.SBNRIApp
import sbnri.consumer.android.base.activity.BaseActivityComponent
import sbnri.consumer.android.base.activity.BaseFragment
import sbnri.consumer.android.base.contract.BaseView
import sbnri.consumer.android.data.models.UserDetails
import sbnri.consumer.android.databinding.HomeFragmentBinding

class HomeFragment : BaseFragment() ,HomeContract.HomeFragmentView{


   // val picasso : Picasso = (appContext as SBNRIApp).getComponent().getPicasso()

    private var _binding: HomeFragmentBinding? = null
     lateinit var userDetails: UserDetails

    override fun initView() {

        userDetails = Hawk.get("UserDetails");
        Picasso.get().load(userDetails.photoURL)
                .into(_binding?.profileImage)
        _binding?.userName?.setText(userDetails.firstName +" "+ userDetails.lastname)

    }

    override fun showProgress() {
    }

    override fun hideProgress() {
    }

    override fun showToastMessage(toastMessage: String?, isErrortoast: Boolean) {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    companion object
    {
        fun newInstance(): HomeFragment {
            val fragment = HomeFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = HomeFragmentBinding.inflate(inflater,container,false)
        val view = this._binding!!.root

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun getBaseView(): BaseView {
        return this
    }

    override fun callDependencyInjector(injectorComponent: DependencyInjectorComponent?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateKnifeView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        return inflater?.inflate(R.layout.home_fragment,container,false)!!
    }

    override fun initialiseDaggerDependencies(activityComponent: BaseActivityComponent) {
        DaggerHomeComponent.builder()
                .homeModule(HomeModule(this))
                .baseActivityComponent(activityComponent)
                .build().injectDependencies(this)
    }
}