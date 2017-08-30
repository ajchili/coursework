/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.util.Scanner;

/**
 * This class is a tester for the MusicStore class.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see com.kirinpatel.BikeStore
 */
public class BikeStoreTester {
    
    /**
     * Tests all of the methods of the BikeStore class.
     * 
     * @param args Main arguments
     */
    public static void main(String[] args) {
        // Initialize variables
        Scanner s = new Scanner(System.in);
        BikeStore bikeStore = new BikeStore(1, false);
        
        // Get input
        do {
            System.out.print("Please enter the number of brands at your store: ");
            while(!s.hasNextInt()) {
                System.out.print("Please enter the number of brands at your store: ");
                s.next();
            }
            bikeStore.setNumberOfBrands(s.nextInt());
        } while(bikeStore.getNumberOfBrands() < 0);
        
        System.out.print("Does your bike store sponsor bike clubs (true/false): ");
        bikeStore.setSponsored(s.nextBoolean());
        
        // Display information
        s.close();
        System.out.println("\n" + bikeStore.toString());
    }
}
