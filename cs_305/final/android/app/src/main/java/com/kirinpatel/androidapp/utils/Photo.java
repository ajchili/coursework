package com.kirinpatel.androidapp.utils;

import java.io.Serializable;

public class Photo implements Serializable {

    private String id;
    private User user;
    private String title;
    private String url;

    public Photo(String id, User user, String title, String url) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
