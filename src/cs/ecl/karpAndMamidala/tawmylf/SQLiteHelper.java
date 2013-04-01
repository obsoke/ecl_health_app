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
    public static final String TABLE_USER = "users";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMERG_NAME = "emerg_name";
    public static final String COLUMN_EMERG_ADDRESS = "emerg_address";
    public static final String COLUMN_EMERG_PHONE = "emerg_phone";

    private static final String DB_NAME = "tawmylf.db";
    private static final int DB_VERSION = 1;

    private static final String DB_CREATE = "create table "
            + TABLE_USER + "(" + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " text not null, "
            + COLUMN_GENDER + " integer not null, "
            + COLUMN_ADDRESS + " text not null, "
            + COLUMN_PHONE + " text not null, "
            + COLUMN_EMERG_NAME + " text not null, "
            + COLUMN_EMERG_ADDRESS + " text not null, "
            + COLUMN_EMERG_PHONE + " text not null);";

    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }
}
