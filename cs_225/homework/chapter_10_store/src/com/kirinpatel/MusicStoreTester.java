/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.util.Scanner;

/**
 * This class is a tester for the MusicStore class.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see com.kirinpatel.MusicStore
 */
public class MusicStoreTester {
    
    /**
     * Tests all of the methods of the MusicStore class.
     * 
     * @param args Main arguments
     */
    public static void main(String[] args) {
        // Initialize variables
        Scanner s = new Scanner(System.in);
        MusicStore musicStore = new MusicStore(1, " ");
        
        // Get input
        do {
            System.out.print("Please enter the number of titles: ");
            while(!s.hasNextInt()) {
                System.out.print("Please enter the number of titles: ");
                s.next();
            }
            musicStore.setTitles(s.nextInt());
        } while(musicStore.getTitles() < 0);
        
        do {
            System.out.print("Please enter the store address: ");
            while(!s.hasNextLine()) {
                System.out.print("Please enter the store address: ");
                s.next();
            }
            musicStore.setAddress(s.nextLine());
        } while(musicStore.getAddress().length() <= 0);
        
        // Display store information
        s.close();
        System.out.println("\n" + musicStore.toString());
    }
}
