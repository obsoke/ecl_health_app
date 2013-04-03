package cs.ecl.karpAndMamidala.tawmylf.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import cs.ecl.karpAndMamidala.tawmylf.Database.SQLiteHelper;
import cs.ecl.karpAndMamidala.tawmylf.Models.WeightItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dale
 * Date: 2013-03-31
 * Time: 10:37 PM
 * To change this template use File | Settings | File Templates.
 */
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

    public WeightItem createWeightItem(float date, float weight) {
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

    private WeightItem cursorToWeightItem(Cursor cursor) {
        WeightItem weightItem = new WeightItem();
        weightItem.setId(cursor.getLong(0));
        weightItem.setDate(cursor.getInt(1));
        weightItem.setWeight(cursor.getFloat(2));
        return weightItem;
    }

}
