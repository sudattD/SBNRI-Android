package sbnri.consumer.android.webservice.consumer;

import java.util.ArrayList;
import java.util.HashMap;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import sbnri.consumer.android.data.models.AllBanksData;
import sbnri.consumer.android.data.models.GenerateUploadUrl;
import sbnri.consumer.android.data.models.UserDetails;
import sbnri.consumer.android.webservice.model.SBNRIResponse;

public interface ApiService {


    @GET("get_all_news")
    Flowable<SBNRIResponse> getAllNews(@QueryMap HashMap<String, Object> params);

    @POST("verify_id_token")
    Flowable<SBNRIResponse<UserDetails>> verifyFireBaseIdToken(@Body HashMap<String, Object> params);

    @GET("get_all_banks_data")
    Flowable<SBNRIResponse<AllBanksData>> getAllBanksData();

    @POST("generate_link_for_email")
    Flowable<SBNRIResponse> generateLinkForEmail(@Body HashMap<String, Object> params);


    @POST("generate_upload_url")
    Flowable<SBNRIResponse<GenerateUploadUrl>> getFilePath(@Body HashMap<String, Object> params); // marked

    @PUT
    Completable uploadFileOnAmazon(@Header("Content-Length") long length, @Url String url, @Body RequestBody image);

}
