package cs.ecl.karpAndMamidala.tawmylf.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import cs.ecl.karpAndMamidala.tawmylf.Models.BloodPressureItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dale
 * Date: 2013-03-31
 * Time: 10:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class BloodPressureDataSource {
    private SQLiteDatabase db;
    private SQLiteHelper dbHelper;
    private String[] columns = { SQLiteHelper.COLUMN_ID,
                                 SQLiteHelper.COLUMN_BP_DATE,
                                 SQLiteHelper.COLUMN_BP_SYSTOLIC_PRESSURE,
                                 SQLiteHelper.COLUMN_BP_DIASTOLIC_PRESSURE,
                                 SQLiteHelper.COLUMN_BP_HEARTRATE};

    public BloodPressureDataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLiteException {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public BloodPressureItem createBloodPressureItem(int date, float sPressure, float dPressure, float hr) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_BP_DATE, date);
        values.put(SQLiteHelper.COLUMN_BP_SYSTOLIC_PRESSURE, sPressure);
        values.put(SQLiteHelper.COLUMN_BP_DIASTOLIC_PRESSURE, dPressure);
        values.put(SQLiteHelper.COLUMN_BP_HEARTRATE, hr);

        long insertId = db.insert(SQLiteHelper.TABLE_BP, null, values);
        Cursor cursor = db.query(SQLiteHelper.TABLE_BP, columns,
                SQLiteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        BloodPressureItem newBPItem = cursorToBPItem(cursor);
        cursor.close();
        return newBPItem;
    }

    public List<BloodPressureItem> getAllBloodPressureItems() {
        List<BloodPressureItem> bpItemList = new ArrayList<BloodPressureItem>();

        Cursor cursor = db.query(SQLiteHelper.TABLE_BP, columns, null, null, null, null, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            BloodPressureItem i = cursorToBPItem(cursor);
            bpItemList.add(i);
            cursor.moveToNext();
        }

        cursor.close();
        return bpItemList;
    }

    private BloodPressureItem cursorToBPItem(Cursor cursor) {
        BloodPressureItem bpItem = new BloodPressureItem();
        bpItem.setId(cursor.getLong(0));
        bpItem.setDate(cursor.getInt(1));
        bpItem.setSystolicPressure(cursor.getFloat(2));
        bpItem.setDiastolicPressure(cursor.getFloat(3));
        bpItem.setHeartrate(cursor.getFloat(4));
        return bpItem;
    }

}
