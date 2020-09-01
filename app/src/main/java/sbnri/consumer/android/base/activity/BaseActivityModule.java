package sbnri.consumer.android.base.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import sbnri.consumer.android.qualifiers.ActivityContext;
import sbnri.consumer.android.scopes.BaseActivityScope;

@Module
public class BaseActivityModule {

    private final Activity activity;

    BaseActivityModule(Activity activity) {
        this.activity = activity;
    }

    @BaseActivityScope
    @Provides
    public Activity activity() {
        return activity;
    }

    @ActivityContext
    @Provides
    Context activityContext() {
        return activity;
    }


}
