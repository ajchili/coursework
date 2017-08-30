/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

/**
 * This class is a tester for the BaseballTeam class.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see com.kirinpatel.BaseballTeam
 */
public class BaseballTeamTester {
    
    /**
     * Tests all of the methods of the BaseballTeam class.
     * 
     * @param args Main arguments 
     */
    public static void main(String[] args) {
        // Setup teams
        BaseballTeam baseballTeam_1 = new BaseballTeam(20);
        BaseballTeam baseballTeam_2 = new BaseballTeam(20, new int[20], new int[20]);
        
        int[] numberOfHits_1 = {15, 40, 35, 6, 100};
        int[] numberOfAtBats_1 = {100, 80, 200, 58, 350};
        BaseballTeam baseballTeam_3 = new BaseballTeam(5, numberOfHits_1, numberOfAtBats_1);
        
        int[] numberOfHits_2 = {10, 500, 800, 80, 270};
        int[] numberOfAtBats_2 = {30, 2300, 1000, 350, 350};
        BaseballTeam baseballTeam_4 = new BaseballTeam(5, numberOfHits_2, numberOfAtBats_2);
        
        int[] numberOfHits_3 = {30, 40, 20};
        int[] numberOfAtBats_3 = {1000, 1000, 1000};
        BaseballTeam baseballTeam_5 = new BaseballTeam(3, numberOfHits_3, numberOfAtBats_3);
        
        
        // Display BaseballTeam statistics
        System.out.println(baseballTeam_1.toString() + "\n");
        System.out.println(baseballTeam_2.toString() + "\n");
        System.out.println(baseballTeam_3.toString() + "\n");
        System.out.println(baseballTeam_4.toString() + "\n");
        System.out.println(baseballTeam_5.toString() + "\n");
        
        // Test equals method for BaseBallTeams
        System.out.println(baseballTeam_1.equals(baseballTeam_2));
        System.out.println(baseballTeam_1.equals(baseballTeam_5) + "\n");
        
        // Display each of the getter methods
        System.out.println(baseballTeam_1.getNumberOfPlayers());
        System.out.println(baseballTeam_1.getTotalNumberOfHits());
        System.out.println(baseballTeam_1.getNumberOfGoodPlayers() + "\n");
        
        // Change team statistics
        baseballTeam_1.setNumberOfPlayers(3);
        baseballTeam_1.setNumberOfHits(numberOfHits_3);
        baseballTeam_1.setNumberOfAtBats(numberOfAtBats_3);
        
        // Display BaseballTeam statistics
        System.out.println(baseballTeam_1.toString() + "\n");
        
        // Re-test equals method for BaseBallTeams
        System.out.println(baseballTeam_1.equals(baseballTeam_2));
        System.out.println(baseballTeam_1.equals(baseballTeam_5) + "\n");
        
        // Re-display each of the getter methods
        System.out.println(baseballTeam_1.getNumberOfPlayers());
        System.out.println(baseballTeam_1.getTotalNumberOfHits());
        System.out.println(baseballTeam_1.getNumberOfGoodPlayers() + "\n");
        
        // Trigger error
        baseballTeam_1.setPlayerNumberOfHits(4, 90);
        
        // Display batting averages
        String outPut = "\nBatting averages:";
        for (double battingAverage : baseballTeam_1.getBattingAverage()) {
            outPut += "  " + battingAverage;
        }
        System.out.println(outPut);
    }
}
