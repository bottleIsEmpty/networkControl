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

    private List<ContentValues> bidList;
    private List<ContentValues> masterList;

    public BidDBHelper (Context context) {
        super(context, BidDBSchema.DB_NAME, null, BidDBSchema.DB_VERSION);
        bidList = new ArrayList<>();
        masterList = new ArrayList<>();

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

        Log.d("DB", command);

        addMasters();

        for (int i = 0; i < 2; i++) {
            sqLiteDatabase.insert(BidDBSchema.UserTable.NAME, null, masterList.get(i));
            Log.d("su.rck.android.com", "added " + i);
        }

        addBids();

        for (int i = 0; i < 20; i++) {
            sqLiteDatabase.insert(BidDBSchema.BidTable.NAME, null, bidList.get(i));
            Log.d("su.rck.android.com", "added " + i);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    private void addMasters() {

        ContentValues values;
        values = new ContentValues();
        values.put(BidDBSchema.UserTable.Cols.ID, "1");
        values.put(BidDBSchema.UserTable.Cols.USER_NAME, "Сергей");
        values.put(BidDBSchema.UserTable.Cols.USER_SURNAME, "Иванов");
        values.put(BidDBSchema.UserTable.Cols.LOGIN, "admin");
        values.put(BidDBSchema.UserTable.Cols.PASSWORD, "qwerty");

        masterList.add(values);

        values = new ContentValues();
        values.put(BidDBSchema.UserTable.Cols.ID, "2");
        values.put(BidDBSchema.UserTable.Cols.USER_NAME, "Валентин");
        values.put(BidDBSchema.UserTable.Cols.USER_SURNAME, "Петров");
        values.put(BidDBSchema.UserTable.Cols.LOGIN, "user");
        values.put(BidDBSchema.UserTable.Cols.PASSWORD, "123");

        masterList.add(values);

    }

    private void addBids() {
        ContentValues values;

        for(int i = 0; i < 10; i++) {
            values = new ContentValues();
            values.put(BidDBSchema.BidTable.Cols.DISTRICT, "Пушкинский");
            values.put(BidDBSchema.BidTable.Cols.STREET, "Щукина");
            values.put(BidDBSchema.BidTable.Cols.HOUSE, "33/71");
            values.put(BidDBSchema.BidTable.Cols.DATE, "31.10.2017, 23:30");
            values.put(BidDBSchema.BidTable.Cols.ROUTER_STATE, "1");
            values.put(BidDBSchema.BidTable.Cols.PHONE, "+380667572223");
            values.put(BidDBSchema.BidTable.Cols.DETAILS, "Пропал интернет и я не знаю, что мне делать.");
            values.put(BidDBSchema.BidTable.Cols.MASTER, "1");

            bidList.add(values);

            values = new ContentValues();
            values.put(BidDBSchema.BidTable.Cols.DISTRICT, "Центр");
            values.put(BidDBSchema.BidTable.Cols.STREET, "Газманова");
            values.put(BidDBSchema.BidTable.Cols.HOUSE, "222/71ф");
            values.put(BidDBSchema.BidTable.Cols.DATE, "31.10.2017, 22:10");
            values.put(BidDBSchema.BidTable.Cols.ROUTER_STATE, "0");
            values.put(BidDBSchema.BidTable.Cols.PHONE, "+380667512323");
            values.put(BidDBSchema.BidTable.Cols.DETAILS, "Пропал интернет, возможно, оборван кабель.");
            values.put(BidDBSchema.BidTable.Cols.MASTER, "2");

            bidList.add(values);
        }
    }
}
