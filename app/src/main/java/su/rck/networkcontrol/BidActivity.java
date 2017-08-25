package su.rck.networkcontrol;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;

public class BidActivity extends SingleFragmentActivity {

    private static final String EXTRA_BID_ID = "su.rck.android.networkControl.bid_id";

    @Override
    protected Fragment createFragment() {
        int bidID = getIntent().getIntExtra(EXTRA_BID_ID, 1);
        return BidFragment.newInstance(bidID);
    }

    //Функция создания нового интента

    public static Intent newIntent(Context packageContext, int bidID) {
        Intent intent = new Intent(packageContext, BidActivity.class);
        intent.putExtra(EXTRA_BID_ID, bidID);

        return intent;
    }
}
