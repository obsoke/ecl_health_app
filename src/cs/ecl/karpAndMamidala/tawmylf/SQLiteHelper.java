package cs.ecl.karpAndMamidala.tawmylf;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created with IntelliJ IDEA.
 * User: dale
 * Date: 2013-03-31
 * Time: 9:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class SQLiteHelper extends SQLiteOpenHelper {
    public static final String COLUMN_ID = "_id";
    public static final String TABLE_USER = "users";
    public static final String COLUMN_USER_NAME = "name";
    public static final String COLUMN_USER_GENDER = "gender";
    public static final String COLUMN_USER_ADDRESS = "address";
    public static final String COLUMN_USER_PHONE = "phone";
    public static final String COLUMN_USER_EMERG_NAME = "emerg_name";
    public static final String COLUMN_USER_EMERG_ADDRESS = "emerg_address";
    public static final String COLUMN_USER_EMERG_PHONE = "emerg_phone";

    public static final String TABLE_BP = "bloodpressure";
    public static final String COLUMN_BP_DATE = "date";
    public static final String COLUMN_BP_SYSTOLIC_PRESSURE = "systolic_pressure";
    public static final String COLUMN_BP_DIASTOLIC_PRESSURE = "diastolic_pressure";
    public static final String COLUMN_BP_HEARTRATE = "heartrate";

    public static final String TABLE_WEIGHT = "weight";
    public static final String COLUMN_WEIGHT_DATE = "date";
    public static final String COLUMN_WEIGHT_WEIGHT = "weight";

    public static final String TABLE_EXERCISE = "exercise";
    public static final String COLUMN_EXER_DATE = "date";
    public static final String COLUMN_EXER_TYPE = "type";
    public static final String COLUMN_EXER_DURATION = "duration";
    public static final String COLUMN_EXER_INTENSITY = "intensity";

    private static final String DB_NAME = "tawmylf.db";
    private static final int DB_VERSION = 2;

    private static final String DB_USER_CREATE = "create table "
            + TABLE_USER + "(" + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_USER_NAME + " text not null, "
            + COLUMN_USER_GENDER + " integer not null, "
            + COLUMN_USER_ADDRESS + " text not null, "
            + COLUMN_USER_PHONE + " text not null, "
            + COLUMN_USER_EMERG_NAME + " text not null, "
            + COLUMN_USER_EMERG_ADDRESS + " text not null, "
            + COLUMN_USER_EMERG_PHONE + " text not null);";

    private static final String DB_BP_CREATE = "create table "
            + TABLE_BP + "(" + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_BP_DATE + " integer not null, "
            + COLUMN_BP_SYSTOLIC_PRESSURE + " real not null, "
            + COLUMN_BP_DIASTOLIC_PRESSURE + " real not null, "
            + COLUMN_BP_HEARTRATE + " real not null);";

    private static final String DB_WEIGHT_CREATE = "create table "
            + TABLE_WEIGHT + "(" + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_WEIGHT_DATE + " integer not null, "
            + COLUMN_WEIGHT_WEIGHT + " real not null);";

    private static final String DB_EXER_CREATE = "create table "
            + TABLE_EXERCISE + "(" + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_EXER_DATE + " integer not null, "
            + COLUMN_EXER_TYPE + " text not null, "
            + COLUMN_EXER_DURATION + " integer not null, "
            + COLUMN_EXER_INTENSITY + " integer not null);";


    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_USER_CREATE);
        db.execSQL(DB_BP_CREATE);
        db.execSQL(DB_WEIGHT_CREATE);
        db.execSQL(DB_EXER_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BP);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WEIGHT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXERCISE);
        onCreate(db);
    }
}
