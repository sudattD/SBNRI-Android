package sbnri.consumer.android.profile;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.orhanobut.hawk.Hawk;

import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnLongClick;
import sbnri.consumer.android.DependencyInjectorComponent;
import sbnri.consumer.android.R;
import sbnri.consumer.android.base.activity.BaseFragment;
import sbnri.consumer.android.base.contract.BaseView;
import sbnri.consumer.android.constants.Constants;
import sbnri.consumer.android.data.models.UserDetails;
import sbnri.consumer.android.util.FragmentUtils;
import sbnri.consumer.android.util.extensions.KotlinExtensionsKt;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class ProfileNameAndCityFragment extends BaseFragment {

    UserDetails mUserDetails;

    @BindView(R.id.etName)
    EditText etName;

    @BindView(R.id.etLastName)
    EditText etLastName;

    @BindView(R.id.etCityName)
    EditText etCityName;

    @BindView(R.id.btnContinue)
    Button btnContinue;

    int AUTOCOMPLETE_REQUEST_CODE = 1;

    public static ProfileNameAndCityFragment newInstance() {
        ProfileNameAndCityFragment fragment = new ProfileNameAndCityFragment();
        Bundle args = new Bundle();
        //args.putString(Constants.FLOW, flow);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    protected BaseView getBaseView() {
        return null;
    }

    @Override
    protected void callDependencyInjector(DependencyInjectorComponent injectorComponent) {

        injectorComponent.injectDependencies(this);
    }

    @Override
    public View onCreateKnifeView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_name_city,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
    }

    private void initView() {

        KotlinExtensionsKt.disable(btnContinue);
        mUserDetails = Hawk.get(Constants.SBNRI_USER_OBJ);

        etLastName.setText(mUserDetails.getLastname());
        etName.setText(mUserDetails.getFirstName());
        etCityName.setText(mUserDetails.getLocation().getCityName());

        if(!TextUtils.isEmpty(etLastName.getText()) &&
                !TextUtils.isEmpty(etName.getText()) &&
                        !TextUtils.isEmpty(etCityName.getText()))
        {
            KotlinExtensionsKt.enable(btnContinue);
        }
    }


    @OnClick(R.id.etCityName)
    public void onSearchCalled() {
        // Set the fields to specify which types of place data to return.


        // Set the fields to specify which types of place data to
        // return after the user has made a selection.
        List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME);

        // Start the autocomplete intent.
        Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
                .build(context);
        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
    }





    @OnClick(R.id.btnContinue)
    public void btnContinue()
    {

        FragmentUtils.replaceFragment(this,ProfileMPinFragment.newInstance(),true);
    }


    @Override
    public void onResume() {
        super.onResume();
        ((ProfileCompletionActivity)  getActivity()).setToolBarTitle("Profile");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
             //   Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                Status status = Autocomplete.getStatusFromIntent(data);
              //  Log.i(TAG, status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
