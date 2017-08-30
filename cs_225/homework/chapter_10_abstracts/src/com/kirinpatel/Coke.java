/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

/**
 * This class is a container for all information pertaining to Coke.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see com.kirinpatel.Food
 */
public class Coke extends Food {
    
    private double viscosity;
    
    /**
     * Main constructor that will set the viscosity of a coke.
     * 
     * @param viscosity Viscosity
     */
    public Coke(double viscosity) {
        super("a wonderful soda", 140);
        setViscosity(viscosity);
    }
    
    /**
     * Provides the number of calories based upon the number of servings
     * specified.
     * 
     * @param servings Number of servings
     * @return Returns number of calories
     */
    @Override
    public int getCaloriesBasedOnNumberOfServings(int servings) {
        return numberOfCalories * servings;
    }
    
    /**
     * Provides viscosity.
     * 
     * @return Returns viscosity
     */
    public double getViscosity() {
        return viscosity;
    }
    
    /**
     * Sets viscosity.
     * 
     * @param viscosity Viscosity
     */
    public void setViscosity(double viscosity) {
        if (viscosity < 0.0) {
            // Error handling
            System.out.println("ERROR: VISCOSITY MUST BE GREATER THAN OR EQUAL TO 0");
            this.viscosity = -1.0;
        } else if (viscosity > 1.0) {
            // Error handling
            System.out.println("ERROR: VISCOSITY MUST BE LESS THAN OR EQUAL TO 1");
            this.viscosity = -1.0;
        } else {
            this.viscosity = viscosity;
        }
    }
}
