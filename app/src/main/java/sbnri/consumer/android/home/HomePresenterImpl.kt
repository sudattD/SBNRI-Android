package sbnri.consumer.android.home

import android.content.Context
import sbnri.consumer.android.base.contract.BaseView
import sbnri.consumer.android.base.schedulers.SchedulerProvider
import sbnri.consumer.android.data.local.SBNRILocalDataSource
import sbnri.consumer.android.data.local.SBNRIPref
import sbnri.consumer.android.data.source.SBNRIDataSource
import sbnri.consumer.android.qualifiers.ApplicationContext
import sbnri.consumer.android.qualifiers.SBNRIRepositoryQualifier
import javax.inject.Inject

class HomePresenterImpl @Inject constructor(@SBNRIRepositoryQualifier val sbnriDataSource: SBNRIDataSource,
                                            schedulerProvider: SchedulerProvider, baseView: BaseView,
                                            sbnriPref: SBNRIPref,
                                            @ApplicationContext context: Context
                                            ): HomeContract.HomePresenter(sbnriDataSource, schedulerProvider,baseView,context) {



    private val fragmentView: HomeContract.HomeFragmentView? = baseView as HomeContract.HomeFragmentView
    private var activityView: HomeContract.HomeActivityView? = baseView as HomeContract.HomeActivityView




    override fun setHomeActivityInstance(homeActivityView: HomeContract.HomeActivityView?) {
        this.activityView = homeActivityView
    }

}