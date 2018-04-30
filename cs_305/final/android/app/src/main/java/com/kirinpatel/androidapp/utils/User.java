package com.kirinpatel.androidapp.utils;

import java.io.Serializable;

public class User implements Serializable {

    private String id;
    private String name;
    private int age = -1;

    public User(String id) {
        this.id = id;
    }

    public User(String id, String name) {
        this(id);
        this.name = name;
    }

    public User(String id, String name, int age) {
        this(id, name);
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
