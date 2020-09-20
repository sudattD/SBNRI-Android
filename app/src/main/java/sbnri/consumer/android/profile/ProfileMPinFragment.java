package sbnri.consumer.android.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;
import sbnri.consumer.android.DependencyInjectorComponent;
import sbnri.consumer.android.R;
import sbnri.consumer.android.base.activity.BaseFragment;
import sbnri.consumer.android.base.contract.BaseView;

public class ProfileMPinFragment extends BaseFragment {


    @BindView(R.id.btnContinue)
    Button btnContinue;

    public static ProfileMPinFragment newInstance() {
        ProfileMPinFragment fragment = new ProfileMPinFragment();
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
        return LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_mpin_password,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
    }

    private void initView() {

        ((ProfileCompletionActivity)  getActivity()).setToolBarTitle("Set Mobile Pin");

    }


    @OnClick(R.id.btnContinue)
    public void btnContinue()
    {

    }
}