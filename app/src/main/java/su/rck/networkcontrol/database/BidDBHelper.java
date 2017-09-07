package su.rck.networkcontrol.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр on 30.08.2017.
 */

public class BidDBHelper extends SQLiteOpenHelper {

    //private List<ContentValues> bidList;
    //private List<ContentValues> masterList;

    public BidDBHelper (Context context) {
        super(context, BidDBSchema.DB_NAME, null, BidDBSchema.DB_VERSION);
        //bidList = new ArrayList<>();
        //masterList = new ArrayList<>();

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String command = "CREATE TABLE IF NOT EXISTS " + BidDBSchema.BidTable.NAME + " (" +
                BidDBSchema.BidTable.Cols.ID + " INTEGER PRIMARY KEY, " +
                BidDBSchema.BidTable.Cols.DISTRICT + " TEXT, " +
                BidDBSchema.BidTable.Cols.STREET + " TEXT, " +
                BidDBSchema.BidTable.Cols.HOUSE + " TEXT, " +
                BidDBSchema.BidTable.Cols.DATE + " NUMERIC, " +
                BidDBSchema.BidTable.Cols.PHONE + " TEXT, " +
                BidDBSchema.BidTable.Cols.ROUTER_STATE + " INTEGER, " +
                BidDBSchema.BidTable.Cols.DETAILS + " BLOB, " +
                BidDBSchema.BidTable.Cols.MASTER + " INTEGER, " +
                "FOREIGN KEY(" + BidDBSchema.BidTable.Cols.MASTER + ") REFERENCES " +
                BidDBSchema.UserTable.NAME + "(" + BidDBSchema.UserTable.Cols.ID + ")" + ")";
        sqLiteDatabase.execSQL(command);

        Log.d("DB", command);


        command = "CREATE TABLE IF NOT EXISTS " + BidDBSchema.UserTable.NAME + " (" +
                BidDBSchema.UserTable.Cols.ID + " INTEGER PRIMARY KEY, " +
                BidDBSchema.UserTable.Cols.USER_NAME + " TEXT, " +
                BidDBSchema.UserTable.Cols.USER_SURNAME + " TEXT, " +
                BidDBSchema.UserTable.Cols.LOGIN  + " TEXT, " +
                BidDBSchema.UserTable.Cols.PASSWORD  + " TEXT)";

        sqLiteDatabase.execSQL(command);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
