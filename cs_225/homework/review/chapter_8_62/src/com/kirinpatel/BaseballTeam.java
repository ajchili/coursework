/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

/**
 * This class is a container for information pertaining to a baseball team,
 * it is used to store and manipulate team statistics.
 * 
 * @author Kirin Patel
 * @version 1.0
 */
public class BaseballTeam {
    
    private int numberOfPlayers;
    private int[] numberOfHits;
    private int[]  numberOfAtBats;
    
    /**
     * Secondary constructor for the BaseballTeam class, this will set the
     * number of players on the team while setting up the arrays pertaining to
     * the number of hits and number of at bats.
     * 
     * @param numberOfPlayers Number of players on the Baseball Team
     */
    public BaseballTeam(int numberOfPlayers) {
        setNumberOfPlayers(numberOfPlayers);
        
        numberOfHits = new int[this.numberOfPlayers];
        numberOfAtBats = new int[this.numberOfPlayers];
    }
    
    /**
     * Main constructor for the BaseballTeam class, this will set all of the
     * statistics for the Baseball Team, the number of players, each player's
     * number of hits and number of at bats.
     * 
     * @param numberOfPlayers Number of players on the Baseball Team
     * @param numberOfHits Array of hits made by each player on the Baseball
     * Team
     * @param numberOfAtBats Array of at bats by each player on the Baseball
     * Team
     */
    public BaseballTeam(int numberOfPlayers, int[] numberOfHits, int[] numberOfAtBats) {
        setNumberOfPlayers(numberOfPlayers);
        setNumberOfHits(numberOfHits);
        setNumberOfAtBats(numberOfAtBats);
    }
    
    /**
     * Provides all of the statistics of the Baseball Team in string format.
     * 
     * @return A printable version of BaseballTeam object
     */
    public String toString() {
        String returnValue = "Number of players: " + numberOfPlayers + "\nNumber of hits:     ";
        for (int i = 0; i < numberOfHits.length; i++) {
            returnValue += numberOfHits[i] + "  ";
        }
        returnValue += "\nNumber of at bats:  ";
        for (int i = 0; i < numberOfAtBats.length; i++) {
            returnValue += numberOfAtBats[i] + "  ";
        }
        returnValue += "\nBatting averages:  ";
        for (double battingAverage: getBattingAverage()) {
            returnValue += battingAverage + "  ";
        }
        returnValue += "\nTotal number of hits: " + getTotalNumberOfHits();
        returnValue += "\nTotal number of players with a .300 batting average or higher: " + getNumberOfGoodPlayers();
        return returnValue;
    }
    
    /**
     * Checks to see if the input is equal to this Baseball Team.
     * 
     * @param o Object to be tested
     * @return Boolean value of if inputed object is equal to this object
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BaseballTeam)) {
            return false;
        } else {
            BaseballTeam objBT = (BaseballTeam) o;
            if (numberOfPlayers != objBT.getNumberOfPlayers()) {
                return false;
            }
            
            if (numberOfHits.length != objBT.getNumberOfHits().length) {
                return false;
            }
            
            if (numberOfAtBats.length != objBT.getNumberOfAtBats().length) {
                return false;
            }
            
            if (getTotalNumberOfHits() != objBT.getTotalNumberOfHits()) {
                return false;
            }
            
            if (getNumberOfGoodPlayers() != objBT.getNumberOfGoodPlayers()) {
                return false;
            }
            
            return true;
        }
    }
    
    /**
     * This method takes the given input, checks for errors and then sets the
     * number of players on the Baseball Team.
     * 
     * @param numberOfPlayers Number of players on the Baseball Team
     */
    public void setNumberOfPlayers(int numberOfPlayers) {
        if (numberOfPlayers > 0) {
            this.numberOfPlayers = numberOfPlayers;
        } else {
            // Error Handling
            this.numberOfPlayers = 1;
        }
    }
    
    /**
     * This method takes the given input, checks for errors and then sets the
     * array for number of hits to the input.
     * 
     * @param numberOfHits Array of hits made by each player on the Baseball
     * Team
     */
    public void setNumberOfHits(int[] numberOfHits) {
        if (numberOfHits.length == numberOfPlayers) {
            this.numberOfHits = numberOfHits;
        } else {
            // Error Handling
            numberOfHits = new int[numberOfPlayers];
            
            for (int i = 0; i < numberOfPlayers; i++) {
                if (i <= numberOfHits.length) {
                    this.numberOfHits[i] = numberOfHits[i];
                } else {
                    this.numberOfHits[i] = 0;
                }
            }
        }
    }
    /**
     * This method takes the given input, checks for errors and then sets the
     * array for number of at bats to the input.
     * 
     * @param numberOfAtBats Array of at bats by each player on the Baseball
     * Team
     */
    public void setNumberOfAtBats(int[] numberOfAtBats) {
        if (numberOfAtBats.length == numberOfPlayers) {
            this.numberOfAtBats = numberOfAtBats;
        } else {
            // Error Handling
            numberOfAtBats = new int[numberOfPlayers];
            
            for (int i = 0; i < numberOfPlayers; i++) {
                if (i <= numberOfAtBats.length) {
                    this.numberOfAtBats[i] = numberOfAtBats[i];
                } else {
                    this.numberOfAtBats[i] = 0;
                }
            }
        }
    }
    
    /**
     * This method will take the given player and number of hits for that player
     * and check for any errors before setting that player's number of hits.
     * 
     * @param player The player who's statistic will be changed
     * @param numberOfHits How many hits the player made
     */
    public void setPlayerNumberOfHits(int player, int numberOfHits) {
        if (numberOfPlayers > player && player > 0) {
            this.numberOfHits[player] = numberOfHits;
        } else {
            // Error handling
            System.out.println("ERROR: ARRAY INDEX OUT OF BOUNDS");
        }
    }
    
    /**
     * This method will take the given player and number of at bats for that
     * player and check for any errors before setting that player's number of at
     * bats.
     * 
     * @param player The player who's statistic will be changed
     * @param numberOfAtBats How many at bats the player has
     */
    public void setPlayerNumberOfAtBats(int player, int numberOfAtBats) {
        if (numberOfPlayers > player && player > 0) {
            this.numberOfAtBats[player] = numberOfAtBats;
        } else {
            // Error handling
            System.out.println("ERROR: ARRAY INDEX OUT OF BOUNDS");
        }
    }
    
    /**
     * This method will return the number of players on the Baseball Team.
     * 
     * @return Number of players on the Baseball Team
     */
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }
    
    /**
     * This method will return the BaseBall Team's number of hits.
     * 
     * @return Array of hits made by each player on the Baseball
     * Team
     */
    public int[] getNumberOfHits() {
        return numberOfHits;
    }
    
    /**
     * This method will return the BaseBall Team's number of at bats.
     * 
     * @return Array of at bats by each player on the Baseball
     * Team
     */
    public int[] getNumberOfAtBats() {
        return numberOfAtBats;
    }
    
    /**
     * This method will return the number of hits made by a specified player.
     * 
     * @param player Player who's statistic will be checked
     * @return Number of hits made by the player
     */
    public int getPlayerNumberOfHits(int player) {
        if (numberOfPlayers > player && player > 0) {
            return numberOfHits[player];
        } else {
            // Error handling
            return -1;
        }
    }
    
    /**
     * This method will return the number of at bats made by a specified player.
     * @param player Player who's statistic will be checked
     * @return Number of at bats made by the player
     */
    public int getPlayerNumberOfAtBats(int player) {
        if (numberOfPlayers > player && player > 0) {
            return numberOfAtBats[player];
        } else {
            // Error handling
            return -1;
        }
    }
    
    /**
     * This method will return the total number of hits made by the Baseball
     * Team.
     * 
     * @return Total number of hits made by the Baseball Team
     */
    public int getTotalNumberOfHits() {
        int hits = 0;
        
        for (int hit : numberOfHits) {
            hits += hit;
        }
        
        return hits;
    }
    
    /**
     * This method will return the number of players who have a .300 batting
     * average or better.
     * 
     * @return The number of players who have a .300 batting average or better
     */
    public int getNumberOfGoodPlayers() {
        int numberOfGoodPlayers = 0;
        
        for (int i = 0; i < numberOfPlayers; i++) {
            if (numberOfHits[i] != 0 && numberOfAtBats[i] != 0) {
                double hits = numberOfHits[i];
                double atBats = numberOfAtBats[i];
                if (hits / atBats >= 0.300) {
                    numberOfGoodPlayers++;
                }
            }
        }
        
        return numberOfGoodPlayers;
    }
    
    /**
     * This will return an array that is sorted in ascending order of number of
     * hits.
     * 
     * @return A sorted array of each player's number of hits
     */
    public int[] getSortedHits() {
        boolean isRunning;
        int iteration = 0;
        int hits;
        int[] sortedNumberOfHits = this.numberOfHits;
        
        do {
            isRunning = false;
            iteration++;
            for (int i = 0; i < sortedNumberOfHits.length - iteration; i++) {
                if (sortedNumberOfHits[i] > sortedNumberOfHits[i + 1]) {
                    hits = sortedNumberOfHits[i];
                    sortedNumberOfHits[i] = sortedNumberOfHits[i + 1];
                    sortedNumberOfHits[i + 1] = hits;
                    isRunning = true;
                }
            }
        } while(isRunning);
        
        return sortedNumberOfHits;
    }
    
    /**
     * This will return an array of batting averages of the Baseball Team.
     * 
     * @return Array of batting averages
     */
    public double[] getBattingAverage() {
        double[] battingAverage = new double[numberOfPlayers];
        
        for (int i = 0; i < numberOfPlayers; i++) {
            if (numberOfHits[i] != 0 && numberOfAtBats[i] != 0) {
                double hits = numberOfHits[i];
                double atBats = numberOfAtBats[i];
                battingAverage[i] = hits / atBats;
            } else {
                // Error handling
                battingAverage[i] = 0.000;
            }
        }
        
        return battingAverage;
    }
}
