/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

/**
 * This class is a container for all information pertaining to Fruit.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see com.kirinpatel.Food
 */
public class Fruit extends Food {
    
    private String season;
    
    /**
     * Main constructor that will set the number of calories and the season of a
     * fruit.
     * 
     * @param numberOfCalories Number of calories
     * @param season Season of fruit
     */
    public Fruit(int numberOfCalories, String season) {
        super("a fruit", numberOfCalories);
        setSeason(season);
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
     * Provides season of a fruit.
     * 
     * @return Returns season
     */
    public String getSeason() {
        return season;
    }
    
    /**
     * Sets season of a fruit.
     * 
     * @param season Season of fruit
     */
    public void setSeason(String season) {
        if (season.length() > 0) {
            this.season = season;
        } else {
            // Error handling
            System.out.println("ERROR: SEASON MUST BE PROVIDED");
            this.season = "";
        }
    }
}
