package cs.ecl.karpAndMamidala.liveforever.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import cs.ecl.karpAndMamidala.liveforever.Models.BloodPressureItem;

import java.util.ArrayList;
import java.util.List;

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

    public BloodPressureItem createBloodPressureItem(long date, float sPressure, float dPressure, float hr) {
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
        bpItem.setDate(cursor.getLong(1));
        bpItem.setSystolicPressure(cursor.getFloat(2));
        bpItem.setDiastolicPressure(cursor.getFloat(3));
        bpItem.setHeartrate(cursor.getFloat(4));
        return bpItem;
    }

    public void generateBPData() {
        this.open();
        this.createBloodPressureItem(1356998400, 200, 150, 170);
        this.createBloodPressureItem(1357257600, 180, 160, 170);
        this.createBloodPressureItem(1357689600, 110, 170, 150);
        this.createBloodPressureItem(1358035200, 138, 232, 238);
        this.createBloodPressureItem(1358467200, 123, 153, 232);
        this.createBloodPressureItem(1359504000, 200, 182, 229);
        this.createBloodPressureItem(1356998400, 195, 129, 222);
        this.createBloodPressureItem(1360627200, 180, 130, 250);
        this.createBloodPressureItem(1361232000, 200, 150, 220);
        this.createBloodPressureItem(1362009600, 170, 123, 234);
        this.createBloodPressureItem(1362268800, 232, 234, 234);
        this.createBloodPressureItem(1362873600, 231, 113, 123);
        this.createBloodPressureItem(1363305600, 138, 223, 170);
        this.close();
    }
}
