package sbnri.consumer.android.data.local;

import java.util.HashMap;

import io.reactivex.Flowable;
import sbnri.consumer.android.data.source.SBNRIDataSource;
import sbnri.consumer.android.webservice.consumer.ApiService;
import sbnri.consumer.android.webservice.model.SBNRIResponse;

public class SBNRIRepository implements SBNRIDataSource {

    private final ApiService service;
    private final SBNRIDataSource mLocalDataSource;


    public SBNRIRepository(ApiService apiService, SBNRIDataSource localDataSource) {
        service = apiService;
        this.mLocalDataSource = localDataSource;
    }

    @Override
    public Flowable<SBNRIResponse> getAllNews(HashMap<String, Object> params) {
        return service.getAllNews(params);
    }
}