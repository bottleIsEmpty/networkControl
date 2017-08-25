package su.rck.networkcontrol;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Александр on 08.08.2017.
 */

public class BidLab {

    private static BidLab sBidLab;
    private List<Bid> mBids;

    public static BidLab get(Context context) {
        if (sBidLab == null) {
            sBidLab = new BidLab(context);
        }

        return sBidLab;
    }

    private BidLab(Context context) {
        //mAppContext = appContext;
        mBids = new ArrayList<>();
        /*for (int i = 0; i < 100; i++) {
            Bid bid = new Bid();
            bid.setID(i+1);
            bid.setOpened(i % 2 == 0);
            bid.setAddress("#" + i + " улица пушкина дом колотушкина");
            bid.setDate(new Date());
            mBids.add(bid);
        }*/
        mBids.add(new Bid(1, "Пушкинский", "Ленина", "20/8", new Date(), "+380602123123", "Оборван кабель"));
        mBids.add(new Bid(2, "Ленинский", "Пушкина", "31/2", new Date(), "+380602123123", "Оборван кабель уже 2 недели"));
        mBids.add(new Bid(3, "Центр", "Вахрушева", "15/4", new Date(), "+380602123123", "Пропал интернет"));
        mBids.add(new Bid(4, "Пушкинский", "Ленина", "20/8", new Date(), "+380602123123", "Оборван кабель"));
        mBids.add(new Bid(5, "Ленинский", "Пушкина", "31/2", new Date(), "+380602123123", "Оборван кабель уже 2 недели"));
        mBids.add(new Bid(6, "Центр", "Вахрушева", "15/4", new Date(), "+380602123123", "Пропал интернет"));
        mBids.add(new Bid(7, "Пушкинский", "Ленина", "20/8", new Date(), "+380602123123", "Оборван кабель"));
        mBids.add(new Bid(8, "Ленинский", "Пушкина", "31/2", new Date(), "+380602123123", "Оборван кабель уже 2 недели"));
        mBids.add(new Bid(9, "Центр", "Вахрушева", "15/4", new Date(), "+380602123123", "Пропал интернет"));
        mBids.add(new Bid(10, "Пушкинский", "Ленина", "20/8", new Date(), "+380602123123", "Оборван кабель"));
        mBids.add(new Bid(11, "Ленинский", "Пушкина", "31/2", new Date(), "+380602123123", "Оборван кабель уже 2 недели"));
        mBids.add(new Bid(12, "Центр", "Вахрушева", "15/4", new Date(), "+380602123123", "Пропал интернет"));
        mBids.add(new Bid(13, "Пушкинский", "Ленина", "20/8", new Date(), "+380602123123", "Оборван кабель"));
        mBids.add(new Bid(14, "Ленинский", "Пушкина", "31/2", new Date(), "+380602123123", "Оборван кабель уже 2 недели"));
        mBids.add(new Bid(15, "Центр", "Вахрушева", "15/4", new Date(), "+380602123123", "Пропал интернет"));
        mBids.add(new Bid(16, "Пушкинский", "Ленина", "20/8", new Date(), "+380602123123", "Оборван кабель"));
        mBids.add(new Bid(17, "Ленинский", "Пушкина", "31/2", new Date(), "+380602123123", "Оборван кабель уже 2 недели"));
        mBids.add(new Bid(18, "Центр", "Вахрушева", "15/4", new Date(), "+380602123123", "Пропал интернет"));
    }

    public List<Bid> getBids() {
        return mBids;
    }

    public Bid getBid(int id) {
        for (Bid bid : mBids) {
            if (bid.getID() == id) {
                return bid;
            }
        }
        return null;
    }
}
