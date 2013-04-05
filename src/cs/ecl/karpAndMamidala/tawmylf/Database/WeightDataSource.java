package cs.ecl.karpAndMamidala.tawmylf.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import cs.ecl.karpAndMamidala.tawmylf.Database.SQLiteHelper;
import cs.ecl.karpAndMamidala.tawmylf.Models.WeightItem;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WeightDataSource {
    private SQLiteDatabase db;
    private SQLiteHelper dbHelper;
    private String[] columns = { SQLiteHelper.COLUMN_ID,
                                 SQLiteHelper.COLUMN_WEIGHT_DATE,
                                 SQLiteHelper.COLUMN_WEIGHT_WEIGHT };

    public WeightDataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLiteException {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public WeightItem createWeightItem(long date, float weight) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_WEIGHT_DATE, date);
        values.put(SQLiteHelper.COLUMN_WEIGHT_WEIGHT, weight);

        long insertId = db.insert(SQLiteHelper.TABLE_WEIGHT, null, values);
        Cursor cursor = db.query(SQLiteHelper.TABLE_WEIGHT, columns,
                SQLiteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        WeightItem newWeightItem = cursorToWeightItem(cursor);
        cursor.close();
        return newWeightItem;
    }

    public List<WeightItem> getAllWeightItems() {
        List<WeightItem> weightItemList = new ArrayList<WeightItem>();

        Cursor cursor = db.query(SQLiteHelper.TABLE_WEIGHT, columns, null, null, null, null, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            WeightItem i = cursorToWeightItem(cursor);
            weightItemList.add(i);
            cursor.moveToNext();
        }

        cursor.close();
        return weightItemList;
    }

    public List<WeightItem> getLast30DaysWeightItems() {
        List<WeightItem> weightItemList = new ArrayList<WeightItem>();

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        Date d = cal.getTime();
        long thirtyDaysAgo = d.getTime() / 1000;

        Cursor cursor = db.query(SQLiteHelper.TABLE_WEIGHT, columns, SQLiteHelper.COLUMN_WEIGHT_DATE + " > " + thirtyDaysAgo, null, null, null, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            WeightItem i = cursorToWeightItem(cursor);
            weightItemList.add(i);
            cursor.moveToNext();
        }

        cursor.close();
        return weightItemList;
    }

    private WeightItem cursorToWeightItem(Cursor cursor) {
        WeightItem weightItem = new WeightItem();
        weightItem.setId(cursor.getLong(0));
        weightItem.setDate(cursor.getLong(1));
        weightItem.setWeight(cursor.getFloat(2));
        return weightItem;
    }

    public void generateWeightData() {
        this.open();
        this.createWeightItem(1356998400, 200);
        this.createWeightItem(1357257600, 215);
        this.createWeightItem(1357689600, 205);
        this.createWeightItem(1358035200, 199);
        this.createWeightItem(1358467200, 198);
        this.createWeightItem(1359504000, 190);
        this.createWeightItem(1356998400, 160);
        this.createWeightItem(1360627200, 140);
        this.createWeightItem(1361232000, 120);
        this.createWeightItem(1362009600, 80);
        this.createWeightItem(1362268800, 60);
        this.createWeightItem(1362873600, 40);
        this.createWeightItem(1363305600, 20);
        this.close();
    }
}
