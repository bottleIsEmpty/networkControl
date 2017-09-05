package su.rck.networkcontrol;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

public class BidPagerActivity extends AppCompatActivity {

    private static final String EXTRA_BID_ID = "su.rck.android.networkControl.bid_id";

    private ViewPager mViewPager;
    private List<Bid> mBids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bid_pager);

        int bidID = getIntent().getIntExtra(EXTRA_BID_ID, 1);

        mViewPager = (ViewPager) findViewById(R.id.activity_bid_pager_view_pager);

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

        for (int i = 0; i < mBids.size(); i++) {
            if (mBids.get(i).getID() == bidID) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }

    //Функция создания нового интента

    public static Intent newIntent(Context packageContext, int bidID) {
        Intent intent = new Intent(packageContext, BidPagerActivity.class);
        intent.putExtra(EXTRA_BID_ID, bidID);

        return intent;
    }
}
