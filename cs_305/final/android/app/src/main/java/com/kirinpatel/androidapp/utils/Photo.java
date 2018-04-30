package com.kirinpatel.androidapp.utils;

public class Photo {

    private String id;
    private User user;
    private String url;

    public Photo(String id, User user, String url) {
        this.id = id;
        this.user = user;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getUrl() {
        return url;
    }
}
