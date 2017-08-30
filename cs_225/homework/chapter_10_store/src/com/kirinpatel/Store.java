/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

/**
 * This class is a container for information pertaining to Stores.
 * 
 * @author Kirin Patel
 * @version 1.0
 */
public class Store {
    
    public final double SALES_TAX_RATE = 0.06;
    private String name;
    
    /**
     * Basic constructor for a Store.
     * 
     * @param newName Store name
     */
    public Store(String newName) {
       setName(newName); 
    }
    
    /**
     * Provides Store in string format.
     * 
     * @return Printable version of Store
     */
    @Override
    public String toString() {
        return ("name: " + name);
    }
    
    /**
     * Provides the store name.
     * 
     * @return Returns store name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets the name of a store.
     * 
     * @param newName Store name
     */
    public void setName(String newName) {
        name = newName;
    }
    
}
