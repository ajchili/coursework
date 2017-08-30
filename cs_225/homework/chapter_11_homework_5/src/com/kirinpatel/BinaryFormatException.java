/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

/**
 * This exception is thrown when a string value is not in binary format.
 *
 * @author Kirin Patel
 * @version 1.0
 */
public class BinaryFormatException extends NumberFormatException {
    
    /**
     * Main constructor for BinaryFormatException
     * 
     * @param message Message
     */
    public BinaryFormatException(String message) {
        super(message);
    }
}
