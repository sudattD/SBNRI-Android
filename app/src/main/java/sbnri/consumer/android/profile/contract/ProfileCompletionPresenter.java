package sbnri.consumer.android.profile.contract;

import android.content.Context;
import android.graphics.Bitmap;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.UUID;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import sbnri.consumer.android.base.contract.BaseView;
import sbnri.consumer.android.base.schedulers.SchedulerProvider;
import sbnri.consumer.android.data.local.SBNRIPref;
import sbnri.consumer.android.data.local.SBNRIRepository;
import sbnri.consumer.android.data.source.SBNRIDataSource;
import sbnri.consumer.android.fileUpload.AttachFile;
import sbnri.consumer.android.qualifiers.ApplicationContext;
import sbnri.consumer.android.qualifiers.SBNRIRepositoryQualifier;
import sbnri.consumer.android.scopes.ActivityScope;
import sbnri.consumer.android.util.NetworkUtils;
import sbnri.consumer.android.webservice.ApiCallTags;
import sbnri.consumer.android.webservice.model.SBNRIResponse;

@ActivityScope
public class ProfileCompletionPresenter extends ProfileCompletionActivityContract.Presenter {


    private final ProfileCompletionActivityContract.View view;
    private final SBNRIPref sbnriPref;
    @Inject
    public ProfileCompletionPresenter(@SBNRIRepositoryQualifier @NonNull SBNRIDataSource sbnriDataSource, @NonNull SchedulerProvider schedulerProvider, BaseView baseView, @ApplicationContext Context context,SBNRIPref sbnriPref) {
        super(sbnriDataSource, schedulerProvider, baseView, context);
        this.view = (ProfileCompletionActivityContract.View) baseView;
        this.sbnriPref = sbnriPref;
    }

    @Override
    public void getS3BucketForImageUpload(Bitmap bitmap) {

     /*   HashMap<String, Object> params = new HashMap<>();
        params.put("page",1);
        params.put("last_hash_id",0);
*/

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH);
        String title = sdf.format(new Date());

        title = title + String.valueOf(Math.round(Math.random()));
        sbnriPref.putImage("SBNRI", title, bitmap);

        final AttachFile file = new AttachFile();

        String uuid = UUID.randomUUID().toString();
        file.setId(2);
        file.setFilePath(uuid + "/" + title);
        file.setLocalFilePath(sbnriPref.getSavedImagePath());
        file.setFileName(title);
        file.setType("profile_pic");
        file.setContentType("*jpeg");

        file.upload((SBNRIRepository) mSbnriDataSource, mSchedulerProvider, view, (BaseView.UploadImage) view);

        // NetworkUtils.makeNetworkCall(ApiCallTags.GET_S3_BUCKET_URL, mSbnriDataSource.getAllNews(params), mSchedulerProvider, this);



    }

    @Override
    public void onSessionExpired() {
        super.onSessionExpired();
    }

    @Override
    public void onSuccess(String callTag, SBNRIResponse response, HashMap<String, Object> extras) {
        super.onSuccess(callTag, response, extras);
    }

    @Override
    public void onFailure(String callTag, SBNRIResponse response, HashMap<String, Object> extras) {
        super.onFailure(callTag, response, extras);
    }

    @Override
    public void onError(String callTag, Throwable e, HashMap<String, Object> extras) {
        super.onError(callTag, e, extras);
    }

}
