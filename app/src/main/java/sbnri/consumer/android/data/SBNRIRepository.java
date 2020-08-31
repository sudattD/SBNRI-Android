package sbnri.consumer.android.data;

import sbnri.consumer.android.data.source.SBNRIDataSource;
import sbnri.consumer.android.webservice.consumer.ApiService;

public class SBNRIRepository implements ApiService {

    private final ApiService service;
    private final SBNRIDataSource mLocalDataSource;

    public SBNRIRepository(ApiService apiService, SBNRIDataSource localDataSource) {
        service = apiService;
        this.mLocalDataSource = localDataSource;
    }
}
