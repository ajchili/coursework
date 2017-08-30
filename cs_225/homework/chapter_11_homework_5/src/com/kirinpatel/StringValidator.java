/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

/**
 * This class pertains all the information pertaining to the StringValidator
 * class.
 * 
 * @author Kirin Patel
 * @version 1.0
 */
public class StringValidator {
    
    private String string;
    private char delimiter;
    
    /**
     * Main constructor that will set the string and delimiter for the
     * StringValidator
     * 
     * @param string String of values to be validated
     * @param delimiter Delimiter
     * @throws RuntimeException 
     */
    public StringValidator(String string, char delimiter) throws RuntimeException {
        setString(string);
        setDelimiter(delimiter);
    }
    
    /**
     * Provides the string of values.
     * 
     * @return Returns string of values
     */
    public String getString() {
        return string;
    }
    
    /**
     * Provides the delimiter.
     * 
     * @return Returns delimiter
     */
    public char getDelimiter() {
        return delimiter;
    }
    
    /**
     * Check to see if string contains a certain variable type.
     * 
     * @param type Variable type
     * @return If string contains a certain variable type
     * @throws RuntimeException 
     */
    public boolean checkTokens(int type) throws RuntimeException {
        int length = 0;
        
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c == delimiter) {
                length++;
            }
        }
        
        String[] strings = new String[length];
        String subString = string;
        for (int i = 0; i < length; i++) {
            int indexEnd = subString.indexOf(delimiter);
            strings[i] = subString.substring(0, indexEnd);
            subString = subString.substring(indexEnd + 1);
        }
        
        switch (type) {
            case 0:
                // boolean
                for (String string : strings) {
                    string = string.toLowerCase();
                    if (!string.equals("false") && !string.equals("true")) {
                        // Does not contain boolean value
                        return false;
                    }
                }
                
                return true;
            case 1:
                // int
                for (String string : strings) {
                    for (char c : string.toCharArray()) {
                        if (!Character.isDigit(c)) {
                            // Does not contain digit value
                            return false;
                        }
                    }
                }
                
                return true;
            case 2:
                // double
                for (String string : strings) {
                    for (char c : string.toCharArray()) {
                        if (!Character.isDigit(c) && c != '.') {
                            // Does mot contain digit value
                            return false;
                        }
                    }
                }
                
                return true;
            case 3:
                // char
                for (String string : strings) {
                    if (string.length() > 1) {
                        return false;
                    } else if (string.length() == 0) {
                        // Error handling
                        throw new RuntimeException("No char provided for tester.");
                    }
                }
                
                return true;
            default:
                return false;
        }
    }
    
    /**
     * Sets string.
     * 
     * @param string String
     * @throws RuntimeException 
     */
    public void setString(String string) throws RuntimeException {
        if (string.length() > 0) {
            this.string = string;
        } else {
            // Error handling
            this.string = "";
            throw new RuntimeException("No string provided.");
        }
    }
    
    /**
     * Sets delimiter.
     * 
     * @param delimiter Delimiter
     */
    public void setDelimiter(char delimiter) {
        this.delimiter = delimiter;
    }
}
