/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.util.Scanner;

/**
 * This class is a tester for the Restaurant class.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see com.kirinpatel.Restaurant
 */
public class RestaurantTester {
    
    /**
     * Tests all of the methods of the Restaurant class.
     * 
     * @param args Main arguments
     */
    public static void main(String[] args) {
        // Initialize variables
        Scanner s = new Scanner(System.in);
        Restaurant restaurant = new Restaurant(1, 1);
        
        // Get input
        do {
            System.out.print("Please enter the number of yearly customers: ");
            while(!s.hasNextInt()) {
                System.out.print("Please enter the number of yearly customers: ");
                s.next();
            }
            restaurant.setNumberOfYearlyCustomers(s.nextInt());
        } while(restaurant.getNumberOfYearlyCustomers() < 0);
        
        do {
            System.out.print("Please enter the average customer price: ");
            while(!s.hasNextDouble()) {
                System.out.print("Please enter the average customer price: ");
                s.next();
            }
            restaurant.setAverageCustomerPrice(s.nextDouble());
        } while(restaurant.getAverageCustomerPrice() < 0.0);
        
        // Display information
        s.close();
        System.out.println("\n" + restaurant.toString());
    }
}
