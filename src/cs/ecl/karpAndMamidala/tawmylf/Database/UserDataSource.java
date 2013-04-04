package cs.ecl.karpAndMamidala.tawmylf.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import cs.ecl.karpAndMamidala.tawmylf.Database.SQLiteHelper;
import cs.ecl.karpAndMamidala.tawmylf.Models.User;

public class UserDataSource {
    private SQLiteDatabase db;
    private SQLiteHelper dbHelper;
    private String[] columns = { SQLiteHelper.COLUMN_ID,
                                 SQLiteHelper.COLUMN_USER_NAME,
                                 SQLiteHelper.COLUMN_USER_GENDER,
                                 SQLiteHelper.COLUMN_USER_ADDRESS,
                                 SQLiteHelper.COLUMN_USER_PHONE,
                                 SQLiteHelper.COLUMN_USER_EMERG_NAME,
                                 SQLiteHelper.COLUMN_USER_EMERG_ADDRESS,
                                 SQLiteHelper.COLUMN_USER_EMERG_PHONE};

    public UserDataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLiteException {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public boolean doesUserExist() {
        Cursor cursor = db.query(SQLiteHelper.TABLE_USER, columns,
                null, null, null, null, null);
        int count = cursor.getCount();
        return count > 0 ? true : false;
    }

    public User createUser(String name, int gender, String address, String phone,
                           String emerg_name, String emerg_address, String emerg_phone) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_USER_NAME, name);
        values.put(SQLiteHelper.COLUMN_USER_GENDER, gender);
        values.put(SQLiteHelper.COLUMN_USER_ADDRESS, address);
        values.put(SQLiteHelper.COLUMN_USER_PHONE, phone);
        values.put(SQLiteHelper.COLUMN_USER_EMERG_NAME, emerg_name);
        values.put(SQLiteHelper.COLUMN_USER_EMERG_ADDRESS, emerg_address);
        values.put(SQLiteHelper.COLUMN_USER_EMERG_PHONE, emerg_phone);

        long insertId = db.insert(SQLiteHelper.TABLE_USER, null, values);
        Cursor cursor = db.query(SQLiteHelper.TABLE_USER, columns,
                SQLiteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        User newUser = cursorToUser(cursor);
        cursor.close();
        return newUser;
    }

    private User cursorToUser(Cursor cursor) {
        User user = new User();
        user.setId(cursor.getLong(0));
        user.setName(cursor.getString(1));
        user.setGender(cursor.getInt(2));
        user.setAddress(cursor.getString(3));
        user.setPhone(cursor.getString(4));
        user.setEmergName(cursor.getString(5));
        user.setEmergAddress(cursor.getString(6));
        user.setEmergPhone(cursor.getString(7));
        return user;
    }

}
