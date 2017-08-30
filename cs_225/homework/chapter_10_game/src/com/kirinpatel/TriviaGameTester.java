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
 * @see com.kirinpatel.TriviaGame
 */
public class TriviaGameTester {
    
    /**
     * Tests all of the methods of the SportsGame class.
     * 
     * @param args Main arguments
     */
    public static void main(String[] args) {
        // Initialize variables
        Scanner s = new Scanner(System.in);
        TriviaGame triviaGame = new TriviaGame(1, 1);
        
        // Get user input
        do {
            System.out.print("Please enter the amount of prize money: ");
            while(!s.hasNextInt()) {
                System.out.print("Please enter the amount of prize money: ");
                s.next();
            }
            triviaGame.setPrizeMoney(s.nextInt());
        } while(triviaGame.getPrizeMoney() < 0);
        
        do {
            System.out.print("Please enter the number of questions: ");
            while(!s.hasNextInt()) {
                System.out.print("Please enter the number of questions: ");
                s.next();
            }
            triviaGame.setNumberOfQuestions(s.nextInt());
        } while(triviaGame.getNumberOfQuestions() < 0);
        
        // Display game information
        System.out.println("\n" + triviaGame.toString());
    }
}
