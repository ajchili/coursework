/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.io.*;
import java.util.Random;

/**
 * This class will generate a random lowercase letter and append it to a binary 
 * file.  If it doesn't exist, it will create the file.  Also, each time a
 * letter is written, the initial byte is updated to keep a count of how
 * many writes have occurred.
 * 
 * @author Kirin Patel
 * @version 1.0
 */
public class AddRandomLetter {
    
    /**
     * Main method.
     * 
     * @param args Main arguments
     */
    public static void main(String[] args) {
        // Write to file
        writeToFile();
        
        // Read from file
        checkContentsOfFile();
    }
    
    /**
     * Provides a randomly generated char.
     * 
     * @return Random character
     */
    private static char generateChar() {
        Random random = new Random();
        return (char) (97 + random.nextInt(25));
    }
    
    /**
     * Writes a generated char to a file.
     */
    private static void writeToFile() {
        try {
            RandomAccessFile raf = new RandomAccessFile("chars.dat", "rw");
            
            byte timesWritten;
            
            if (raf.length() != 0)
                timesWritten = (byte) (raf.readByte() + 1);
            else
                timesWritten = 1;
            
            raf.seek(0);
            raf.writeByte(timesWritten);
            raf.seek(raf.length());
            raf.writeChar(generateChar());
        } catch (IOException ex) {
            // Error hadling
            System.out.println("File not able to be read/written to.");
        }
    }
    
    /**
     * Prints out file contents.
     */
    private static void checkContentsOfFile() {
        try {
            DataInputStream input = new DataInputStream(new FileInputStream("chars.dat"));
            
            System.out.println("Times written to: " + input.readByte());
            
            do {
                System.out.print(input.readChar());
            } while(input.available() > 0);
            System.out.print("\n");
        } catch (FileNotFoundException ex) {
            // Error handling
            System.out.println("File not found.");
        } catch (IOException ex) {
            // Error handlong
            System.out.println("File not able to be read.");
        }
    }
}
