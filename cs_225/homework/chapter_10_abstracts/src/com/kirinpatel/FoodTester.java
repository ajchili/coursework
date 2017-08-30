/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.util.Scanner;

/**
 * This class is a tester for the Food class as well as its subclasses.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see com.kirinpatel.Coke
 * @see com.kirinpatel.Fruit
 */
public class FoodTester {
    
    /**
     * Tests all of the methods of the Food class as well as its 
     * subclasses.
     * 
     * @param args Main arguments
     */
    public static void main(String[] args) {
        // Initialize Variables
        Scanner s = new Scanner(System.in);
        Coke coke = new Coke(1);
        Fruit fruit = new Fruit(1, "null");
        
        // Get input
        do {
            System.out.print("Please enter the viscosity of your drink: ");
            while(!s.hasNextDouble()) {
                System.out.print("Please enter the viscosity of your drink: ");
                s.nextDouble();
            }
            coke.setViscosity(s.nextDouble());
        } while (coke.getViscosity() < 0.0 || coke.getViscosity() > 1.0);
        
        do {
            System.out.print("Please enter the number of calories in your fruit: ");
            while(!s.hasNextInt()) {
                System.out.print("Please enter the number of calories in your fruit: ");
                s.nextInt();
            }
            fruit.setNumberOfCalories(s.nextInt());
        } while (fruit.getNumberOfCalories() < 0);
        
        do {
            System.out.print("Please enter season of your fruit: ");
            while(!s.hasNextLine()) {
            System.out.print("Please enter season of your fruit: ");
                s.next();
            }
            fruit.setSeason(s.nextLine());
        } while(fruit.getSeason().length() <= 0);
        
        // Display information
        System.out.println("\n" + coke.toString() + "\nNumber of calories in 4 servings: " + coke.getCaloriesBasedOnNumberOfServings(4));
        System.out.println("\n" + fruit.toString() + "\nSeason: " + fruit.getSeason() + "\nNumber of calories in 4 servings: " + fruit.getCaloriesBasedOnNumberOfServings(4));
    }
}
