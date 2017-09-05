package su.rck.networkcontrol;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class BidListFragment extends Fragment {

    private RecyclerView mBidRecyclerView;
    private BidAdapter mAdapter;
    private TextView mEmptyText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bid_list, container, false);

        mEmptyText = view.findViewById(R.id.empty_text);
        mBidRecyclerView = view.findViewById(R.id.bid_recycler_view);
        mBidRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();

        updateUI();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_bid_list, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        
        return true;
    }

    private void updateUI() {
        BidLab bidLab = BidLab.get(getActivity());
        List<Bid> bids = bidLab.getBids();

        if (mAdapter == null) {
            mAdapter = new BidAdapter(bids);
            mBidRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setBids(bids);
            mAdapter.notifyDataSetChanged();
        }

        if (!bids.isEmpty()) {
            mEmptyText.setVisibility(View.INVISIBLE);
        }
    }

    private class BidHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mAddressTextView;
        private TextView mDateTextView;
        private CheckBox mSolvedCheckBox;

        private Bid mBid;

        public BidHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            mAddressTextView = itemView.findViewById(R.id.bid_list_item_addressTextView);
            mDateTextView = itemView.findViewById(R.id.bid_list_item_dateTextView);
            mSolvedCheckBox = itemView.findViewById(R.id.bid_list_item_solvedCheckBox);
        }

        public void bindBid(Bid bid) {
            bid.showInfo();
            mBid = bid;
            mAddressTextView.setText(bid.getAddress());
            mDateTextView.setText(bid.getDate().toString());
            mSolvedCheckBox.setChecked(bid.getRouterState());
        }

        @Override
        public void onClick(View view) {
            Intent intent = BidPagerActivity.newIntent(getActivity(), mBid.getID());
            startActivity(intent);
        }
    }

    private class BidAdapter extends RecyclerView.Adapter<BidHolder> {

        private List<Bid> mBids;

        public BidAdapter(List<Bid> bids) {
            mBids = bids;
        }

        @Override
        public BidHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_bid, parent, false);

            return new BidHolder(view);
        }

        @Override
        public void onBindViewHolder(BidHolder holder, int position) {

            Bid bid = mBids.get(position);
            holder.bindBid(bid);
        }

        @Override
        public int getItemCount() {
            return mBids.size();
        }

        public void setBids(List<Bid> bids) {
            mBids = bids;
        }

    }

}
