/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

/**
 * This class is a container for all information pertaining to Motorized
 * Vehicle.
 * 
 * @author Kirin Patel
 * @verion 1.0
 * @see com.kirinpatel.Vehicle
 */
public class MotorizedVehicle extends Vehicle {
    
    private double evd;
    
    /**
     * Main constructor that will set the owner name, number of wheels, and the
     * engine volume displacement of a motorized vehicle.
     * 
     * @param ownerName Name of owner
     * @param numberOfWheels Number of wheels
     * @param evd Engine volume displacement in liters
     */
    public MotorizedVehicle(String ownerName, int numberOfWheels, double evd) {
        super(ownerName, numberOfWheels);
        setEVD(evd);
    }
    
    /**
     * Provides MotorizedVehicle class in string format.
     * 
     * @return Returns printable version of MotorizedVehicle
     */
    @Override
    public String toString() {
        String returnValue = "Owner name: " + ownerName;
        returnValue += "\nNumber of wheels: " + numberOfWheels;
        returnValue += "\nEngine volume displacement (liters): " + evd;
        returnValue += "\nHorsepower: " + getHorsepower();
        return returnValue;
    }
    
    /**
     * Provides engine volume displacement.
     * 
     * @return Returns engine volume displacement
     */
    public double getEVD() {
        return evd;
    }
    
    /**
     * Provides horsepower.
     * 
     * @return Returns horsepower
     */
    public double getHorsepower() {
        return evd * numberOfWheels;
    }
    
    /**
     * Sets engine volume displacement.
     * 
     * @param evd Engine volume displacement
     */
    public void setEVD(double evd) {
        if (evd > 0.0) {
            this.evd = evd;
        } else {
            // Error handling
            System.out.println("ERROR: ENGINE VOLUME DISPLACEMENT MUST BE GREATER THAN 0");
            this.evd = -1.0;
        }
    }
}
