package com.kirinpatel.criminalintent.util;

import java.util.Date;
import java.util.UUID;

public class Crime {

    private UUID uid;
    private String title;
    private Date date;
    private boolean solved;

    public Crime(UUID uuid) {
        uid = uuid;
    }

    public Crime() {
        this(UUID.randomUUID());
        date = new Date(System.currentTimeMillis());
    }

    public Crime(String title) {
        this();
        this.title = title;
    }

    public Crime(String title, boolean solved) {
        this(title);
        this.solved = solved;
    }

    public UUID getUid() {
        return uid;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public String getPhotoFilename() {
        return "IMG_" + getUid().toString() + ".jpg";
    }
}
