/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.util.Random;
import java.util.Scanner;

/**
 * This class is a tester for the ArrayIndexOutOfBounds class.
 * 
 * @author Kirin Patel
 * @version 1.0
 */
public class ArrayIndexOutOfBoundsExceptionTester {
    
    private static int[] numbers;
    
    /**
     * Tests all of the getters and setters of the
     * ArrayIndexOutOfBoundsException class.
     * 
     * @param args Main arguments
     */
    public static void main(String[] args) {
        // Initiate variables
        Random r = new Random();
        Scanner s = new Scanner(System.in);
        numbers = new int[100];
        
        for (int i = 0; i < 100; i++) {
            numbers[i] = r.nextInt();
        }
        
        // Get input and display information
        do {
            System.out.print("Please select an index to view: ");
            while (!s.hasNextInt()) {
                System.out.print("Please select an index to view: ");
                s.next();
            }
            int index = s.nextInt();
            System.out.println("Value at index " + index + ": " + getNumber(index));
        } while(true);
    }
    
    private static int getNumber(int index) throws ArrayIndexOutOfBounds {
        if (index >= 0 && index <= 99) {
            return numbers[index];
        } else {
            throw new ArrayIndexOutOfBounds("Out of bounds.");
        }
    }
}
