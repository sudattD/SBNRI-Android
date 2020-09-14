package sbnri.consumer.android.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.hawk.Hawk;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.OnClick;
import sbnri.consumer.android.DependencyInjectorComponent;
import sbnri.consumer.android.R;
import sbnri.consumer.android.accountflow.ShowBanksListActivity;
import sbnri.consumer.android.base.activity.BaseActivity;
import sbnri.consumer.android.base.activity.BaseActivityComponent;
import sbnri.consumer.android.base.activity.BaseFragment;
import sbnri.consumer.android.base.contract.BaseView;
import sbnri.consumer.android.data.models.AllBanksData;
import sbnri.consumer.android.data.models.Bank;
import sbnri.consumer.android.data.models.SubBank;
import sbnri.consumer.android.data.models.UserDetails;
import sbnri.consumer.android.databinding.HomeFragmentBinding;
import sbnri.consumer.android.qualifiers.HomeFragmentPresenter;
import sbnri.consumer.android.util.GeneralUtilsKt;

public class HomeFragmentJ extends BaseFragment implements HomeContract.HomeFragmentView {


    @HomeFragmentPresenter
    @Inject
    HomePresenterImplJ presenter;

    HomeFragmentBinding binding;

    UserDetails mUserDetails;


    ArrayList<Bank> mPreferredBanksMetaData; //the size will always be one although it is a list
    ArrayList<Bank> mOthersaBanksMetaData; //the size will always be one although it is a list


    Bank mPreferredBank;
    Bank mOthersaBank;
    List<SubBank> mPreferredBanksList = new ArrayList<>();
    List<SubBank> mOthersBanksList = new ArrayList<>();

    List<SubBank> allSubBanks = new ArrayList<>();

    AllBanksData allBanks = null;
    @Override
    protected BaseView getBaseView() {
        return this;
    }

    @Override
    protected void callDependencyInjector(DependencyInjectorComponent injectorComponent) {
            //nothing
    }

    @Override
    public View onCreateKnifeView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = HomeFragmentBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        return view;
    }

    public static HomeFragmentJ newInstance() {
        HomeFragmentJ fragment = new HomeFragmentJ();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
        setBaseActivityPresenter(presenter);
    }

    @Override
    public void initView() {

        mUserDetails = Hawk.get("UserDetails");

        Picasso.get().load(mUserDetails.getPhotoURL()).into(binding.profileImage);
        binding.userName.setText(mUserDetails.getFirstName());

        presenter.getAllBanksData();
    }

    @Override
    public void showProgress() {

        ((BaseActivity) getActivity()).rl_progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        ((BaseActivity) getActivity()).rl_progress.setVisibility(View.GONE);
    }

    @Override
    public void showToastMessage(String toastMessage, boolean isErrortoast) {

    }

    @Override
    protected void initialiseDaggerDependencies(BaseActivityComponent activityComponent) {
        DaggerHomeComponent.builder()
                .homeModuleJ(new HomeModuleJ(this))
                .baseActivityComponent(activityComponent)
                .build().injectDependencies(this);

    }


    @Override
    public void showAllBankData(List<Bank> bankList, AllBanksData banksData) {
        //binding.cardBanks.tvSuccess.setText("SUCCESS / TEST");
        mPreferredBank = GeneralUtilsKt.fetchFirstElementOfPreferredBank(bankList);
        mOthersaBank = GeneralUtilsKt.fetchFirstElementOfOthersBank(bankList);

        mPreferredBanksMetaData = GeneralUtilsKt.singleElementAsArrayList(mPreferredBank);
        mOthersaBanksMetaData = GeneralUtilsKt.singleElementAsArrayList(mOthersaBank);

        mOthersBanksList = GeneralUtilsKt.fetchOthersBanksFromAllBanks(bankList);
        mPreferredBanksList = GeneralUtilsKt.fetchPreferredBanksFromAllBanks(bankList);
        this.allBanks = banksData;
       // allBanks = GeneralUtilsKt.fetchAllBanks(bankList);
    }

    @OnClick(R.id.btn_open_nri_accnt)
    public void openBankListActivity()
    {
        context.startActivity(ShowBanksListActivity.createInstance(context,mPreferredBanksMetaData,
                mOthersaBanksMetaData,
                new ArrayList<>(mPreferredBanksList),
                new ArrayList<>(mOthersBanksList),new ArrayList<>(allSubBanks),
                mPreferredBank,
                mOthersaBank,
                allBanks));
       // Intent intent = new Intent()
        //startActivity();
    }


}
