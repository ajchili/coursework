/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.io.*;

/**
 * Taken from http://www.cs.armstrong.edu/liang/intro9e/html/TestObjectInputStream.html
 * 
 * @author Liang
 */

public class TestObjectInputStream {
    
    /**
     * Main method that will read from a file.
     * 
     * @param args Main arguments
     * @throws ClassNotFoundException Class that is read is not found on this
     * machine
     * @throws IOException File is unable to be read or written to
     */
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        // Create an input stream for file object.dat
        ObjectInputStream input = new ObjectInputStream(new FileInputStream("object.dat"));

        // Write a string, double value, and object to the file
        String name = input.readUTF();
        double score = input.readDouble();
        java.util.Date date = (java.util.Date)(input.readObject());
        System.out.println(name + " " + score + " " + date);

        // Close input stream
        input.close();
    }
}
