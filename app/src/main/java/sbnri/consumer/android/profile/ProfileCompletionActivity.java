package sbnri.consumer.android.profile;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import javax.inject.Inject;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import butterknife.BindView;
import butterknife.OnClick;
import sbnri.consumer.android.DependencyInjectorComponent;
import sbnri.consumer.android.R;
import sbnri.consumer.android.base.activity.BaseFragmentActivity;
import sbnri.consumer.android.base.contract.BaseView;
import sbnri.consumer.android.constants.Constants;
import sbnri.consumer.android.data.models.UserDetails;
import sbnri.consumer.android.profile.contract.ProfileCompletionActivityContract;
import sbnri.consumer.android.profile.contract.ProfileCompletionPresenter;
import sbnri.consumer.android.util.ActivityUtils;
import sbnri.consumer.android.util.FragmentUtils;
import sbnri.consumer.android.util.PermissionUtils;
import sbnri.consumer.android.util.extensions.KotlinExtensionsKt;

public class ProfileCompletionActivity extends BaseFragmentActivity implements ProfileCompletionActivityContract.View, BaseView.UploadImage {


    public static Bitmap bitmap = null;
    private final int SELECT_FILE = 2;


    @BindView(R.id.profile_image)
    ImageView profile_image;

    @BindView(R.id.toolbar_title)
    TextView toolBarTitle;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Inject
    ProfileCompletionPresenter presenter;

    UserDetails mUserDetails;

    Bitmap bm = null;
    public static Intent createInstance(Context context)
    {
        Intent intent = new Intent(context, ProfileCompletionActivity.class);

        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_profile_completion);
        baseToolbar.setVisibility(View.GONE);

        mUserDetails = Hawk.get(Constants.SBNRI_USER_OBJ);
        if(!KotlinExtensionsKt.isNullTrue(mUserDetails))
        {
            Picasso.get().load(mUserDetails.getPhotoURL()).into(profile_image);
        }

         FragmentManager  fragmentManager = getSupportFragmentManager();
        FragmentUtils.addFragment(fragmentManager, ProfileNameAndCityFragment.newInstance(), getContainerId(), false);



    }

    private void getS3BucketURL(Bitmap bm) {

        if(bm!=null)
        presenter.getS3BucketForImageUpload(bm);
    }

    @Override
    protected int getContainerId() {
        return R.id.fragmentContainer;
    }

    @Override
    protected BaseView getBaseView() {
        return this;
    }

    @Override
    protected void callDependencyInjector(DependencyInjectorComponent injectorComponent) {
        injectorComponent.injectDependencies(this);
    }


    @OnClick({R.id.profile_image,R.id.iv_edit})
    public void clickedGallery(View view)
    {
       // galleryIntent();
        runNetworkDependentTask(() -> PermissionUtils.checkPermissionsAndRun(this, Constants.CAMERA_PERMISSIONS, this::galleryIntent), null);
    }

    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }



    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            runNetworkDependentTask(() -> {
                if (requestCode == SELECT_FILE) {
                    onSelectFromGalleryResult(data);
                }/* else if (requestCode == REQUEST_CAMERA) {
                    onCaptureImageResult(data);
                }*/
            }, null);
        }
    }

    private void onSelectFromGalleryResult(Intent data) {

        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(context.getContentResolver(), data.getData());
                if (bm != null) {
                    bm = ActivityUtils.getResizedBitmap(bm, Constants.COMPRESSED_IMAGE_WIDTH);
                    setResultAndFinish(bm);
                } else {
                    //showToastMessage(getString(R.string.unable_to_get_image), true);
                }
            } catch (IOException e) {
                Logger.d(e.getLocalizedMessage());
            }
        } else {
            //showToastMessage(getString(R.string.unable_to_get_image), true);
        }
    }

    private void setResultAndFinish(Bitmap bm) {
        bitmap = bm;
        setResult(Activity.RESULT_OK);

        profile_image.setImageBitmap(bitmap);
        getS3BucketURL(bitmap);
    }


    public void setToolBarTitle(String title)
    {
        toolBarTitle.setText(title);
    }

    @Override
    public void navigateToPlayStore(String resp) {

    }

    @Override
    public void initView() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showToastMessage(String toastMessage, boolean isErrortoast) {
        sbnriToast(toastMessage, Toast.LENGTH_LONG,isErrortoast);
    }

    @Override
    public void accessTokenExpired() {

    }

    @Override
    public void updateImageAfterUpload(String path) {

        progressBar.setProgress(100,true);

        //(new Handler()).postDelayed(this::waitMethod, 500);
        //progressBar.se

    }

    private void waitMethod()
    {

       // progressBar.setProgress(ContextCompat.getDrawable(context,R.drawable.circular_progress_finish_state));

    }

    @Override
    public Context getAppContext() {
        return context.getApplicationContext();
    }
}
