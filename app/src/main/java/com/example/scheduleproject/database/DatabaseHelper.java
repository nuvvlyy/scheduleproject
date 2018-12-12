package com.example.scheduleproject.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "phone.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "phone";
    public static final String COL_ID = "_id";
    public static final String COL_TITLE = "title";
    public static final String COL_NUMBER = "number";
    public static final String COL_IMAGE = "image";

    private static final String SQL_CREATE_TABLE_SCHEDLULE
            = "CREATE TABLE " + TABLE_NAME + "("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_TITLE + " TEXT,"
            + COL_NUMBER + " TEXT,"
            + COL_IMAGE + " TEXT"
            + ")";
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
