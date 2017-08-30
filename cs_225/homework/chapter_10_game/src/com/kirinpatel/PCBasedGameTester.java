/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.util.Scanner;

/**
 * This class is a tester for the PCBasedGame class.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see com.kirinpatel.PCBaseGame
 */
public class PCBasedGameTester {
    
    /**
     * Tests all of the methods of the Game class.
     * 
     * @param args Main arguments
     */
    public static void main(String[] args) {
        // Initialize variables
        Scanner s = new Scanner(System.in);
        PCBasedGame pcGame = new PCBasedGame(1, 1, 1.0);
        
        do {
            System.out.print("Please enter the minimum amount of RAM required for the game: ");
            while (!s.hasNextInt()) {
                System.out.print("Please enter the minimum amount of RAM required for the game: ");
                s.next();
            }
            pcGame.setMinimumRam(s.nextInt());
        } while(pcGame.getMinimumRam() < 0);
        
        do {
            System.out.print("Please enter the minimum amount of storage required for the game: ");
            while(!s.hasNextInt()) {
                System.out.print("Please enter the minimum amount of storage required for the game: ");
                s.next();
            }
            pcGame.setMinimumStorage(s.nextInt());
        } while(pcGame.getMinimumStorage() < 0);
        
        do {
            System.out.print("Please enter the minimum GHz for a CPU required for the game: ");
            while(!s.hasNextDouble()) {
                System.out.print("Please enter the minimum GHz for a CPU required for the game: ");
                s.next();
            }
            pcGame.setMinimumGHz(s.nextDouble());
        } while(pcGame.getMinimumGHz() < 0.0);
        
        // Display game information
        System.out.println("\n" + pcGame.toString());
    }
    
}
