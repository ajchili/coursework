package com.kirinpatel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;

/**
 * Taken from http://www.cs.armstrong.edu/liang/intro9e/html/TestObjectOutputStream.html
 * 
 * @author Liang
 */

public class TestObjectOutputStream {
    
    /**
     * Main method that will write data and objects to a file.
     * 
     * @param args Main arguments
     * @throws IOException Will be thrown if the file is unable to be written to
     */
    public static void main(String[] args) throws IOException {
        // Create an output stream for file object.dat
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("object.dat"));

        // Write a string, double value, and object to the file
        output.writeUTF("John");
        output.writeDouble(85.5);
        output.writeObject(new java.util.Date());

        // Close output stream
        output.close();
    }
}