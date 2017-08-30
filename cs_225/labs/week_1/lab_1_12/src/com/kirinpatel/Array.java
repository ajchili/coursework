/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

/**
 * This class pertains to Chapter 8, arrays, in the text book and will house
 * any of the in class work completed during week one.
 * 
 * @author Kirin Patel
 * @version 1.0
 */
public class Array {
    
    /**
     * This method will double all of the values in the array.
     * 
     * @param array Array of floats which will be doubled
     */
    public void doubleValues(float[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] *= 2;
        }
    }
    
    /**
     * This method will calculate the percentage of true values in an array of
     * booleans.
     * 
     * @param array Array of floats
     * @return 
     */
    public double percentTrue(boolean[] array) {
        int numberOfTrue = 0;
        
        for (int i = 0; i < array.length; i++) {
            if (array[i]) {
                numberOfTrue++;
            }
        }
        
        return (double) numberOfTrue / array.length;
    }
    
    /**
     * This method returns an array of booleans corresponding to the input array,
     * each value will either be true, for an integer value greater than or
     * equal to 100, and false for any integer less than 100.
     * 
     * @param array Array of integers
     * @return 
     */
    public boolean[] isGreaterThan(int[] array) {
        boolean[] isGreater = new boolean[array.length];
        
        for (int i = 0; i < array.length; i++) {
            if (array[i] >= 100) {
                isGreater[i] = true;
            }
        }
        
        return isGreater;
    }
    
}
