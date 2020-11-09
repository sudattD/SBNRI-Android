package sbnri.consumer.android.profile;

import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.orhanobut.hawk.Hawk;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnLongClick;
import sbnri.consumer.android.DependencyInjectorComponent;
import sbnri.consumer.android.R;
import sbnri.consumer.android.base.activity.BaseActivity;
import sbnri.consumer.android.base.activity.BaseFragment;
import sbnri.consumer.android.base.contract.BaseView;
import sbnri.consumer.android.constants.Constants;
import sbnri.consumer.android.data.local.SBNRIPref;
import sbnri.consumer.android.data.models.UserDetails;
import sbnri.consumer.android.places.AutoCompletePlace;
import sbnri.consumer.android.places.SearchAndAutoDetectionHelper;
import sbnri.consumer.android.util.ActivityUtils;
import sbnri.consumer.android.util.FragmentUtils;
import sbnri.consumer.android.util.extensions.KotlinExtensionsKt;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class ProfileNameAndCityFragment extends BaseFragment implements SearchAndAutoDetectionHelper.SearchAndAutoDetectionListener{

    private static final int AUTOCOMPLETE_FETCH_ADDRESS_THRESHOLD = 3;
    UserDetails mUserDetails;

    @BindView(R.id.etName)
    EditText etName;

    @BindView(R.id.etLastName)
    EditText etLastName;

    @BindView(R.id.etCityName)
    AutoCompleteTextView etCityName;

    @BindView(R.id.btnContinue)
    Button btnContinue;

    int AUTOCOMPLETE_REQUEST_CODE = 1;

    private Handler handler = new Handler();
    private Runnable runnableFetchAddress;
    int AUTOCOMPLETE_API_HIT_DELAY = 300;

    private String previouslySearchText;

    private List<AutoCompletePlace> resultList = new ArrayList<>();
    ArrayAdapter<AutoCompletePlace> autoCompleteResultAdapter;

    private FusedLocationProviderClient mFusedLocationClient;
    private LocationRequest mLocationRequest;
    protected SearchAndAutoDetectionHelper searchAndAutoDetectionHelper;
    public static final String ADDRESS_REGEX = "[A-Za-z0-9-!@#$%^&*()',:;/ ]*$";

    public static ProfileNameAndCityFragment newInstance() {
        ProfileNameAndCityFragment fragment = new ProfileNameAndCityFragment();
        Bundle args = new Bundle();
        //args.putString(Constants.FLOW, flow);
        fragment.setArguments(args);
        return fragment;
    }

    private final TextWatcher textWatcherLandMark = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String searchText = etCityName.getText().toString().toLowerCase();
            if (isValidAddressForSearchConstraint(searchText, previouslySearchText))
                startDelay(searchText);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    public void startDelay(String text) {
        handler.removeCallbacks(runnableFetchAddress);
        updateFetchAddressRunnable(text);
        handler.postDelayed(runnableFetchAddress, AUTOCOMPLETE_API_HIT_DELAY);
    }

    private void updateFetchAddressRunnable(String text) {
        runnableFetchAddress = () -> {
            previouslySearchText = text;
            getAutocomplete(text);
        };
    }

    private void getAutocomplete(final CharSequence constraint) {
        resultList = new ArrayList<>();
        if (!TextUtils.isEmpty(constraint)) {
            if (searchAndAutoDetectionHelper == null)
                setUpLocationVariables();
            searchAndAutoDetectionHelper.getAutocomplete(constraint);
        }
    }

    private void setUpLocationVariables() {

        searchAndAutoDetectionHelper = SearchAndAutoDetectionHelper.Companion.newInstance( this);
        mFusedLocationClient = new FusedLocationProviderClient(context);
        createLocationRequest();

    }
    public static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10 * 1000; // every 10 secs
    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setFastestInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    private boolean isValidAddressForSearchConstraint(String searchText, String previouslySearchText) {
        return !(TextUtils.isEmpty(searchText)
                || searchText.trim().length() < AUTOCOMPLETE_FETCH_ADDRESS_THRESHOLD
                || searchText.trim().equalsIgnoreCase(previouslySearchText));
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

       // KotlinExtensionsKt.disable(btnContinue);
        mUserDetails = Hawk.get(Constants.SBNRI_USER_OBJ);
        setupAutoCompleteTextView();
        if(!KotlinExtensionsKt.isNullTrue(mUserDetails))
        {
            etLastName.setText(mUserDetails.getLastname());
            etName.setText(mUserDetails.getFirstName());

            if(!KotlinExtensionsKt.isNullTrue(mUserDetails.getLocation()))
            etCityName.setText(mUserDetails.getLocation().getCityName());

        }



        if(!TextUtils.isEmpty(etLastName.getText()) &&
                !TextUtils.isEmpty(etName.getText()) &&
                        !TextUtils.isEmpty(etCityName.getText()))
        {
            KotlinExtensionsKt.enable(btnContinue);
        }
    }

    private void setupAutoCompleteTextView() {

        etCityName.addTextChangedListener(textWatcherLandMark);
        etCityName.setFilters(new InputFilter[]{ActivityUtils.setFilter(ADDRESS_REGEX)});

        autoCompleteResultAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, resultList);
        etCityName.setAdapter(autoCompleteResultAdapter);
        etCityName.setOnItemClickListener((parent, view, position, l) -> {
            Object item = parent.getItemAtPosition(position);
            if (item instanceof AutoCompletePlace) {
                AutoCompletePlace autoCompletePlace = (AutoCompletePlace) item;

                searchAndAutoDetectionHelper.getAddressForAutoCompletePlace(autoCompletePlace);
            }
        });
    }



    @OnClick(R.id.btnContinue)
    public void btnContinue()
    {
        if(!TextUtils.isEmpty(etName.getText()) &&
          !TextUtils.isEmpty(etLastName.getText()) &&
         !TextUtils.isEmpty(etCityName.getText()))
        FragmentUtils.replaceFragment(this,ProfileMPinFragment.newInstance(),true);

        else
            showToastMessage("Please fill all the details",false);

    }


    @Override
    public void onResume() {
        super.onResume();
        ((ProfileCompletionActivity)  getActivity()).setToolBarTitle("Profile");
    }

    @NotNull
    @Override
    public LocationRequest getLocationRequest() {
        return null;
    }

    @Override
    public boolean getRequestingCurrentLocation() {
        return false;
    }

    @Override
    public void setRequestingCurrentLocation(boolean requestingCurrentLocation) {

    }

    @NotNull
    @Override
    public SBNRIPref getSbnriPref() {
        return null;
    }

    @Override
    public void onUnableToStartGps() {

    }

    @Override
    public void startLocationUpdates() {

    }

    @Override
    public void onLocationAutoDetected(double latitude, double longitude, @org.jetbrains.annotations.Nullable List<? extends Address> addresses, @org.jetbrains.annotations.Nullable String pincode) {

    }

    @Override
    public void couldNotAutoDetectLocation() {

    }

    @Override
    public void onPermissionDeniedForever() {

    }

    @Override
    public void showProgress(@NotNull String string) {

    }

    @Override
    public void showToastMessage(@NotNull String string, boolean isError) {
        ((ProfileCompletionActivity)getActivity()).showToastMessage(string,isError);
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onAutoCompletePlacesFetched(@NotNull List<AutoCompletePlace> places) {

        resultList = places;
        if (!resultList.isEmpty()) {
            updateAutoCompleteAdapter();
        }
    }

    private void updateAutoCompleteAdapter() {
        if (autoCompleteResultAdapter != null) {
            autoCompleteResultAdapter.clear();
            autoCompleteResultAdapter.addAll(resultList);
            autoCompleteResultAdapter.notifyDataSetChanged();
            autoCompleteResultAdapter.getFilter().filter(etCityName.getText().toString(), etCityName);
        }
    }

    @Override
    public void onAddressRetrievedById(@NotNull Address addresses) {

        etCityName.setText(addresses.getSubAdminArea());

    }
}
