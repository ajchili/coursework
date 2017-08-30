/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.util.Scanner;

/**
 * This class is a tester for the WebStore class.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see com.kirinpatel.WebStore
 */
public class WebStoreTester {
    
    /**
     * Tests all of the methods of the WebStore class.
     * 
     * @param args Main arguments
     */
    public static void main(String[] args) {
        // Initialize variables
        Scanner s = new Scanner(System.in);
        WebStore webStore = new WebStore(" ", " ");
        
        // Get input
        do {
            System.out.print("Please enter the web store url: ");
            webStore.setURL(s.next());
        } while (webStore.getURL().length() <= 0);
        
        do {
            System.out.print("Please enter the programming language used by the web store: ");
            webStore.setLanguage(s.next());
        } while (webStore.getLanguage().length() <= 0);
        
        // Display store information
        s.close();
        System.out.println("\n" + webStore.toString());
    }
}
