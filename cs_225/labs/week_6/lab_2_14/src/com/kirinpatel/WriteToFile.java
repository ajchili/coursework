/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.io.*;
import java.util.Scanner;

/**
 * Takes in doubles from user and then writes to a file.
 * 
 * @author Kirin Patel
 * @version 1.0
 */
public class WriteToFile {
 
    /**
     * Main method that will write provide doubles to a file.
     * 
     * @param args Main arguments
     */
    public static void main(String[] args) {
        // Initialize Variables
        Scanner s = new Scanner(System.in);
        DataOutputStream out = null;
        try {
            out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File("temp.dat"))));
            
            // Get info
            double d = -1.0;
            do {
                System.out.print("Please enter a double: ");
                d = s.nextDouble();
                if (d != -1.0)
                    out.writeDouble(d);
            } while (d != -1.0);
            out.flush();
        } catch (FileNotFoundException ex) {
            // Error handling
            System.out.println("File not created.");
        } catch (IOException ex) {
            // Error handling
            System.out.println("Error writing to file.");
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                // Error handling
                System.out.println("File not closed.");
            }
        }
    }
}
