package cs.ecl.karpAndMamidala.liveforever.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import cs.ecl.karpAndMamidala.liveforever.Models.ExerciseItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    public ExerciseItem createExerciseItem(long date, String type, int duration, int intensity) {
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

        this.open();
        Cursor cursor = db.query(SQLiteHelper.TABLE_EXERCISE, columns, null, null, null, null, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            ExerciseItem i = cursorToExerItem(cursor);
            exerciseItemList.add(i);
            cursor.moveToNext();
        }

        cursor.close();
        this.close();
        return exerciseItemList;
    }

    private ExerciseItem cursorToExerItem(Cursor cursor) {
        ExerciseItem exerciseItem = new ExerciseItem();
        exerciseItem.setId(cursor.getLong(0));
        exerciseItem.setDate(cursor.getLong(1));
        exerciseItem.setType(cursor.getString(2));
        exerciseItem.setDuration(cursor.getInt(3));
        exerciseItem.setIntensity(cursor.getInt(4));
        return exerciseItem;
    }

    public void generateExerData() {
        this.open();
        this.createExerciseItem(1356998400, "Cardio", 60, 5);
        this.createExerciseItem(1357257600, "Cardio", 60, 5);
        this.createExerciseItem(1357689600, "Cardio", 60, 6);
        this.createExerciseItem(1358035200, "Cardio", 60, 6);
        this.createExerciseItem(1358467200, "Cardio", 75, 9);
        this.createExerciseItem(1359504000, "Cardio", 75, 9);
        this.createExerciseItem(1356998400, "Cardio", 75, 10);
        this.createExerciseItem(1360627200, "Cardio", 90, 12);
        this.createExerciseItem(1361232000, "Cardio", 90, 12);
        this.createExerciseItem(1362009600, "Cardio", 90, 11);
        this.createExerciseItem(1362268800, "Cardio", 120, 16);
        this.createExerciseItem(1362873600, "Cardio", 120, 14);
        this.createExerciseItem(1363305600, "Cardio", 90, 10);

        this.createExerciseItem(1356998400, "Strength", 3, 5);
        this.createExerciseItem(1357257600, "Strength", 3, 5);
        this.createExerciseItem(1357689600, "Strength", 3, 10);
        this.createExerciseItem(1358035200, "Strength", 3, 10);
        this.createExerciseItem(1358467200, "Strength", 3, 15);
        this.createExerciseItem(1359504000, "Strength", 4, 5);
        this.createExerciseItem(1356998400, "Strength", 4, 5);
        this.createExerciseItem(1360627200, "Strength", 4, 10);
        this.createExerciseItem(1361232000, "Strength", 4, 12);
        this.createExerciseItem(1362009600, "Strength", 3, 15);
        this.createExerciseItem(1362268800, "Strength", 3, 15);
        this.createExerciseItem(1362873600, "Strength", 3, 15);
        this.createExerciseItem(1363305600, "Strength", 3, 20);
        this.close();
    }

    public List<ExerciseItem> getLast30DaysExerciseItems() {
        List<ExerciseItem> exerciseItemList = new ArrayList<ExerciseItem>();

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        Date d = cal.getTime();
        long thirtyDaysAgo = d.getTime() / 1000;

        this.open();
        Cursor cursor = db.query(SQLiteHelper.TABLE_EXERCISE, columns, SQLiteHelper.COLUMN_EXER_DATE + " > " + thirtyDaysAgo, null, null, null, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            ExerciseItem i = cursorToExerItem(cursor);
            exerciseItemList.add(i);
            cursor.moveToNext();
        }

        this.close();
        cursor.close();
        return exerciseItemList;
    }

    public List<ExerciseItem> getCardioItems(List<ExerciseItem> itemList) {
        List<ExerciseItem> cardioList = new ArrayList<ExerciseItem>();
        for (ExerciseItem i: itemList) {
            if(i.getType().equalsIgnoreCase("Cardio")) {
                cardioList.add(i);
            }
        }
        return cardioList;
    } // end getCardioItems

    public List<ExerciseItem> getStrengthItems(List<ExerciseItem> itemList) {
        List<ExerciseItem> strengthList = new ArrayList<ExerciseItem>();
        for (ExerciseItem i: itemList) {
            if(i.getType().equalsIgnoreCase("Strength")) {
                strengthList.add(i);
            }
        }
        return strengthList;
    } // end getStrengthItems
}
