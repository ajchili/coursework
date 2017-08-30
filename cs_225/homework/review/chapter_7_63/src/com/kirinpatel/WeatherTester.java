/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.util.Scanner;

/**
 * This class is a tester for the Weather class.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see java.lang.String
 * @see java.util.Scanner
 * @see com.kirinpatel.Weather
 */
public class WeatherTester {
    
    /**
     * Tests all of the getters and setters of the Weather class.
     * 
     * @param args Main arguments
     */
    public static void main(String[] args) {
        // Initialize variables
        Weather weather = new Weather(70, "sunny");
        Weather newWeather = new Weather(70, "sunny");
        Scanner s = new Scanner(System.in);
        String input;
        
        // Test equals
        System.out.println(weather.equals(newWeather));
        newWeather.setSkyCondition("cloudy");
        System.out.println(weather.equals(newWeather) + "\n");
        
        // Test weather
        do {
            System.out.print("Please enter a temperature (integer): ");
            weather.setTemperature(s.nextInt());
            System.out.print("Please enter a sky condition (sunny, snowy, cloudy, rainy): ");
            weather.setSkyCondition(s.next());
            System.out.println(weather.toString() + "\nType \'n\' to quit or \'y\' to continue.");
            input = s.next();
        } while(!input.toLowerCase().equals("n"));
    }
    
}
