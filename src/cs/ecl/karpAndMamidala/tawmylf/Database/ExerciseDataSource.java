package cs.ecl.karpAndMamidala.tawmylf.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import cs.ecl.karpAndMamidala.tawmylf.Models.ExerciseItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dale
 * Date: 2013-03-31
 * Time: 10:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class ExerciseDataSource {
    private SQLiteDatabase db;
    private SQLiteHelper dbHelper;
    private String[] columns = { SQLiteHelper.COLUMN_ID,
                                 SQLiteHelper.COLUMN_EXER_DATE,
                                 SQLiteHelper.COLUMN_EXER_TYPE,
                                 SQLiteHelper.COLUMN_EXER_DURATION,
                                 SQLiteHelper.COLUMN_EXER_INTENSITY};

    public ExerciseDataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLiteException {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public ExerciseItem createExerciseItem(int date, String type, int duration, int intensity) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_EXER_DATE, date);
        values.put(SQLiteHelper.COLUMN_EXER_TYPE, type);
        values.put(SQLiteHelper.COLUMN_EXER_DURATION, duration);
        values.put(SQLiteHelper.COLUMN_EXER_INTENSITY, intensity);

        long insertId = db.insert(SQLiteHelper.TABLE_EXERCISE, null, values);
        Cursor cursor = db.query(SQLiteHelper.TABLE_EXERCISE, columns,
                SQLiteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        ExerciseItem newExerItem = cursorToExerItem(cursor);
        cursor.close();
        return newExerItem;
    }

    public List<ExerciseItem> getAllExerciseItems() {
        List<ExerciseItem> exerciseItemList = new ArrayList<ExerciseItem>();

        Cursor cursor = db.query(SQLiteHelper.TABLE_EXERCISE, columns, null, null, null, null, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            ExerciseItem i = cursorToExerItem(cursor);
            exerciseItemList.add(i);
            cursor.moveToNext();
        }

        cursor.close();
        return exerciseItemList;
    }

    private ExerciseItem cursorToExerItem(Cursor cursor) {
        ExerciseItem exerciseItem = new ExerciseItem();
        exerciseItem.setId(cursor.getLong(0));
        exerciseItem.setDate(cursor.getInt(1));
        exerciseItem.setType(cursor.getString(2));
        exerciseItem.setDuration(cursor.getInt(3));
        exerciseItem.setIntensity(cursor.getInt(4));
        return exerciseItem;
    }
}
