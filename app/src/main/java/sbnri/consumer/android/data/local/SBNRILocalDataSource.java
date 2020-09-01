package sbnri.consumer.android.data.local;

import android.content.Context;

import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.room.Room;
import io.reactivex.Flowable;
import sbnri.consumer.android.base.schedulers.SchedulerProvider;
import sbnri.consumer.android.data.source.SBNRIDataSource;
import sbnri.consumer.android.webservice.model.SBNRIResponse;

public class SBNRILocalDataSource implements SBNRIDataSource {


    private final SchedulerProvider schedulerProvider;
    public SBNRILocalDataSource(@NonNull Context context,
                                  @NonNull SchedulerProvider schedulerProvider) {


        this.schedulerProvider = schedulerProvider;
    }

    @Override
    public Flowable<SBNRIResponse> getAllNews(HashMap<String, Object> params) {
        return null;
    }
}
