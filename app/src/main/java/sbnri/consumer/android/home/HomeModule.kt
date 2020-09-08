package sbnri.consumer.android.home

import android.content.Context
import dagger.Module
import dagger.Provides
import sbnri.consumer.android.base.contract.BaseView
import sbnri.consumer.android.base.schedulers.SchedulerProvider
import sbnri.consumer.android.data.local.SBNRILocalDataSource
import sbnri.consumer.android.data.local.SBNRIPref
import sbnri.consumer.android.data.source.SBNRIDataSource
import sbnri.consumer.android.qualifiers.*

@Module
class HomeModule(val baseView: BaseView) {

    @HomeActivityPresenter
    @Provides
    internal fun getHomeActivityPresenter(@SBNRIRepositoryQualifier dataSource: SBNRIDataSource, @LocalDataSource sbnriLocalDataSource: SBNRILocalDataSource, schedulerProvider: SchedulerProvider, @ApplicationContext context: Context, sbnriPref: SBNRIPref): HomePresenterImpl {
        return HomePresenterImpl(dataSource, schedulerProvider,baseView,sbnriPref,context)
    }

    @HomeFragmentPresenter
    @Provides
    internal fun getHomeFragmentPresenter(@SBNRIRepositoryQualifier dataSource: SBNRIDataSource, @LocalDataSource sbnriLocalDataSource: SBNRILocalDataSource, schedulerProvider: SchedulerProvider, @ApplicationContext context: Context, sbnriPref: SBNRIPref): HomePresenterImpl {
        return HomePresenterImpl(dataSource,schedulerProvider,baseView,sbnriPref,context)
    }

}