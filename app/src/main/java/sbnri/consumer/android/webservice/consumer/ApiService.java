package sbnri.consumer.android.webservice.consumer;

import java.util.HashMap;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import sbnri.consumer.android.webservice.model.SBNRIResponse;

public interface ApiService {


    @GET("get_all_news")
    Flowable<SBNRIResponse> getAllNews(@QueryMap HashMap<String, Object> params);
}
