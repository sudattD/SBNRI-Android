package sbnri.consumer.android.profile.contract;

import android.content.Context;
import android.graphics.Bitmap;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import sbnri.consumer.android.base.contract.BasePresenterImp;
import sbnri.consumer.android.base.contract.BaseView;
import sbnri.consumer.android.base.schedulers.SchedulerProvider;
import sbnri.consumer.android.data.source.SBNRIDataSource;

public interface ProfileCompletionActivityContract {

    interface View extends BaseView {

        void navigateToPlayStore(String resp);
    }

    abstract class Presenter extends BasePresenterImp
    {


        public Presenter(@NonNull SBNRIDataSource sbnriDataSource, @NonNull SchedulerProvider schedulerProvider, BaseView baseView, Context context) {
            super(sbnriDataSource, schedulerProvider, baseView, context);
        }


        abstract void getS3BucketForImageUpload(Bitmap bitmap);
    }
}
