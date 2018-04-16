package com.kirinpatel.flickrfetcher.utils;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Photo implements Serializable {

    private String caption;
    private String id;
    private String url;
    private Bitmap imageBitmap = null;

    public Photo(String id, String caption, String url) {
        this.id = id;
        this.caption = caption;
        this.url = url;
    }

    @Override
    public String toString() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setImageBitmap(Bitmap imageBitmap) {
        this.imageBitmap = imageBitmap;
    }

    public String getCaption() {
        return caption;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public Bitmap getImageBitmap() {
        return imageBitmap;
    }
}
