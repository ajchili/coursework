/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel.util;

/**
 * This class holds a client as a User when connecting to a server.
 * 
 * @author Kirin Patel
 * @version 1.0
 */
public class User {
    
    private int id;
    private String username;
    
    /**
     * Main constructor that creates a user.
     * 
     * @param id User ID
     * @param username Username
     */
    public User(int id, String username) {
        this.id = id;
        this.username = username;
    }
    
    /**
     * Provides User in string format.
     * 
     * @return Returns string format of User
     */
    @Override
    public String toString() {
        return id + ": " + username;
    }
    
    /**
     * Provides User ID.
     * 
     * @return 
     */
    public int getID() {
        return id;
    }
    
    /**
     * Sets User ID.
     * 
     * @param id User ID
     */
    public void setID(int id) {
        this.id = id;
    }
    
    /**
     * Sets username.
     * 
     * @param username Username
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
