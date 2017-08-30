/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.io.*;

/**
 * Taken from http://www.cs.armstrong.edu/liang/intro9e/html/Copy.html
 * 
 * @author Liang
 */
public class Copy {
  /** Main method
     @param args[0] for sourcefile 
     @param args[1] for target file
   */
  public static void main(String[] args) throws IOException { 
    // Check command-line parameter usage
    if (args.length != 2) { 
      System.out.println(
        "Usage: java Copy sourceFile targetfile");
      System.exit(1);
    }

    // Check if source file exists
    File sourceFile = new File(args[0]);
    if (!sourceFile.exists()) {
       System.out.println("Source file " + args[0] 
         + " does not exist");
       System.exit(2);
    }

    // Check if target file exists
    File targetFile = new File(args[1]);
    if (targetFile.exists()) {
      System.out.println("Target file " + args[1] 
        + " already exists");
      System.exit(3);
    }

    // Create an input stream
    BufferedInputStream input = 
      new BufferedInputStream(new FileInputStream(sourceFile));

    // Create an output stream
    BufferedOutputStream output = 
      new BufferedOutputStream(new FileOutputStream(targetFile));

    // Continuously read a byte from input and write it to output
    int r, numberOfBytesCopied = 0;
    while ((r = input.read()) != -1) {
      output.write((byte)r);
      numberOfBytesCopied++;
    }

    // Close streams
    input.close();
    output.close();

    // Display the file size
    System.out.println(numberOfBytesCopied + " bytes copied");
  }
}