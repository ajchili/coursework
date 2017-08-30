/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.util.Scanner;

/**
 * This class is a tester for the SportsGame class.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see com.kirinpatel.SportsGame
 */
public class SportsGameTester {
    
    /**
     * Tests all of the methods of the SportsGame class.
     * 
     * @param args Main arguments
     */
    public static void main(String[] args) {
        // Initialize variables
        Scanner s = new Scanner(System.in);
        SportsGame sportsGame = new SportsGame(false, false);
        
        System.out.print("Please enter if a game is a team game (true/false): ");
        sportsGame.setIsAbleToTie(s.nextBoolean());
        
        System.out.print("Please enter if a game is tieable (true/false): ");
        sportsGame.setIsAbleToTie(s.nextBoolean());
        
        // Display game information
        System.out.println("\n" + sportsGame.toString());
    }
}
