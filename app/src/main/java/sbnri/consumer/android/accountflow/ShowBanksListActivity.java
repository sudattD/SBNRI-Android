package sbnri.consumer.android.accountflow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import sbnri.consumer.android.DependencyInjectorComponent;
import sbnri.consumer.android.R;
import sbnri.consumer.android.accountflow.viewholders.CustomAdapterAllBanks;
import sbnri.consumer.android.accountflow.viewholders.ShowBanksContract;
import sbnri.consumer.android.accountflow.viewholders.VH_HeaderPreferredBank;
import sbnri.consumer.android.accountflow.viewholders.VH_PreferredBanks;
import sbnri.consumer.android.accountflow.viewholders.VH_viewTypeTest;
import sbnri.consumer.android.adapters.AbstractBetterViewHolder;
import sbnri.consumer.android.adapters.GenericAdapterBuilder;
import sbnri.consumer.android.adapters.GenericRecyclerViewAdapter;
import sbnri.consumer.android.adapters.ItemEncapsulator;
import sbnri.consumer.android.adapters.SimpleGenericRecyclerViewAdapter;
import sbnri.consumer.android.adapters.TypeNotSupportedException;
import sbnri.consumer.android.base.activity.BaseActivity;
import sbnri.consumer.android.base.contract.BaseView;
import sbnri.consumer.android.constants.Constants;
import sbnri.consumer.android.data.models.AllBanksData;
import sbnri.consumer.android.data.models.Bank;
import sbnri.consumer.android.data.models.SubBank;
import sbnri.consumer.android.util.GeneralUtilsKt;

import static sbnri.consumer.android.util.GeneralUtilsKt.encapsulateItems;

public class  ShowBanksListActivity extends BaseActivity implements ShowBanksContract.ShowBanksView , GenericAdapterBuilder.GetViewHolder {



    public static Intent createInstance(Context context, ArrayList<Bank> mPreferredBanksMetaData,
                                        ArrayList<Bank> mOthersaBanksMetaData,
                                        ArrayList<SubBank> mPreferredBanksList,
                                        ArrayList<SubBank> mOthersBanksList,
                                        ArrayList<SubBank> allBanksList,
                                        Bank preferredBankData,
                                        Bank othersBankData,
                                        AllBanksData allBanks)
    {
        Intent intent = new Intent(context, ShowBanksListActivity.class);

        intent.putParcelableArrayListExtra(Constants.PREFERRED_BANKS_META_DATA,mPreferredBanksMetaData);
        intent.putParcelableArrayListExtra(Constants.OTHERS_BANKS_META_DATA,mOthersaBanksMetaData);
        intent.putParcelableArrayListExtra(Constants.PREFERRED_BANK_LIST,mPreferredBanksList);
        intent.putParcelableArrayListExtra(Constants.OTHERS_BANK_LIST,mOthersBanksList);
        intent.putParcelableArrayListExtra(Constants.ALL_BANKS_LIST,allBanksList);
        intent.putExtra(Constants.PREFERRED_BANK_DATA,preferredBankData);
        intent.putExtra(Constants.OTHERS_BANK_DATA,othersBankData);
        intent.putExtra(Constants.ALL_BANKS,allBanks);
        return intent;
    }

    ArrayList<Bank> mPreferredBanksMetaData; //the size will always be one although it is a list
    ArrayList<Bank> mOthersaBanksMetaData; //the size will always be one although it is a list
    ArrayList<SubBank> mPreferredBanksList;
    ArrayList<SubBank> mOthersBanksList;
    ArrayList<SubBank> allBanksList;
    Bank preferredBankData;
    Bank othersBankData;

    AllBanksData allBanksData;

    private CustomAdapterAllBanks customAdapter;

    private GenericRecyclerViewAdapter<ItemEncapsulator> preferredBanksAdapter;
    private SimpleGenericRecyclerViewAdapter<SubBank> subBankViewAdapter;


    @BindView(R.id.rv_preferred_banks)
    RecyclerView rv_preferred_banks;

    @BindView(R.id.rv_other_banks)
    RecyclerView rv_other_banks;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_bank_list);
        baseToolbar.setVisibility(View.GONE);

        getIntentData();
        initView();

    }

    private void getIntentData()
    {
        Intent intent =  getIntent();
        if(intent != null)
        {
            mPreferredBanksMetaData = intent.getParcelableArrayListExtra(Constants.PREFERRED_BANKS_META_DATA);
            mOthersaBanksMetaData = intent.getParcelableArrayListExtra(Constants.OTHERS_BANKS_META_DATA);
            mPreferredBanksList = intent.getParcelableArrayListExtra(Constants.PREFERRED_BANK_LIST);
            mOthersBanksList = intent.getParcelableArrayListExtra(Constants.OTHERS_BANK_LIST);
            allBanksList =intent.getParcelableArrayListExtra(Constants.ALL_BANKS_LIST);
            preferredBankData = intent.getParcelableExtra(Constants.PREFERRED_BANK_DATA);
            othersBankData = intent.getParcelableExtra(Constants.OTHERS_BANK_DATA);
            allBanksData = intent.getParcelableExtra(Constants.ALL_BANKS);
        }



    }

    @Override
    protected BaseView getBaseView() {
        return this;
    }

    @Override
    protected void callDependencyInjector(DependencyInjectorComponent injectorComponent) {

        injectorComponent.injectDependencies(this);
    }

    @Override
    public void initView() {

       // setUpHeaderPreferredBank(mPreferredBanksMetaData);
       //setUpVietTypeTest(mPreferredBanksMetaData);

       //setUpBankListPreferredBank(mPreferredBanksMetaData);
       // setUpHeaderOthersBank();
        //setUpBankListOthersBank();
       //setUpPreferredBanksAdapter();

        setUpCustomAdapterPreferredBanks();

       // setUpCustomAdapterOtherBanks();
    }

    private void setUpVietTypeTest(ArrayList<Bank> mPreferredBanksMetaData) {

        ArrayList<ItemEncapsulator> items = new ArrayList<>(encapsulateItems(VH_viewTypeTest.LAYOUT, GeneralUtilsKt.singleElementAsArrayList(preferredBankData)));

        setUpPreferredBanksAdapter(items);
        rv_preferred_banks.setLayoutManager(new LinearLayoutManager(context));
        rv_preferred_banks.setHasFixedSize(true);
        rv_preferred_banks.setVisibility(View.VISIBLE);
        rv_preferred_banks.setAdapter(preferredBanksAdapter);
    }

    private void setUpHeaderPreferredBank(ArrayList<Bank> banks) {

        ArrayList<ItemEncapsulator> items = new ArrayList<>(encapsulateItems(VH_HeaderPreferredBank.LAYOUT, GeneralUtilsKt.singleElementAsArrayList(preferredBankData)));

        //setUpPreferredBanksAdapter(items);
        rv_preferred_banks.setLayoutManager(new LinearLayoutManager(context));
        rv_preferred_banks.setHasFixedSize(true);
        rv_preferred_banks.setVisibility(View.VISIBLE);
        rv_preferred_banks.setAdapter(preferredBanksAdapter);
       // setUpPreferredBanksAdapter(items);

    }

    private void setUpBankListPreferredBank(ArrayList<Bank> banks)
    {
        ArrayList<ItemEncapsulator> items = new ArrayList<>(encapsulateItems(VH_PreferredBanks.LAYOUT, banks));


      //  ArrayList<ItemEncapsulator> items = new ArrayList<>(encapsulateItems(VH_Brand.LAYOUT, brandArrayList));
        //addCanNotFindAction(items);
    //    setUpPreferredBanksAdapter(items);
        rv_preferred_banks.setLayoutManager(new LinearLayoutManager(context));
        rv_preferred_banks.setHasFixedSize(true);
        rv_preferred_banks.setVisibility(View.VISIBLE);
        rv_preferred_banks.setAdapter(preferredBanksAdapter);
        //setUpPreferredBanksAdapter(items);


    }

    private void setUpPreferredBanksAdapter(ArrayList<ItemEncapsulator> items) {

        /*    preferredBanksAdapter = new GenericAdapterBuilder<ItemEncapsulator>(context,this)
                    .setItems(items)
                    .setClickable(true)
                    .setSelectable(false)
                    .buildRecyclerViewAdapter();*/

       // customAdapter = new CustomAdapter(mPreferredBanksMetaData,mPreferredBanksList);

        rv_preferred_banks.setLayoutManager(new LinearLayoutManager(context));
        rv_preferred_banks.setHasFixedSize(true);
        rv_preferred_banks.setVisibility(View.VISIBLE);
        rv_preferred_banks.setAdapter(customAdapter);


    }

    private void setUpCustomAdapterPreferredBanks() {

        /*    preferredBanksAdapter = new GenericAdapterBuilder<ItemEncapsulator>(context,this)
                    .setItems(items)
                    .setClickable(true)
                    .setSelectable(false)
                    .buildRecyclerViewAdapter();*/

        customAdapter = new CustomAdapterAllBanks(mPreferredBanksMetaData,mPreferredBanksList,mOthersaBanksMetaData,mOthersBanksList,
                allBanksData);

        rv_preferred_banks.setLayoutManager(new LinearLayoutManager(context));
        rv_preferred_banks.setHasFixedSize(true);
        rv_preferred_banks.setVisibility(View.VISIBLE);
        rv_preferred_banks.setAdapter(customAdapter);


    }


    private void setUpCustomAdapterOtherBanks() {



       // customAdapter = new CustomAdapterAllBanks(mOthersaBanksMetaData,mOthersBanksList);

        rv_other_banks.setLayoutManager(new LinearLayoutManager(context));
        rv_other_banks.setHasFixedSize(true);
        rv_other_banks.setVisibility(View.VISIBLE);
        rv_other_banks.setAdapter(customAdapter);


    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showToastMessage(String toastMessage, boolean isErrortoast) {

    }

    @Override
    public void accessTokenExpired() {

    }

    @Override
    public View getView(@NonNull ViewGroup parent, int viewType) {
        switch (viewType)
        {
            case VH_HeaderPreferredBank.LAYOUT:
                return getLayoutInflater().inflate(VH_HeaderPreferredBank.LAYOUT, parent,false);

            case VH_PreferredBanks.LAYOUT:
                return getLayoutInflater().inflate(VH_PreferredBanks.LAYOUT,
                    parent,false);
            case VH_viewTypeTest.LAYOUT:
                return getLayoutInflater().inflate(VH_viewTypeTest.LAYOUT,
                        parent,false);

            default:throw (TypeNotSupportedException.create("Type not supported"));
        }
    }

    @Override
    public AbstractBetterViewHolder getViewHolder(View view, int viewType) {
        switch (viewType)
        {
            case VH_HeaderPreferredBank.LAYOUT:
                return new VH_HeaderPreferredBank(view);

            case VH_PreferredBanks.LAYOUT:
                return new VH_PreferredBanks(view);
            case VH_viewTypeTest
                        .LAYOUT: return new VH_viewTypeTest(view);
            default:
                throw (TypeNotSupportedException.create("Type not supported"));

        }
    }
}
