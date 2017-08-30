/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

/**
 * This class is a container for information pertaining to weather conditions,
 * it is used to store and manipulate temperature and sky condition statistics.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see java.lang.string
 */
public class Weather {
    
    private int temperature;
    private String skyCondition;
    
    /**
     * Main constructor for the Weather class, this will take in a temperature
     * and sky conditions while handling any input errors.
     * 
     * @param temperature Current temperature in degrees Fahrenheit
     * @param skyCondition Current sky condition
     */
    public Weather(int temperature, String skyCondition) {
        setTemperature(temperature);
        setSkyCondition(skyCondition);
    }
    
    /**
     * Provides all of the weather statistics in string format.
     * 
     * @return A printable version of Weather object
     */
    @Override
    public String toString() {
        return "Current temperature " + convertTemperature(temperature) + " degrees celcius with " + skyCondition + " skies.";
    }
    
    /**
     * Checks to see if the input is equal to this Weather.
     * 
     * @param o Object to be tested
     * @return Boolean value of if inputed object is equal to this object
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Weather)) {
            return false;
        } else {
            Weather objW = (Weather) o;
            if (temperature != objW.getTemperature()) {
                return false;
            }
            
            if (!skyCondition.equals(objW.getSkyCondition())) {
                return false;
            }
            return true;
        }
    }
    
    /**
     * Used to set the temperature statistic.
     * Temperatures range from -50 to 150 degrees Fahrenheit.
     * 
     * @param temperature Current temperature in degrees Fahrenheit
     */
    public void setTemperature(int temperature) {
        if (temperature < -50 || temperature > 150) {
            // Error handling
            System.out.println("Temperature is out of bounds, please enter a temperature between -50 and 150.");
            this.temperature = 70;
        } else {
            this.temperature = temperature;
        }
    }
    
    /**
     * Used to set the sky condition statistic.
     * Sky conditions include sunny, snowy, cloudy, and rainy.
     * 
     * @param skyCondition Current sky condition
     */
    public void setSkyCondition(String skyCondition) {
        switch (skyCondition) {
            case "sunny":
                this.skyCondition = skyCondition;
                break;
            case "snowy":
                this.skyCondition = skyCondition;
                break;
            case "cloudy":
                this.skyCondition = skyCondition;
                break;
            case "rainy":
                this.skyCondition = skyCondition;
                break;
            default:
                System.out.println("Unknown sky condition");
                this.skyCondition = "sunny";
        }
    }
    
    /**
     * Returns the stored temperature statistic.
     * 
     * @return Temperature in degrees Fahrenheit
     */
    public int getTemperature() {
        return temperature;
    }
    
    /**
     * Returns the stored sky condition statistic.
     * 
     * @return Sky condition
     */
    public String getSkyCondition() {
        return skyCondition;
    }
    
    /**
     * Converts temperature from degrees Fahrenheit to degrees Celsius and
     * returns value.
     * 
     * @param temperature
     * @return Converted temperature
     */
    public double convertTemperature(int temperature) {
        return ((temperature - 32.0) * (5.0 / 9.0));
    }
    
}
