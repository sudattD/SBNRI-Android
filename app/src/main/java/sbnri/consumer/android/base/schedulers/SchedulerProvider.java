package servify.android.consumer.base.schedulers;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import servify.android.consumer.android.scopes.ApplicationScope;

/**
 * Created by swapnull on 06/12/16.
 */
@ApplicationScope
public class SchedulerProvider implements BaseSchedulerProvider {

    @Inject
    public SchedulerProvider() {
    }

    @NonNull
    public Scheduler computation() {
        return Schedulers.computation();
    }

    @NonNull
    public Scheduler io() {
        return Schedulers.io();
    }

    @NonNull
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }

}
