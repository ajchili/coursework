package com.kirinpatel.carlist.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.kirinpatel.carlist.database.CarCursorWrapper;
import com.kirinpatel.carlist.database.CarDatabaseHelper;
import com.kirinpatel.carlist.database.Schema;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Cars {

    private static Cars cars;
    private Context context;
    private SQLiteDatabase database;

    public static Cars get(Context context) {
        if (cars == null) {
            cars = new Cars(context);
        }

        return cars;
    }

    private Cars(Context context) {
        this.context = context;
        database = new CarDatabaseHelper(context).getWritableDatabase();
    }

    public List<Car> getCars() {
        List<Car> cars = new ArrayList<>();

        CarCursorWrapper cursor = queryCars(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                cars.add(cursor.getCar());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return cars;
    }

    public Car getCar(UUID uuid) {
        CarCursorWrapper cursor = queryCars(Schema.CarTable.Cols.UUID + " = ? ",
                new String[] { uuid.toString() });

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getCar();
        } finally {
            cursor.close();
        }
    }

    public File getPhotoFile(Car car) {
        File directory = context.getFilesDir();
        return new File(directory, car.getPhotoFilename());
    }

    public void addCar(Car car) {
        ContentValues values = getContentValues(car);
        database.insert(Schema.CarTable.NAME, null, values);
    }

    public void updateCar(Car car) {
        String uuid = car.getUuid().toString();
        ContentValues values = getContentValues(car);

        database.update(Schema.CarTable.NAME,
                values,
                Schema.CarTable.Cols.UUID + " = ?",
                new String[] { uuid });
    }

    private static ContentValues getContentValues(Car car) {
        ContentValues values = new ContentValues();

        values.put(Schema.CarTable.Cols.UUID, car.getUuid().toString());
        values.put(Schema.CarTable.Cols.MAKE, car.getMake());
        values.put(Schema.CarTable.Cols.MODEL, car.getModel());
        values.put(Schema.CarTable.Cols.TYPE, car.getType());
        values.put(Schema.CarTable.Cols.YEAR, car.getYear());

        return values;
    }

    private CarCursorWrapper queryCars(String clause, String[] args) {
        Cursor cursor = database.query(
                Schema.CarTable.NAME,
                null,
                clause,
                args,
                null,
                null,
                null);
        return new CarCursorWrapper(cursor);
    }
}
