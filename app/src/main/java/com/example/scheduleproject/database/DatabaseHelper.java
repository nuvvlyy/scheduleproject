package com.example.scheduleproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "phone.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "myschedule";
    public static final String COL_ID = "_id";
    public static final String COL_TITLE = "title";
    public static final String COL_VENUE = "venue";
    public static final String COL_DATE = "date";
    public static final String COL_ENDDATE = "enddate";

    private static final String SQL_CREATE_TABLE_MYSCHE
            = "CREATE TABLE " + TABLE_NAME + "("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_TITLE + " TEXT,"
            + COL_VENUE  + " TEXT,"
            + COL_DATE + " TEXT,"
            + COL_ENDDATE+" TEXT"
            + ")";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_MYSCHE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
} // ปิดคลาส DatabaseHelper