package com.kirinpatel.criminalintent.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.kirinpatel.criminalintent.util.Crime;

import java.util.Date;
import java.util.UUID;

public class CrimeCursorWrapper extends CursorWrapper {

    public CrimeCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Crime getCrime() {
        String uuid = getString(getColumnIndex(Schema.CrimeTable.Cols.UUID));
        String title = getString(getColumnIndex(Schema.CrimeTable.Cols.TITLE));
        long date = getLong(getColumnIndex(Schema.CrimeTable.Cols.DATE));
        int isSolved = getInt(getColumnIndex(Schema.CrimeTable.Cols.SOLVED));

        Crime crime = new Crime(UUID.fromString(uuid));
        crime.setTitle(title);
        crime.setDate(new Date(date));
        crime.setSolved(isSolved == 1);

        return crime;
    }
}
