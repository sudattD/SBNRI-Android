package sbnri.consumer.android.accountflow.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import sbnri.consumer.android.data.models.AllBanksData;
import sbnri.consumer.android.data.models.Bank;
import sbnri.consumer.android.data.models.SubBank;
import sbnri.consumer.android.util.GeneralUtilsKt;

public class CustomAdapterAllBanks extends RecyclerView.Adapter {



    List<SubBank> preferredBanks;
    List<Bank> preferredBankMetaData;

    AllBanksData allBanksData;

    List<SubBank> othersBanks;
    List<Bank> othersBankMetaData;




    public CustomAdapterAllBanks(List<Bank> preferredBankMetaData, List<SubBank> preferredBanks,
                                 List<Bank> othersBankMetaData,
                                 List<SubBank> othersBanks,
                                 AllBanksData allBanksData) {
        this.preferredBankMetaData = preferredBankMetaData;
        this.preferredBanks = preferredBanks;

        this.othersBankMetaData = othersBankMetaData;
        this.othersBanks = othersBanks;
        this.allBanksData = allBanksData;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = null;

        if (viewType == 0)
        {
            view = layoutInflater.inflate(VH_HeaderPreferredBank.LAYOUT,parent,false);
            return new VH_HeaderPreferredBank(view);
        }
        else
            view = layoutInflater.inflate(VH_PreferredBanks.LAYOUT,parent,false);
        return new VH_PreferredBanks(view);


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    /*   switch (position)
       {
           case 0:
               VH_HeaderPreferredBank vh_headerPreferredBank = (VH_HeaderPreferredBank) holder;
               vh_headerPreferredBank.tv_label_openAccount.setText(preferredBankMetaData.get(position).getTitle());
               vh_headerPreferredBank.tv_account_brief.setText(preferredBankMetaData.get(position).getTitle());

               break;

           case 1:
               VH_PreferredBanks vh_preferredBanks = (VH_PreferredBanks) holder;
               Picasso.get().load(preferredBanks.get(position).getBankImage()).
                       into(vh_preferredBanks.iv_bank_image);

               break;
       }*/

/*        if(getItemViewType(position) == 0)
        {

                VH_HeaderPreferredBank vh_headerPreferredBank = (VH_HeaderPreferredBank) holder;
                vh_headerPreferredBank.tv_label_openAccount.setText(preferredBankMetaData.get(position).getTitle());
                vh_headerPreferredBank.tv_account_brief.setText(preferredBankMetaData.get(position).getTitle());



        }
        else {

                VH_PreferredBanks vh_preferredBanks = (VH_PreferredBanks) holder;
                Picasso.get().load(preferredBanks.get(position).getBankImage()).
                        into(vh_preferredBanks.iv_bank_image);
            }


        */



    }

    @Override
    public int getItemCount() {
        return preferredBanks.size() + othersBanks.size() + 2;
    }


    @Override
    public int getItemViewType(int position) {

            if (position == 0 || position== 2) {

                return 0;
            } else

                return 1;


    }


}
