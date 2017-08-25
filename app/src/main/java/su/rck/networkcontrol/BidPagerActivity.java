package su.rck.networkcontrol;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import java.util.List;

public class BidPagerActivity extends FragmentActivity {

    private static final String EXTRA_BID_ID = "su.rck.android.networkControl.bid_id";

    private ViewPager mViewPager;
    private List<Bid> mBids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bid_pager);

        int bidID = getIntent().getIntExtra(EXTRA_BID_ID, 1);

        mViewPager = findViewById(R.id.activity_bid_pager_view_pager);

        mBids = BidLab.get(this).getBids();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {

            @Override
            public Fragment getItem(int position) {
                Bid bid = mBids.get(position);
                return BidFragment.newInstance(bid.getID());
            }

            @Override
            public int getCount() {
                return mBids.size();
            }
        });
    }

    //Функция создания нового интента

    public static Intent newIntent(Context packageContext, int bidID) {
        Intent intent = new Intent(packageContext, BidPagerActivity.class);
        intent.putExtra(EXTRA_BID_ID, bidID);

        return intent;
    }
}
