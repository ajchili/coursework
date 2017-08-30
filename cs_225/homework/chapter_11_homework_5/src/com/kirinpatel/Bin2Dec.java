/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * This class tests a conversion between binary and decimal string objects.
 * 
 * @author Kirin Patel
 * @version 1.0
 */
public class Bin2Dec {
    
    /**
     * Tests the bin2dec method.
     * 
     * @param args Main arguments
     */
    public static void main(String[] args) {
        // Initialize variables
        Scanner s = new Scanner(System.in);
        
        // Get and display data
        System.out.println("Type \'n\' to quit.");
        String text;
        do {
            System.out.print("Please enter a binary string: ");
            text = s.next();
            if (text.equals("n")) {
                break;
            }
            System.out.println(bin2dec(text));
        } while(true);
    }
    
    /**
     * Converts given string into decimal number IF given string is a binary
     * number.
     * Code taken from https://github.com/ajchili/ap_comp_sci/blob/master/src/Testing/BinaryConversion.java
     * 
     * @param binaryString Binary string
     * @return Decimal value
     */
    public static int bin2dec(String binaryString) {
        ArrayList<String> convert = new ArrayList<String>();
        for (int i = 0; i < binaryString.length(); i++) {
                if ((binaryString.subSequence(i, i + 1)).equals("1") || (binaryString.subSequence(i, i + 1)).equals("0")) {
                    convert.add((String) (binaryString.subSequence(i, i + 1)));
                } else {
                    try {
                        Integer.parseInt(binaryString);
                    } catch (NumberFormatException e) {
                        if (binaryString.contains("1") || binaryString.contains("0")) {
                            throw new BinaryFormatException("Binary string not provided.");
                        } else {
                            e.printStackTrace();
                        }
                    }
                }
        }
        Collections.reverse(convert);
        int decimal = 0;
        for (int i = 0; i < binaryString.length(); i++) {
                if (convert.get(i).equals("1")) {
                        decimal += Math.pow(2, i);
                }
        }
        
        return decimal;
    }
}
