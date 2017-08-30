/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.util.Scanner;

/**
 * This class is a tester for the StringValidator class.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see com.kirinpatel.StringValidator
 */
public class StringValidatorTester {
    
    /**
     * Tests all of the getters and setters of the StringValidator class.
     * 
     * @param args Main arguments
     */
    public static void main(String[] args) {
        // Initialize variables
        Scanner s = new Scanner(System.in);
        String string = "";
        String delimiter = ",";
        
        // Get input
        System.out.print("Please enter a string to be validated: ");
        string = s.next();
        System.out.print("Please enter the delimiter: ");
        delimiter = s.next();
        
        StringValidator stringValidator = new StringValidator(string, delimiter.charAt(0));
        
        // Display information
        System.out.println(stringValidator.checkTokens(0));
        System.out.println(stringValidator.checkTokens(1));
        System.out.println(stringValidator.checkTokens(2));
        System.out.println(stringValidator.checkTokens(3));
    }
}
