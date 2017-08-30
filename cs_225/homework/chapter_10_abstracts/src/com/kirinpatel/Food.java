/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

/**
 * This class is a container for all information pertaining to Food.
 *
 * @author Kirin Patel
 * @version 1.0
 */
public abstract class Food {
    
    protected String description;
    protected int numberOfCalories;
    
    /**
     * Main constructor that will set the description and number of calories of 
     * a food.
     * 
     * @param description Description of food
     * @param numberOfCalories Number of calories
     */
    public Food(String description, int numberOfCalories) {
        setDescription(description);
        setNumberOfCalories(numberOfCalories);
    }
    
    /**
     * Provides the number of calories based upon the number of servings
     * specified.
     * 
     * @param servings Number of servings
     * @return Returns number of calories
     */
    abstract int getCaloriesBasedOnNumberOfServings(int servings);
    
    /**
     * Provides Food class in string format.
     * 
     * @return Returns printable version of Food
     */
    @Override
    public String toString() {
        String returnValue = "This food is " + description;
        returnValue += "\nContains " + numberOfCalories + " calories";
        return returnValue;
    }
    
    /**
     * Tests to see if the provided object equals this object.
     * 
     * @param o Object to be tested
     * @return Returns if the two objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Food)) {
            return false;
        } else {
            Food objF = (Food) o;
            return (description.equals(objF.getDescription()) && numberOfCalories == objF.getNumberOfCalories());
        }
    }
    
    /**
     * Provides food description.
     * 
     * @return Returns description of food
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Provides number of calories.
     * 
     * @return Returns number of calories
     */
    public int getNumberOfCalories() {
        return numberOfCalories;
    }
    
    /**
     * Sets description.
     * 
     * @param description Description of food
     */
    public void setDescription(String description) {
        if (description.length() > 0) {
            this.description = description;
        } else {
            // Error handling
            System.out.println("ERROR: DESCRIPTION MUST BE PROVIDED");
            this.description = "";
        }
    }
    
    /**
     * Sets number of calories.
     * 
     * @param numberOfCalories Number of calories
     */
    public void setNumberOfCalories(int numberOfCalories) {
        if (numberOfCalories > 0) {
            this.numberOfCalories = numberOfCalories;
        } else {
            // Error handling
            System.out.println("ERROR: NUMBER OF CALORIES MUST BE GREATER THAN 0");
            this.numberOfCalories = -1;
        }
    }
}
