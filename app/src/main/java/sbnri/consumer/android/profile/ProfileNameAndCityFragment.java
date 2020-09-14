package sbnri.consumer.android.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sbnri.consumer.android.DependencyInjectorComponent;
import sbnri.consumer.android.R;
import sbnri.consumer.android.base.activity.BaseFragment;
import sbnri.consumer.android.base.contract.BaseView;

public class ProfileNameAndCityFragment extends BaseFragment {

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
}
