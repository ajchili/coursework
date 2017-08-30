/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.io.*;

/**
 * Taken from http://www.cs.armstrong.edu/liang/intro9e/html/TestObjectStreamForArray.html
 * 
 * @author Liang
 */
public class TestObjectStreamForArray {
  
    /**
     * Main method will write and read an array to and from a file.
     * 
     * @param args Main parameters
     * @throws ClassNotFoundException Class being read is not found on system
     * @throws IOException File cannot be read or written to.
     */
    public static void main(String[] args) throws ClassNotFoundException, IOException {
    int[] numbers = {1, 2, 3, 4, 5};
    String[] strings = {"John", "Susan", "Kim"};

    // Create an output stream for file array.dat
    ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("array.dat", true));

    // Write arrays to the object output stream
    output.writeObject(numbers);
    output.writeObject(strings);

    // Close the stream
    output.close();

    // Create an input stream for file array.dat
    ObjectInputStream input = new ObjectInputStream(new FileInputStream("array.dat"));

    int[] newNumbers = (int[])(input.readObject());
    String[] newStrings = (String[])(input.readObject());

    // Display arrays
    for (int i = 0; i < newNumbers.length; i++)
        System.out.print(newNumbers[i] + " ");
    System.out.println();

    for (int i = 0; i < newStrings.length; i++)
        System.out.print(newStrings[i] + " ");
    
    // Close the stream
    input.close();
  }
}
