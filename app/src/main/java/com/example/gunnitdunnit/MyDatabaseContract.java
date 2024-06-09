package com.example.gunnitdunnit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.provider.BaseColumns;

public class MyDatabaseContract {
    public static final String TABLE_NAME = "my_table";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_AGE = "age";

    private MyDatabaseContract() {
    }

    public static class MyTable implements BaseColumns {
        public static final String TABLE_NAME = "my_table";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_AGE = "age";
        public static final String COLUMN_LAYOUT_NAME = "layout_name";
    }
}