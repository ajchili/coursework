/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel.exception;

/**
 * Exception that will be thrown if integer value is less than 10.
 * 
 * @author Kirin Patel
 * @version 1.0
 */
public class LessTenException extends Exception {
    
    /**
     * Main constructor for LessTenException
     * 
     * @param message Message
     */
    public LessTenException(String message) {
        super("LessTenException: " + message);
    }
}
