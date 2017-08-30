/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

/**
 * This class is a container for all information pertaining to Bicycle.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see com.kirinpatel.Vehicle
 */
public class Bicycle extends Vehicle {
    
    /**
     * Main constructor that will set the name of the owner of the bicycle.
     * 
     * @param ownerName Name of owner
     */
    public Bicycle(String ownerName) {
        super(ownerName, 2);
    }
}
