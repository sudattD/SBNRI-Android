package sbnri.consumer.android.accountflow.viewholders;

import android.view.View;

import sbnri.consumer.android.R;
import sbnri.consumer.android.adapters.AbstractBetterViewHolder;
import sbnri.consumer.android.adapters.ItemEncapsulator;
import sbnri.consumer.android.data.models.Bank;

public class VH_viewTypeTest extends AbstractBetterViewHolder<ItemEncapsulator> {



    Bank bank;
    public static final int LAYOUT = R.layout.item_view_type_test;

    public VH_viewTypeTest(View view) {
        super(view);
    }

    @Override
    public void bind(ItemEncapsulator element, int position) {

    }
}
