package sbnri.consumer.android.data.source;

import sbnri.consumer.android.webservice.consumer.ApiService;

public class SBNRIRepository implements ApiService {

    private final ApiService service;
    private final SBNRIDataSource mLocalDataSource;

    public ServifyRepository(ApiService apiService, SBNRIDataSource localDataSource) {
        service = apiService;
        this.mLocalDataSource = localDataSource;
    }
}
