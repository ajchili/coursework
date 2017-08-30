/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

/**
 * Exception that will be thrown if index is not within the bounds of an array.
 * 
 * @author Kirin Patel
 * @version 1.0
 */
public class ArrayIndexOutOfBounds extends ArrayIndexOutOfBoundsException {
    
    /**
     * Main constructor for ArrayIndexOutOfBounds
     * 
     * @param message Message
     */
    public ArrayIndexOutOfBounds(String message) {
        super(message);
    }
}
