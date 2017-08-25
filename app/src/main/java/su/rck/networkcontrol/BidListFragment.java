package su.rck.networkcontrol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class BidListFragment extends Fragment {

    //public static final String TAG = "BidListFragment";
    private RecyclerView mBidRecyclerView;
    private BidAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bid_list, container, false);

        mBidRecyclerView = view.findViewById(R.id.bid_recycler_view);
        mBidRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }

    private void updateUI() {
        BidLab bidLab = BidLab.get(getActivity());
        List<Bid> bids = bidLab.getBids();

        mAdapter = new BidAdapter(bids);
        mBidRecyclerView.setAdapter(mAdapter);

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
            mBid = bid;
            mAddressTextView.setText(bid.getAddress());
            mDateTextView.setText(bid.getDate().toString());
            mSolvedCheckBox.setChecked(false);
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

    }

}
