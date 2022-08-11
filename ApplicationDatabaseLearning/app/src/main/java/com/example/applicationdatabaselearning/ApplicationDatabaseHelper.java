package com.example.applicationdatabaselearning;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ApplicationDatabaseHelper extends SQLiteOpenHelper {

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + "User" + " (" +
                    "email" + " TEXT PRIMARY KEY," +
                    "name" + " TEXT," +
                    "password" + " TEXT," +
                    "mobile" + " TEXT " + ")";

    public ApplicationDatabaseHelper(@Nullable Context context) {
        super(context, "UserDatabase.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long insertUser(User user) {
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("email", user.getEmail());
        values.put("password", user.getPassword());
        values.put("mobile", user.getMobile());


        return this.getWritableDatabase().insert("User",null, values);
    }

    public ArrayList<User> getUsers() {
        String[] projection = {
                "email",
                "name",
                "password",
                "mobile"
        };

        Cursor cursor = this.getReadableDatabase().query("User", projection, null, null, null, null, null);

        ArrayList<User> users = new ArrayList<User>();
        while (cursor.moveToNext()) {
            String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String password = cursor.getString(cursor.getColumnIndexOrThrow("password"));
            String mobile = cursor.getString(cursor.getColumnIndexOrThrow("mobile"));

            User user = new User();
            user.setEmail(email);
            user.setName(name);
            user.setPassword(password);
            user.setMobile(mobile);

            users.add(user);
        }
        return users;
    }

    @Nullable
    public User getUserByEmail(String email) {
        String[] projection = {
                "email",
                "name",
                "password",
                "mobile"
        };

        String selection = " email = ?";
        String[] selectionArgs = {
                email
        };

        Cursor cursor = this.getReadableDatabase().query("User", projection, selection, selectionArgs, null, null, null,null);

        User user =new User();
        while (cursor.moveToNext()) {
            email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String password = cursor.getString(cursor.getColumnIndexOrThrow("password"));
            String mobile = cursor.getString(cursor.getColumnIndexOrThrow("mobile"));

           user.setEmail(email);
            user.setName(name);
            user.setPassword(password);
            user.setMobile(mobile);
            break;
        }
        return user;
    }
}
