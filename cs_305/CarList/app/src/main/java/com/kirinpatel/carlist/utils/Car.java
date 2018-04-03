package com.kirinpatel.carlist.utils;

import java.util.UUID;

public class Car {

    private UUID uuid;
    private String make;
    private String model;
    private String type;
    private int year;

    public Car() {
        this.uuid = UUID.randomUUID();
    }

    public Car(UUID uuid) {
        this.uuid = uuid;
    }

    public Car(String make, String model, String type, int year) {
        this();
        this.make = make;
        this.model = model;
        this.type = type;
        this.year = year;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getType() {
        return type;
    }

    public int getYear() {
        return year;
    }

    public String getPhotoFilename() {
        return "IMG_" + getUuid().toString() + ".jpg";
    }
}
