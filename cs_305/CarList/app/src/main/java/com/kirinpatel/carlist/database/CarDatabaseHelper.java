package com.kirinpatel.carlist.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CarDatabaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "carBase.db";

    public CarDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + Schema.CarTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                Schema.CarTable.Cols.UUID + ", " +
                Schema.CarTable.Cols.MAKE + ", " +
                Schema.CarTable.Cols.MODEL + ", " +
                Schema.CarTable.Cols.TYPE + ", " +
                Schema.CarTable.Cols.YEAR + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
