package sbnri.consumer.android.accountflow.viewholders;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import sbnri.consumer.android.R;
import sbnri.consumer.android.adapters.AbstractBetterViewHolder;
import sbnri.consumer.android.adapters.ItemEncapsulator;
import sbnri.consumer.android.data.models.Bank;

class VH_HeaderPreferredBank extends AbstractBetterViewHolder<ItemEncapsulator> {


    @BindView(R.id.tv_label_openAccount)
    TextView tv_label_openAccount;

    @BindView(R.id.tv_account_brief)
    TextView tv_account_brief;

    Bank bankDetail;


    public static final int LAYOUT = R.layout.item_header_preferred_banks;
    protected VH_HeaderPreferredBank(View view) {
        super(view);
    }

    @Override
    public void bind(ItemEncapsulator element, int position) {

    }

    @Override
    public void bind(ItemEncapsulator element, int size, int position) {
        super.bind(element, size, position);

        bankDetail = (Bank) element.getItem();
        tv_label_openAccount.setText(bankDetail.getTitle());
        tv_account_brief.setText(bankDetail.getSubtitle());


    }
}
