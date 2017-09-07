package su.rck.networkcontrol;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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

import org.json.JSONException;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class BidListFragment extends Fragment {

    static private String TAG = "su.rck.BidListFragment";

    private RecyclerView mBidRecyclerView;
    private BidAdapter mAdapter;
    private TextView mEmptyText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bid_list, container, false);

        new GetBidsTask().execute();

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

    //Присвоение интерфейса для меню

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_bid_list, menu);
    }

    //События по нажатию на элементы меню

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_logout:
                new SuggestLogoutDialog().show(getActivity().getSupportFragmentManager(), TAG);
            break;

            case R.id.menu_item_update_bids:
                new GetBidsTask().execute();
            break;
        }
        return  true;
    }

    //Обновление интерфейса

    private void updateUI() {

        List<Bid> bids = BidLab.get(getActivity()).getBids();

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

    //ViewHolder

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

    //Adapter

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

    //Диалоговое окно подтверждения выхода

    public static class SuggestLogoutDialog extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Выход из аккаунта");
            builder.setMessage(R.string.suggest_logout)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            QueryPreferences.deletePrefAuthorizedUserID(getActivity());
                            Intent intent = new Intent(getActivity(), SignInActivity.class);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });

            return builder.create();
        }
    }

    //AsyncTask для получения всех заявок

    private class GetBidsTask extends AsyncTask<Void, Void, List<Bid>> {
        private int masterID;
        private List<Bid> bids;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            masterID = QueryPreferences.getPrefAuthorizedUserID(getActivity());
        }

        @Override
        protected List<Bid> doInBackground(Void... voids) {
            try {
                bids = NetworkController.get().fetchBids(masterID);
            } catch (IOException | JSONException | ParseException e) {
                e.printStackTrace();
            }
            return bids;
        }

        @Override
        protected void onPostExecute(List<Bid> bids) {
            super.onPostExecute(bids);
            BidLab.get(getActivity()).replaceBids(bids, masterID);

            updateUI();
        }
    }
}
