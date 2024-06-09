package com.example.gunnitdunnit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "my_database.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + MyDatabaseContract.MyTable.TABLE_NAME + " (" +
                    MyDatabaseContract.MyTable._ID + " INTEGER PRIMARY KEY," +
                    MyDatabaseContract.MyTable.COLUMN_NAME + " TEXT," +
                    MyDatabaseContract.MyTable.COLUMN_AGE + " INTEGER," +
                    MyDatabaseContract.MyTable.COLUMN_LAYOUT_NAME + " TEXT)";  // Updated this line

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + MyDatabaseContract.MyTable.TABLE_NAME;

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}