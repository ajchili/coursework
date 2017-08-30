/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.io.*;
import java.util.Scanner;

/**
 * Program that will reads lines of characters from a text file and writes each
 * line as a UTF-8 string into a binary file.
 * 
 * @author Kirin Patel
 * @version 1.0
 */
public class UTFToBinary {
    
    /**
     * Main method.
     * 
     * @param args Main Arguments
     */
    public static void main(String[] args) {
        // Initialize Variables
        Scanner s = new Scanner(System.in);
        File file = null;
        
        // Get information
        System.out.print("Please enter the location of the UTF-8 file: ");
        file = new File(s.next());
        
        while (!file.exists()) {
            System.out.print("No file found.\nPlease enter the location of the UTF-8 file: ");
            file = new File(s.next());
        }
        
        // Write to file
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream(file));
            File output = new File(file.getParent() + "/converted_text.dat");
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(output));
            
            dos.writeBytes(dis.readUTF());
            
            System.out.println("Size of original file: " + (file.getTotalSpace() - file.getFreeSpace()));
            System.out.println("Size of binary file: " + (output.getTotalSpace() - output.getFreeSpace()));
        } catch (FileNotFoundException ex) {
            // Error handling
            System.out.println("No output file.");
        } catch (EOFException ex) {
            // Error handling
            System.out.println("No more data found to write.");
        } catch (IOException ex) {
            // Error handling
            System.out.println("Error writing to file.");
        }
    }
}
