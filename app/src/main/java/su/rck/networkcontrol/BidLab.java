package su.rck.networkcontrol;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import su.rck.networkcontrol.database.BidDBCursorWrapper;
import su.rck.networkcontrol.database.BidDBHelper;
import su.rck.networkcontrol.database.BidDBSchema;

/**
 * Created by Александр on 08.08.2017.
 */

public class BidLab {

    private static BidLab sBidLab;
    private SQLiteDatabase mDatabase;
    private Context mContext;

    public static BidLab get(Context context) {
        if (sBidLab == null) {
            sBidLab = new BidLab(context);
        }

        return sBidLab;
    }

    private BidLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new BidDBHelper(mContext).getWritableDatabase();
    }

    //Преобразование заявки в объект ContentValues

    static private ContentValues getBidCV(Bid bid) {
        ContentValues values = new ContentValues();
        values.put(BidDBSchema.BidTable.Cols.ID, bid.getID());
        values.put(BidDBSchema.BidTable.Cols.DISTRICT, bid.getDistrict());
        values.put(BidDBSchema.BidTable.Cols.STREET, bid.getStreet());
        values.put(BidDBSchema.BidTable.Cols.HOUSE, bid.getHouse());
        values.put(BidDBSchema.BidTable.Cols.DATE, bid.getDate().getTime());
        values.put(BidDBSchema.BidTable.Cols.ROUTER_STATE, bid.getRouterState() ? 1 : 0);
        values.put(BidDBSchema.BidTable.Cols.PHONE, bid.getPhoneNumber());
        values.put(BidDBSchema.BidTable.Cols.DETAILS, bid.getDetails());
        values.put(BidDBSchema.BidTable.Cols.MASTER, bid.getMaster());

        return values;
    }

    //Преобразование пользователя в ContentValues

    static private ContentValues getUserCV(User user) {
        ContentValues values = new ContentValues();
        values.put(BidDBSchema.UserTable.Cols.ID, user.getID());
        values.put(BidDBSchema.UserTable.Cols.LOGIN, user.getLogin());
        values.put(BidDBSchema.UserTable.Cols.PASSWORD, user.getPassword());
        values.put(BidDBSchema.UserTable.Cols.USER_NAME, user.getName());
        values.put(BidDBSchema.UserTable.Cols.USER_SURNAME, user.getSurname());

        return values;
    }

    public List<Bid> getBids() {
        List<Bid> bids = new ArrayList<>();

        BidDBCursorWrapper cursor = queryBids(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                bids.add(cursor.getBid());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return bids;
    }

    public Bid getBid(int id) {
        BidDBCursorWrapper cursor = queryBids(BidDBSchema.BidTable.Cols.ID + " = ?", new String[] {String.valueOf(id)});

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getBid();
        } finally {
            cursor.close();
        }
    }

    public void addBid(Bid bid) {
        ContentValues values = getBidCV(bid);

        mDatabase.insert(BidDBSchema.BidTable.NAME, null, values);
    }

    public void setBids(List<Bid> bids, int masterID) {
        deleteAllBidsByMasterID(masterID);

        for (Bid bid: bids) {
            addBid(bid);
        }
    }

    public void updateBid(Bid bid) {
        String id = String.valueOf(bid.getID());
        ContentValues values = getBidCV(bid);

        mDatabase.update(BidDBSchema.BidTable.NAME, values, BidDBSchema.BidTable.Cols.ID + " = ?", new String[] {id});
    }

    public void replaceBids(List<Bid> bids, int masterID) {
        mDatabase.delete(BidDBSchema.BidTable.NAME, BidDBSchema.BidTable.Cols.MASTER + " = ?", new String[] {String.valueOf(masterID)});

        for (int i = 0; i < bids.size(); i++) {
            addBid(bids.get(i));
        }
    }

    public void deleteBid (Bid bid) {
        int id = bid.getID();

        deleteBid(id);
    }

    public void deleteBid(int bidID) {
        mDatabase.delete(BidDBSchema.BidTable.NAME, BidDBSchema.BidTable.Cols.ID + " = ?", new String[] {String.valueOf(bidID)});
    }

    public void deleteAllBidsByMasterID(int id) {
        mDatabase.delete(BidDBSchema.BidTable.NAME, BidDBSchema.BidTable.Cols.MASTER + " = ?", new String[] {String.valueOf(id)});
    }

    public void addUser(User user) {
        ContentValues values = getUserCV(user);
        mDatabase.delete(BidDBSchema.UserTable.NAME, "_id = ?", new String[]{String.valueOf(user.getID())});
        mDatabase.insert(BidDBSchema.UserTable.NAME, null, values);
    }

    private BidDBCursorWrapper queryBids(String whereClaus, String[] whereArgs) {
        Cursor cursor = mDatabase.query(BidDBSchema.BidTable.NAME, null, whereClaus, whereArgs, null, null, null);
        return new BidDBCursorWrapper(cursor);
    }


}
