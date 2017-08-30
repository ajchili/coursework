/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.io.*;

/**
 * Taken from http://www.cs.armstrong.edu/liang/intro9e/html/TestFileStream.html
 * 
 * @author Liang
 */
public class TestFileStream {
  public static void main(String[] args) throws IOException {
    // Create an output stream to the file
    FileOutputStream output = new FileOutputStream("temp.dat");

    // Output values to the file
    for (int i = 1; i <= 10; i++)
      output.write(i);

    // Close the output stream
    output.close();

    // Create an input stream for the file
    FileInputStream input = new FileInputStream("temp.dat");

    // Read values from the file
    int value;
    while ((value = input.read()) != -1)
      System.out.print(value + " ");

    // Close the output stream
    input.close();
  }
}
