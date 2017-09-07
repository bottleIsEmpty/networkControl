package su.rck.networkcontrol.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Date;

import su.rck.networkcontrol.Bid;

/**
 * Created by Александр on 31.08.2017.
 */

public class BidDBCursorWrapper extends CursorWrapper {
    public BidDBCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Bid getBid() {
        int id = getInt(getColumnIndex(BidDBSchema.BidTable.Cols.ID));
        String district = getString(getColumnIndex(BidDBSchema.BidTable.Cols.DISTRICT));
        String street = getString(getColumnIndex(BidDBSchema.BidTable.Cols.STREET));
        String house = getString(getColumnIndex(BidDBSchema.BidTable.Cols.HOUSE));
        long date = getLong(getColumnIndex(BidDBSchema.BidTable.Cols.DATE));
        boolean routerState = (getInt(getColumnIndex(BidDBSchema.BidTable.Cols.ROUTER_STATE)) != 0);
        String phone = getString(getColumnIndex(BidDBSchema.BidTable.Cols.PHONE));
        String details = getString(getColumnIndex(BidDBSchema.BidTable.Cols.DETAILS));
        int master = getInt(getColumnIndex(BidDBSchema.BidTable.Cols.MASTER));
        Bid bid = new Bid(id, district, street, house, new Date(date), routerState, phone, details, master);
        return bid;
    }
 }
