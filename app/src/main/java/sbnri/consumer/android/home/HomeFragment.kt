package sbnri.consumer.android.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import sbnri.consumer.android.DependencyInjectorComponent
import sbnri.consumer.android.R
import sbnri.consumer.android.base.activity.BaseActivityComponent
import sbnri.consumer.android.base.activity.BaseFragment
import sbnri.consumer.android.base.contract.BaseView

class HomeFragment : BaseFragment() ,HomeContract.HomeFragmentView{



    override fun initView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showToastMessage(toastMessage: String?, isErrortoast: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
    init {

    }



    override fun getBaseView(): BaseView {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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