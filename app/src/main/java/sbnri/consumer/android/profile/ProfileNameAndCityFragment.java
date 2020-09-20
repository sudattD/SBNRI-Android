package sbnri.consumer.android.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.orhanobut.hawk.Hawk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;
import sbnri.consumer.android.DependencyInjectorComponent;
import sbnri.consumer.android.R;
import sbnri.consumer.android.base.activity.BaseFragment;
import sbnri.consumer.android.base.contract.BaseView;
import sbnri.consumer.android.constants.Constants;
import sbnri.consumer.android.data.models.UserDetails;
import sbnri.consumer.android.util.FragmentUtils;

public class ProfileNameAndCityFragment extends BaseFragment {

    UserDetails mUserDetails;

    @BindView(R.id.etName)
    EditText etName;

    @BindView(R.id.etLastName)
    EditText etLastName;

    @BindView(R.id.btnContinue)
    Button btnContinue;



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

        mUserDetails = Hawk.get(Constants.SBNRI_USER_OBJ);

        etLastName.setText(mUserDetails.getLastname());
        etName.setText(mUserDetails.getFirstName());
    }


    @OnClick(R.id.btnContinue)
    public void btnContinue()
    {

        FragmentUtils.replaceFragment(this,ProfileMPinFragment.newInstance(),true);
    }
}
