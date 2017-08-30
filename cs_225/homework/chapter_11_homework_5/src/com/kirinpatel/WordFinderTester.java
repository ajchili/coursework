/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is a tester for the WordFinder class.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see com.kirinpatel.WordFinder
 */
public class WordFinderTester {
    
    /**
     * Tests all of the getters and setters of the WordFinder class.
     * 
     * @param args Main arguments
     */
    public static void main(String[] args) {
        // Initiate variables
        Scanner s = new Scanner(System.in);
        
        // Get information
        System.out.print("Please enter a file location: ");
        File file = new File(s.next());
        System.out.print("Please enter a word to find: ");
        String string = s.next();
        
        WordFinder wordFinder = new WordFinder(file, string);
        
        // Display information
        try {
            System.out.println("Number of iterations of the word \"" + wordFinder.getString() + "\" found in the file \"" + wordFinder.getFile() + "\": " + wordFinder.getWordCount());
        } catch (IOException ex) {
            Logger.getLogger(WordFinderTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
