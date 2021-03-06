package sbnri.consumer.android.accountflow.viewholders;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import androidx.cardview.widget.CardView;
import butterknife.BindView;
import butterknife.ButterKnife;
import sbnri.consumer.android.R;
import sbnri.consumer.android.adapters.AbstractBetterViewHolder;
import sbnri.consumer.android.adapters.OnCommonItemClickListener;
import sbnri.consumer.android.data.models.SubBank;

public class VH_PreferredBanks extends AbstractBetterViewHolder<SubBank> {


    @BindView(R.id.tv_highlight)
    TextView tv_highlight;

    @BindView(R.id.tv_achievement)
    TextView tv_achievement;

    @BindView(R.id.iv_bank_image_)
    ImageView iv_bank_image;

    @BindView(R.id.tv_bank_name)
    TextView tv_bank_name;

    @BindView(R.id.tv_bank_turnover)
    TextView tv_bank_turnover;

    @BindView(R.id.tv_days)
    TextView tv_days;

    @BindView(R.id.tv_interest_rate)
    TextView tv_interest_rate;

    @BindView(R.id.tv_fd_rate)
    TextView tv_fd_rate;

    @BindView(R.id.cardViewOuter)
    CardView cardViewOuter;

    @BindView(R.id.cardViewInner)
    CardView cardViewInner;

    @BindView(R.id.iv_info)
    ImageView iv_info;

    @BindView(R.id.iv_bank_image_card)
    CardView iv_bank_image_card;


    SubBank subBankDetails;

    OnCommonItemClickListener commonItemClickListener;


    public static final int LAYOUT = R.layout.item_bank_list_card;

     public VH_PreferredBanks(View view) {
        super(view);
         ButterKnife.bind(this, view);

    }
    public VH_PreferredBanks(View view, OnCommonItemClickListener commonItemClickListener) {
        super(view);
        this.commonItemClickListener = commonItemClickListener;
        ButterKnife.bind(this, view);

    }

    @Override
    public void bind(SubBank element, int position) {
        //subBankDetails =  element.

        tv_bank_name.setText(element.getBankName());
        // tv_bank_turnover
        //tv_days
        //tv_interest_rate
        //tv_fd_rate
        //iv_bank_image

        tv_highlight.setText(element.getBankHighlight());
        tv_achievement.setText(element.getNumberOfAccounts());


       // Picasso.get().load(mUserDetails.getPhotoURL()).into(binding.profileImage);
        Picasso.get().load(element.getBankImage()).into(iv_bank_image);
        iv_bank_image_card.setCardBackgroundColor(Color.parseColor("#1A"+element.getBankBackgroundColor().getHex().replace("#","")));

       cardViewOuter.setCardBackgroundColor(Color.parseColor(element.getBankBackgroundColor().getHex()));

      //  cardViewOuter.setBackground(Drawable.createFromPath());

        View.OnClickListener clickListener = v -> commonItemClickListener.onItemClick(v, element);
        cardViewOuter.setOnClickListener(clickListener);
        cardViewInner.setOnClickListener(clickListener);
        iv_info.setOnClickListener(clickListener);
    }


}
