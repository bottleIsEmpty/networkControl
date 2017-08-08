package su.rck.networkcontrol;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

public class BidListFragment extends ListFragment {

    public static final String TAG = "BidListFragment";
    private RecyclerView mBidRecyclerView;
    private BidAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bid_list, container, false);

        mBidRecyclerView = (RecyclerView)view.findViewById(R.id.bid_recycler_view);
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

    private class BidHolder extends RecyclerView.ViewHolder {

        public TextView mTitleTextView;

        public BidHolder(View itemView) {
            super(itemView);
            mTitleTextView = (TextView)itemView;
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
            View view = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);

            return new BidHolder(view);
        }

        @Override
        public void onBindViewHolder(BidHolder holder, int position) {

            Bid bid = mBids.get(position);
            holder.mTitleTextView.setText(bid.getAddress());

        }

        @Override
        public int getItemCount() {
            return mBids.size();
        }
    }

}
