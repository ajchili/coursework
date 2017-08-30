/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.util.Random;

/**
 * This class is a tester for the Array class.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see java.util.Random
 * @see com.kirinpatel.Array
 */
public class ArrayTester {
    
    /**
     * Tests all of the getters and setters of the Array class.
     * 
     * @param args Main arguments
     */
    public static void main(String[] args) {
        // Create Variables
        Random random = new Random();
        
        float[] floats = new float[random.nextInt(75) + 25];
        for (int i = 0; i < floats.length; i++) {
            floats[i] = random.nextFloat();
        }
        boolean[] bools = new boolean[random.nextInt(75) + 25];
        for (int i = 0; i < bools.length; i++) {
            bools[i] = random.nextBoolean();
        }
        int[] ints = new int[random.nextInt(75) + 25];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = random.nextInt(150);
        }
        
        Array array = new Array();
        
        // Display floats (before and after)
        String displayFloat = "Current floats:";
        for (float f : floats) {
            displayFloat += "  " + f;
        }
        System.out.println(displayFloat);
        array.doubleValues(floats);
        displayFloat = "Doubled floats:    ";
        for (float f : floats) {
            displayFloat += "  " + f;
        }
        System.out.println(displayFloat);
        
        // Display percent true
        System.out.println("\nPercent true: " + array.percentTrue(bools));
        
        // Display number of Integers greater than 100
        boolean isGreaterThan[] = array.isGreaterThan(ints);
        String displayIsGreaterThan = "\nInteger values:";
        for (int i : ints) {
            displayIsGreaterThan += "  " + i;
        }
        System.out.println(displayIsGreaterThan);
        displayIsGreaterThan = "Numbers greater than 100: ";
        for (boolean b : isGreaterThan) {
            displayIsGreaterThan += "  " + b;
        }
        System.out.println(displayIsGreaterThan);
    }
    
}
