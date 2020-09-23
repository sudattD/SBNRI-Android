package sbnri.consumer.android.data.local;

import android.content.Context;

import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.room.Room;
import io.reactivex.Flowable;
import sbnri.consumer.android.base.schedulers.SchedulerProvider;
import sbnri.consumer.android.data.models.AllBanksData;
import sbnri.consumer.android.data.models.UserDetails;
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

    @Override
    public Flowable<SBNRIResponse<UserDetails>> verifyFireBaseIdToken(HashMap<String, Object> params) {
        return null;
    }

    @Override
    public Flowable<SBNRIResponse<AllBanksData>> getAllBanksData() {
        return null;
    }

    @Override
    public Flowable<SBNRIResponse> generateLinkForEmail(HashMap<String, Object> params) {
        return null;
    }
}
