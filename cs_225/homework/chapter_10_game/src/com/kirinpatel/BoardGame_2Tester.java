/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.util.Scanner;

/**
 * This class is a tester for the BoardGame_2 class.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see com.kirinpatel.BoardGame_2
 */
public class BoardGame_2Tester {
    
    /**
     * Tests all of the methods of the BoardGame_2 class.
     * 
     * @param args Main arguments
     */
    public static void main(String[] args) {
        // Initialize variables
        Scanner s = new Scanner(System.in);
        BoardGame_2 boardGame = new BoardGame_2(1, Integer.MAX_VALUE, false);
        
        // Get input
        do {
            System.out.print("Please enter the minimum number of players: ");
            while(!s.hasNextInt()) {
                System.out.print("Please enter the minimum number of players: ");
                s.next();
            }
            boardGame.setMinNumberOfPlayers(s.nextInt());
        } while (boardGame.getMinPlayers() < 0);
        
        do {
            System.out.print("Please enter the maximum number of players: ");
            while(!s.hasNextInt()) {
                System.out.print("Please enter the maximum number of players: ");
                s.next();
            }
            boardGame.setMaxNumberOfPlayers(s.nextInt());
        } while (boardGame.getMaxPlayers() < 0);
        
        System.out.print("Please enter if a game is timed (true/false): ");
        boardGame.setIsTimed(s.nextBoolean());
        
        // Display game information
        s.close();
        System.out.println("\n" + boardGame.toString());
    }
    
}
