/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

/**
 * This class is a container for information pertaining to music store.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see com.kirinpatel.Store
 */
public class MusicStore extends Store {
    
    private int titles;
    private String address;
    
    /**
     * Main constructor that will set the number of titles the store has
     * and the address of the Music Store.
     * 
     * @param titles Number of titles
     * @param address Address of store
     */
    public MusicStore(int titles, String address) {
        super("Music Store");
        setTitles(titles);
        setAddress(address);
    }
    
    /**
     * Provides MusicStore in string format.
     * 
     * @return Returns printable version of MusicStore
     */
    @Override
    public String toString() {
        String returnValue = super.toString();
        returnValue += "\nNumber of titles: " + getTitles();
        returnValue += "\nAddress: " + getAddress();
        return returnValue;
    }
    
    /**
     * Provides number of titles.
     * 
     * @return Returns the number of titles at the music store
     */
    public int getTitles() {
        return titles;
    }
    
    /**
     * Provides music store address.
     * 
     * @return Returns the address of the music store
     */
    public String getAddress() {
        return address;
    }
    
    /**
     * Sets the number of titles.
     * 
     * @param titles Number of titles
     */
    public void setTitles(int titles) {
        if (titles > 0) {
            this.titles = titles;
        } else {
            // Error handling
            System.out.println("ERROR: NUMBER OF TITLES MUST BE GREATER THAN 0");
            this.titles = -1;
        }
    }
    
    /**
     * Sets the address of the Music Store
     * 
     * @param address Address of store
     */
    public void setAddress(String address) {
        if (address.length() > 0) {
            this.address = address;
        } else {
            // Error handling
            System.out.println("ERROR: ADDRESS MUST BE PROVIDED");
            this.address = "";
        }
    }
    
}
