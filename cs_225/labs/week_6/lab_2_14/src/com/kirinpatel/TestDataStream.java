/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.io.*;

/**
 * Taken from http://www.cs.armstrong.edu/liang/intro9e/html/TestDataStream.html
 * 
 * @author Liang
 */
public class TestDataStream {
  public static void main(String[] args) throws IOException {
    // Create an output stream for file temp.dat
    DataOutputStream output =
      new DataOutputStream(new FileOutputStream("temp.dat"));

    // Write student test scores to the file
    output.writeUTF("John");
    output.writeDouble(85.5);
    output.writeUTF("Jim");
    output.writeDouble(185.5);
    output.writeUTF("George");
    output.writeDouble(105.25);
    output.writeBoolean(5 < 4);

    // Close output stream
    output.close();

    // Create an input stream for file temp.dat
    DataInputStream input =
      new DataInputStream(new FileInputStream("temp.dat"));

    // Read student test scores from the file
    System.out.println(input.readUTF() + " " + input.readDouble());
    System.out.println(input.readUTF() + " " + input.readDouble());
    System.out.println(input.readUTF() + " " + input.readDouble());
    System.out.println(input.readBoolean());
  }
}
