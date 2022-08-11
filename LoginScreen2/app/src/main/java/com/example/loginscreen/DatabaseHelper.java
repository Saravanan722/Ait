package com.example.loginscreen;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = " UserManager.db";
    private static final String TABLE_USER = "user";
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";
    private static final String COLUMN_USER_MOBILE = "user_mobile";
    private static String CR5EATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "(" + COLUMN_USER_ID + "AUTOINCREMENTINTEGER"
            + COLUMN_USER_NAME + "TEXT" + COLUMN_USER_EMAIL + "TEXT" + COLUMN_USER_PASSWORD + "TEXT" + COLUMN_USER_MOBILE + "TEXT" + ")";
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CR5EATE_USER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_USER_TABLE);
        onCreate(db);

    }

    public void addUser(UserDatails userDatails) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, userDatails.getName());
        values.put(COLUMN_USER_EMAIL, userDatails.getEmail());
        values.put(COLUMN_USER_PASSWORD, userDatails.getPassword());
        values.put(COLUMN_USER_MOBILE, userDatails.getMobile());
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    @SuppressLint("Range")
    public List<UserDatails> getAllUser() {
        String[] column = {
                COLUMN_USER_ID,
                COLUMN_USER_NAME,
                COLUMN_USER_EMAIL,
                COLUMN_USER_PASSWORD,
                COLUMN_USER_MOBILE
        };

        String sortOrder = COLUMN_USER_NAME + "ASC";
        List<UserDatails> userDatailsList = new ArrayList<UserDatails>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER, null,
                null,
                null,
                null,
                null,
                null,
                sortOrder);
        if (cursor.moveToFirst()) {
            do {
                UserDatails userDatails = new UserDatails();
                userDatails.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                userDatails.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                userDatails.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                userDatails.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
                userDatails.setMobile(cursor.getString(cursor.getColumnIndex(COLUMN_USER_MOBILE)));
                userDatailsList.add(userDatails);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return userDatailsList;
    }

    public boolean checkUser(String email) {
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?";
        String[] selectionArgs = {email};
        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    public boolean checkUser(String email, String password) {
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }
}
