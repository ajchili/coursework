/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

/**
 * This class is a container for all information pertaining to Vehicle.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see java.lang.String
 */
public abstract class Vehicle {
    
    protected String ownerName;
    protected int numberOfWheels;
    
    /**
     * Main constructor that will set the owner name and number of wheels of a
     * vehicle.
     * 
     * @param ownerName Name of owner
     * @param numberOfWheels Number of wheels
     */
    public Vehicle(String ownerName, int numberOfWheels) {
        setOwnersName(ownerName);
        setNumberOfWheels(numberOfWheels);
    }
    
    /**
     * Tests to see if the provided object equals this object.
     * 
     * @param o Object to be tested
     * @return Returns if the two objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Vehicle)) {
            return false;
        } else {
            Vehicle objV = (Vehicle) o;
            return (ownerName.equals(objV.ownerName) && numberOfWheels == objV.numberOfWheels);
        }
    }
    
    /**
     * Provides Vehicle class in string format.
     * 
     * @return Returns printable version of Vehicle
     */
    @Override
    public String toString() {
        String returnValue = "Vehicle owner: " + getOwnersName();
        returnValue += "\nNumber of wheels: " + getNumberOfWheels();
        return returnValue;
    }
    
    /**
     * Provides owner name.
     * 
     * @return Returns owner name
     */
    public String getOwnersName() {
        return ownerName;
    }
    
    /**
     * Provides number of wheels.
     * 
     * @return Returns number of wheels
     */
    public int getNumberOfWheels() {
        return numberOfWheels;
    }
    
    /**
     * Sets owner name.
     * 
     * @param ownerName Name of owner
     */
    public void setOwnersName(String ownerName) {
        if (!ownerName.equals("")) {
            this.ownerName = ownerName;
        } else {
            // Error handling
            System.out.println("ERROR: NO OWNER NAME ENTERED");
            this.ownerName = "";
        }
    }
    
    /**
     * Sets number of wheels.
     * 
     * @param numberOfWheels Number of wheels
     */
    public void setNumberOfWheels(int numberOfWheels) {
        if (numberOfWheels > 0) {
            this.numberOfWheels = numberOfWheels;
        } else {
            // Error handling
            System.out.println("ERROR: NUMBER OF WHEELS MUST BE GREATER THAN 0");
            this.numberOfWheels = -1;
        }
    }
    
}
