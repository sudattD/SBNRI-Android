package sbnri.consumer.android.accountflow.viewholders;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import sbnri.consumer.android.R;
import sbnri.consumer.android.adapters.AbstractBetterViewHolder;
import sbnri.consumer.android.adapters.ItemEncapsulator;
import sbnri.consumer.android.data.models.SubBank;

class VH_PreferredBanks extends AbstractBetterViewHolder<ItemEncapsulator> {


    @BindView(R.id.tv_highlight)
    TextView tv_highlight;

    @BindView(R.id.tv_achievement)
    TextView tv_achievement;

    @BindView(R.id.iv_bank_image)
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

    SubBank subBankDetails;



    public static final int LAYOUT = R.layout.item_preferred_bank_trial_linear;

    protected VH_PreferredBanks(View view) {
        super(view);
    }

    @Override
    public void bind(ItemEncapsulator element, int position) {

        subBankDetails = (SubBank) element.getItem();

    }
}
