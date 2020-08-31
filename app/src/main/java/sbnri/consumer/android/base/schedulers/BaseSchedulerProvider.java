package sbnri.consumer.android.base.schedulers;

/**
 * Created by swapnull on 06/12/16.
 */

import androidx.annotation.NonNull;
import io.reactivex.Scheduler;


/**
 * Allow providing different types of {@link Scheduler}s.
 */
interface BaseSchedulerProvider {

    @NonNull
    Scheduler computation();

    @NonNull
    Scheduler io();

    @NonNull
    Scheduler ui();

}