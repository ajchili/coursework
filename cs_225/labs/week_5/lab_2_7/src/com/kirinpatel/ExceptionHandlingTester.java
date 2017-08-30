/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import com.kirinpatel.exception.LessTenException;

/**
 * This class is a tester for exceptions.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see com.kirinpatel.exception.LessTenException
 */
public class ExceptionHandlingTester {
    
    /**
     * Tests exceptions.
     * 
     * @param args Main arguments
     */
    public static void main(String[] args) {
        try {
            throwException();
        } catch (LessTenException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println("There was an exception -- " + ex.getMessage());
        }
    }
    
    /**
     * Throws an exception when called.
     * 
     * @throws LessTenException Value less than 10
     */
    public static void throwException() throws LessTenException {
        int i = 5;
        
        if (i < 10) {
            throw new LessTenException("I is less than 5");
        }
    }
}
