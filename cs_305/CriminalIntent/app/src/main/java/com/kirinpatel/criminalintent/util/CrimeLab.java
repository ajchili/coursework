package com.kirinpatel.criminalintent.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.kirinpatel.criminalintent.database.CrimeDatabaseHelper;
import com.kirinpatel.criminalintent.database.CrimeCursorWrapper;
import com.kirinpatel.criminalintent.database.Schema;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeLab {

    private static CrimeLab lab;
    private Context context;
    private SQLiteDatabase database;

    public static CrimeLab get(Context context) {
        if (lab == null) {
            lab = new CrimeLab(context);
        }

        return lab;
    }

    private CrimeLab(Context context) {
        this.context = context;
        database = new CrimeDatabaseHelper(context).getWritableDatabase();
    }

    public List<Crime> getCrimes() {
        List<Crime> crimes = new ArrayList<>();

        CrimeCursorWrapper cursor = queryCrimes(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                crimes.add(cursor.getCrime());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return crimes;
    }

    public Crime getCrime(UUID uid) {
        CrimeCursorWrapper cursor = queryCrimes(Schema.CrimeTable.Cols.UUID + " = ? ",
                new String[] { uid.toString() });

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getCrime();
        } finally {
            cursor.close();
        }
    }

    public File getPhotoFile(Crime crime) {
        File directory = context.getFilesDir();
        return new File(directory, crime.getPhotoFilename());
    }

    public void addCrime(Crime crime) {
        ContentValues values = getContentValues(crime);
        database.insert(Schema.CrimeTable.NAME, null, values);
    }

    public void updateCrime(Crime crime) {
        String uuid = crime.getUid().toString();
        ContentValues values = getContentValues(crime);

        database.update(Schema.CrimeTable.NAME,
                values,
                Schema.CrimeTable.Cols.UUID + " = ?",
                new String[] { uuid });
    }

    private static ContentValues getContentValues(Crime crime) {
        ContentValues values = new ContentValues();

        values.put(Schema.CrimeTable.Cols.UUID, crime.getUid().toString());
        values.put(Schema.CrimeTable.Cols.TITLE, crime.getTitle());
        values.put(Schema.CrimeTable.Cols.DATE, crime.getDate().toString());
        values.put(Schema.CrimeTable.Cols.SOLVED, crime.isSolved() ? 1 : 0);

        return values;
    }

    private CrimeCursorWrapper queryCrimes(String clause, String[] args) {
        Cursor cursor = database.query(
                Schema.CrimeTable.NAME,
                null,
                clause,
                args,
                null,
                null,
                null);
        return new CrimeCursorWrapper(cursor);
    }
}
