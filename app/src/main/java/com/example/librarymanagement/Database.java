package com.example.librarymanagement;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    private static final String DB_NAME = "database";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "userLogin";
    private static final String ID = "id";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    public Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USERNAME + " TEXT,"
                + PASSWORD + " TEXT)";
        db.execSQL(query);
    }

    public void addNewUser(User user) {
        SQLiteDatabase database = this.getWritableDatabase();
        
        ContentValues values = new ContentValues();
        
        values.put(USERNAME, user.getUsername());
        values.put(PASSWORD, user.getPassword());
        
        database.insert(TABLE_NAME, null, values);

        database.close();
    }

    public ArrayList<User> getAllUsers(){
        ArrayList<User> userList = new ArrayList<>();

        SQLiteDatabase database = this.getReadableDatabase();

        String select = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = database.rawQuery(select, null);

        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setUsername(cursor.getString(1));
                user.setPassword(cursor.getString(2));

                userList.add(user);
            }
            while (cursor.moveToNext());
        }

        return userList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

