/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This class contains all information pertaining to the WordFinderClass.
 * 
 * @author Kirin Patel
 * @version 1.0
 */
public class WordFinder {
    
    private File file;
    private String string;
    
    /**
     * Main constructor that will set the file to be searched for a specified
     * string.
     * 
     * @param file File path
     * @param string String to be searched for
     */
    public WordFinder(File file, String string) {
        setFile(file);
        setString(string);
    }
    
    /**
     * Provides file path.
     * 
     * @return Returns file path
     */
    public File getFile() {
        return file;
    }
    
    /**
     * Provides search string.
     * 
     * @return Returns search string
     */
    public String getString() {
        return string;
    }
    
    /**
     * Provides number of times that the word is found in the specified file.
     * 
     * @return Returns number of times that the word is found
     * @throws IOException
     */
    public int getWordCount() throws IOException {
        int count = 0;
        String stringFromFile = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
        
        for (int i = 0; i < stringFromFile.length(); i++) {
            int verifiedChars = 0;
            
            if (stringFromFile.charAt(i) == string.charAt(0)) {
                verifiedChars = 1;
                for (int j = 1; j < string.length(); j++) {
                    if (stringFromFile.charAt(i + j) != string.charAt(j)) {
                        break;
                    } else {
                        verifiedChars++;
                    }
                }
            }
            
            if (verifiedChars == string.length()) {
                if (i + string.length() < stringFromFile.length()) {
                    if (stringFromFile.charAt(i + string.length()) == ' ') {
                        count++;
                    }
                } else {
                    count++;
                }
            }
        }
        
        return count;
    }
    
    /**
     * Sets the file path.
     * 
     * @param file File path
     */
    public void setFile(File file) {
        if (file.exists()) {
            this.file = file;
        } else {
            // Error handling
            throw new RuntimeException("No file provided.");
        }
    }
    
    /**
     * Sets the search string.
     * 
     * @param string Search string
     */
    public void setString(String string) {
        if (string.length() > 0) {
            this.string = string;
        } else {
            // Error handling
            this.string = "";
            throw new RuntimeException("No string provided.");
        }
    }
}
