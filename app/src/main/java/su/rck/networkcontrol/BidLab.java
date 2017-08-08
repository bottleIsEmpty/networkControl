package su.rck.networkcontrol;

import android.content.Context;

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
        for (int i = 0; i < 100; i++) {
            Bid bid = new Bid();
            bid.setOpened(i % 2 == 0);
            mBids.add(bid);
        }

    }

    public List<Bid> getBids() {
        return mBids;
    }

    public Bid getBid(UUID id) {
        for (Bid bid : mBids) {
            if (bid.getID().equals(id)) {
                return bid;
            }
        }
        return null;
    }
}
