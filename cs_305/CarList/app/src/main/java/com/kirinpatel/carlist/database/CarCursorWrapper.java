package com.kirinpatel.carlist.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.kirinpatel.carlist.utils.Car;

import java.util.UUID;

public class CarCursorWrapper extends CursorWrapper {

    public CarCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Car getCar() {
        String uuid = getString(getColumnIndex(Schema.CarTable.Cols.UUID));
        String make = getString(getColumnIndex(Schema.CarTable.Cols.MAKE));
        String model = getString(getColumnIndex(Schema.CarTable.Cols.MODEL));
        String type = getString(getColumnIndex(Schema.CarTable.Cols.TYPE));
        int year = getInt(getColumnIndex(Schema.CarTable.Cols.YEAR));

        Car car = new Car(UUID.fromString(uuid));
        car.setMake(make);
        car.setModel(model);
        car.setType(type);
        car.setYear(year);

        return car;
    }
}