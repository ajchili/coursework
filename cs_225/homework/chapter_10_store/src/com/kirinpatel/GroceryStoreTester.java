/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.util.Scanner;

/**
 * This class is a tester for the GroceryStore class.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see com.kirinpatel.GroceryStore
 */
public class GroceryStoreTester {
    
    /**
     * Tests all of the methods of the GroceryStore class.
     * 
     * @param args Main arguments
     */
    public static void main(String[] args) {
        // Initialize variables
        Scanner s = new Scanner(System.in);
        GroceryStore groceryStore = new GroceryStore(1, false);
        
        // Get input
        do {
            System.out.print("Please enter the annual revenue of the grocery store: ");
            while(!s.hasNextDouble()) {
                System.out.print("Please enter the annual revenue of the grocery store: ");
                s.next();
            }
            groceryStore.setAnnualRevenue(s.nextDouble());
        } while(groceryStore.getAnnualRevenue() < 0.0);
        
        System.out.print("Is the store a part of a chain (true/false): ");
        groceryStore.setChain(s.nextBoolean());
        
        // Display information
        s.close();
        System.out.println("\n" + groceryStore.toString());
    }
}
