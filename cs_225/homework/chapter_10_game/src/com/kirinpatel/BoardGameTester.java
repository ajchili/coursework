/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.util.Scanner;

/**
 * This class is a tester for the BoardGame class.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see com.kirinpatel.BoardGame
 */
public class BoardGameTester {
    
    /**
     * Tests all of the methods of the BoardGame class.
     * 
     * @param args Main arguments
     */
    public static void main(String[] args) {
        // Initialize variables
        Scanner s = new Scanner(System.in);
        BoardGame boardGame = new BoardGame(1, false);
        
        // Get input
        do {
            System.out.print("Please enter the number of players: ");
            while (!s.hasNextInt()) {
                System.out.print("Please enter the number of players: ");
                s.nextInt();
            }
            boardGame.setNumberOfPlayers(s.nextInt());
        } while(boardGame.getNumberOfPlayers() < 0);
        
        System.out.print("Please enter if a game is tieable (true/false): ");
        boardGame.setIsAbleToTie(s.nextBoolean());
        
        // Display game information
        s.close();
        System.out.println("\n" + boardGame.toString());
    }
    
}
