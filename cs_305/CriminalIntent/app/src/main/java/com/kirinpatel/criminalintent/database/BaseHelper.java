package com.kirinpatel.criminalintent.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "crimeBase.db";

    public BaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + Schema.CrimeTable.NAME + "(" +
            " _id integer primary key autoincrement, " +
            Schema.CrimeTable.Cols.UUID + ", " +
            Schema.CrimeTable.Cols.TITLE + ", " +
            Schema.CrimeTable.Cols.DATE + ", " +
            Schema.CrimeTable.Cols.SOLVED + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
