package sbnri.consumer.android.base.contract;

import android.content.Context;

import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import sbnri.consumer.android.base.schedulers.SchedulerProvider;
import sbnri.consumer.android.data.source.SBNRIDataSource;
import sbnri.consumer.android.webservice.ApiCallbacks;
import sbnri.consumer.android.webservice.model.SBNRIResponse;

public class BasePresenterImp implements ApiCallbacks {

    @NonNull
    protected SBNRIDataSource mSbnriDataSource;

    @NonNull
    protected SchedulerProvider mSchedulerProvider;

    @NonNull
    protected final CompositeDisposable compositeDisposable;

    protected final BaseView baseView;


    protected final Context context;



    public BasePresenterImp(@NonNull SBNRIDataSource sbnriDataSource, @NonNull SchedulerProvider schedulerProvider, BaseView baseView, Context context) {
        mSbnriDataSource = sbnriDataSource;
        mSchedulerProvider = schedulerProvider;
        compositeDisposable = new CompositeDisposable();
        this.baseView = baseView;
        this.context = context;
    }


    @Override
    public void onSessionExpired() {

    }

    @Override
    public void onSuccess(String callTag, SBNRIResponse response, HashMap<String, Object> extras) {

    }

    @Override
    public void onFailure(String callTag, SBNRIResponse response, HashMap<String, Object> extras) {

    }

    @Override
    public void onError(String callTag, Throwable e, HashMap<String, Object> extras) {

    }

    @Override
    public void deleteSubscriberOnComplete(String callTag, @Nullable Disposable disposable) {

    }
}
