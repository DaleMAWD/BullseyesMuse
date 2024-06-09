package com.example.gunnitdunnit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager {

    private MyDatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public DatabaseManager(Context context) {
        dbHelper = new MyDatabaseHelper(context);
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long insert(String name, int age, String layoutName) {
        ContentValues values = new ContentValues();
        values.put(MyDatabaseContract.MyTable.COLUMN_NAME, name);
        values.put(MyDatabaseContract.MyTable.COLUMN_AGE, age);
        values.put(MyDatabaseContract.MyTable.COLUMN_LAYOUT_NAME, layoutName);
        return db.insert(MyDatabaseContract.MyTable.TABLE_NAME, null, values);
    }

    public Cursor getAllRecords() {
        return db.query(
                MyDatabaseContract.MyTable.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    public int update(int id, String name, int age, String layoutName) {
        ContentValues values = new ContentValues();
        values.put(MyDatabaseContract.MyTable.COLUMN_NAME, name);
        values.put(MyDatabaseContract.MyTable.COLUMN_AGE, age);
        values.put(MyDatabaseContract.MyTable.COLUMN_LAYOUT_NAME, layoutName);
        return db.update(
                MyDatabaseContract.MyTable.TABLE_NAME,
                values,
                MyDatabaseContract.MyTable._ID + "=?",
                new String[]{String.valueOf(id)}
        );
    }

    public int delete(int id) {
        return db.delete(
                MyDatabaseContract.MyTable.TABLE_NAME,
                MyDatabaseContract.MyTable._ID + "=?",
                new String[]{String.valueOf(id)}
        );
    }

    public Cursor searchItems(String query) {
        String[] projection = {
                MyDatabaseContract.MyTable._ID,
                MyDatabaseContract.MyTable.COLUMN_NAME,
                MyDatabaseContract.MyTable.COLUMN_AGE,
                MyDatabaseContract.MyTable.COLUMN_LAYOUT_NAME
        };

        String selection = MyDatabaseContract.MyTable.COLUMN_NAME + " LIKE ?";
        String[] selectionArgs = { "%" + query + "%" };

        return db.query(
                MyDatabaseContract.MyTable.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
    }
}
