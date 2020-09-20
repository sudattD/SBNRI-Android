package sbnri.consumer.android.profile;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;
import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import androidx.fragment.app.FragmentManager;
import butterknife.BindView;
import butterknife.OnClick;
import sbnri.consumer.android.DependencyInjectorComponent;
import sbnri.consumer.android.R;
import sbnri.consumer.android.base.activity.BaseFragmentActivity;
import sbnri.consumer.android.base.contract.BaseView;
import sbnri.consumer.android.constants.Constants;
import sbnri.consumer.android.data.models.UserDetails;
import sbnri.consumer.android.util.ActivityUtils;
import sbnri.consumer.android.util.FragmentUtils;
import sbnri.consumer.android.util.PermissionUtils;

public class ProfileCompletionActivity extends BaseFragmentActivity {


    public static Bitmap bitmap = null;
    private final int SELECT_FILE = 2;


    @BindView(R.id.profile_image)
    ImageView profile_image;

    @BindView(R.id.toolbar_title)
    TextView toolBarTitle;


    UserDetails mUserDetails;

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
        Picasso.get().load(mUserDetails.getPhotoURL()).into(profile_image);
         FragmentManager  fragmentManager = getSupportFragmentManager();
        FragmentUtils.addFragment(fragmentManager, ProfileNameAndCityFragment.newInstance(), getContainerId(), false);

    }

    @Override
    protected int getContainerId() {
        return R.id.fragmentContainer;
    }

    @Override
    protected BaseView getBaseView() {
        return null;
    }

    @Override
    protected void callDependencyInjector(DependencyInjectorComponent injectorComponent) {
        injectorComponent.injectDependencies(this);
    }


    @OnClick({R.id.profile_image,R.id.iv_edit})
    public void clickedGallery(View view)
    {
        galleryIntent();
     //   runNetworkDependentTask(() -> PermissionUtils.checkPermissionsAndRun(this, Constants.CAMERA_PERMISSIONS, this::galleryIntent), null);
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
        Bitmap bm;
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

    }


    public void setToolBarTitle(String title)
    {
        toolBarTitle.setText(title);
    }
}
