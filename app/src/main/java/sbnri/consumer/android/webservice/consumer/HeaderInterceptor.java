package sbnri.consumer.android.webservice.consumer;

        import android.content.Context;
        import android.text.TextUtils;

        import java.io.IOException;

        import javax.inject.Inject;

        import androidx.annotation.NonNull;
        import okhttp3.Interceptor;
        import okhttp3.Request;
        import okhttp3.Response;
        import sbnri.consumer.android.BuildConfig;
        import sbnri.consumer.android.R;
        import sbnri.consumer.android.constants.Constants;
        import sbnri.consumer.android.data.local.SBNRIPref;
        import sbnri.consumer.android.qualifiers.ApplicationContext;
        import sbnri.consumer.android.util.AuthorizationUtils;
        import sbnri.consumer.android.util.DateTimeUtils;

public class HeaderInterceptor implements Interceptor {

    private final Context context;
    private final SBNRIPref sbnriPref;

    @Inject
    public HeaderInterceptor(@ApplicationContext Context context, SBNRIPref sbnriPref) {
        this.context = context;
        this.sbnriPref = sbnriPref;
    }


    @Override
    public Response intercept(@NonNull Chain chain) {
        Request request = chain.request();

        Request.Builder builder = request.newBuilder();


        // put request on s3 fails if two authorization used
        if (!request.method().equalsIgnoreCase("put")) {
            String accessToken = "Bearer 95ea95098e540370853ccd5bc1b944681ac6ba55";
            if (!TextUtils.isEmpty(accessToken))
                builder.addHeader("Authorization", accessToken);
                builder.addHeader("X-DEVICE","1");
                builder.addHeader("X-USERNAME","vbagaria");

        }
        //Bearer fe9e06313a5d46dcbd32c991123d42d141cc9d5c
        //Bearer 95ea95098e540370853ccd5bc1b944681ac6ba55

        request = builder.build();

      //  Logger.d(new Gson().toJson(request.headers()));
        Response response = null;
        try {
            response = chain.proceed(request);
        } catch (IOException e) {
           // Logger.d(e.getMessage());
        } catch (Exception e) {
           // Logger.d(e.getMessage());
        }
        return response;
    }


}
