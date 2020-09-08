package sbnri.consumer.android.home;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import sbnri.consumer.android.base.contract.BasePresenterImp;
import sbnri.consumer.android.base.contract.BaseView;
import sbnri.consumer.android.base.schedulers.SchedulerProvider;
import sbnri.consumer.android.data.local.SBNRILocalDataSource;
import sbnri.consumer.android.data.source.SBNRIDataSource;


/**
 * Created by yashThakur on 23/12/16.
 */

public interface HomeContract {

    interface HomeFragmentView extends BaseView {

    }


    interface HomeActivityView extends BaseView {
        void showNotificationStatus(int unreadCount);
    }

    abstract class HomePresenter extends BasePresenterImp {

        HomePresenter(@NonNull SBNRIDataSource sbnriDataSource, @NonNull SchedulerProvider schedulerProvider, BaseView baseView, Context context) {
            super(sbnriDataSource, schedulerProvider, baseView, context);
        }

        abstract void setHomeActivityInstance(HomeActivityView homeActivityView);
    }


}
