package su.rck.networkcontrol;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Александр on 21.08.2017.
 */

public class BidFragment extends Fragment {

    //Тэги

    private static final String ARG_BID_ID = "bid_id";

    //Заявка для данного фрагмента

    private Bid mBid;

    //Данные по заявке

    private TextView IDField;
    private TextView districtField;
    private TextView streetField;
    private TextView houseField;
    private TextView dateField;
    private TextView phoneField;
    private TextView detailsField;

    public static  BidFragment newInstance(int bidId) {
        Bundle args = new Bundle();
        args.putInt(ARG_BID_ID, bidId);

        BidFragment fragment = new BidFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int bidID = getArguments().getInt(ARG_BID_ID);
        mBid = BidLab.get(getActivity()).getBid(bidID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bid, container, false);

        //Инициализация элементов заявки

        IDField = view.findViewById(R.id.bid_number_field);
        districtField = view.findViewById(R.id.district_field);
        streetField = view.findViewById(R.id.street_field);
        houseField = view.findViewById(R.id.house_field);
        dateField = view.findViewById(R.id.time_field);
        phoneField = view.findViewById(R.id.phone_field);
        detailsField = view.findViewById(R.id.details_field);

        //Добавление данных конкретной заявки

        IDField.setText(IDField.getText() + "" + mBid.getID());
        districtField.setText(districtField.getText() + " " + mBid.getDistrict());
        streetField.setText(streetField.getText() + " " + mBid.getStreet());
        houseField.setText(houseField.getText() + " " + mBid.getHouse());
        dateField.setText(dateField.getText() + "" + mBid.getDate().toString());
        phoneField.setText(phoneField.getText() + " " + mBid.getPhoneNumber());
        detailsField.setText(detailsField.getText() + " " + mBid.getDetails());

        return view;
    }
}
