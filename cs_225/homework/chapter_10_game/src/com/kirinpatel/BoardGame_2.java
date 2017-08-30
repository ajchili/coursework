/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

/**
 * This class is a container for information pertaining to board games.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see com.kirinpatel.Game
 */
public class BoardGame_2 extends Game {
    
    private int minPlayers;
    private int maxPlayers;
    private boolean isTimed;
    
    /**
     * Main constructor that will set the minimum and maximum number of players
     * and if a game is time.
     * 
     * @param minPlayers Minimum number of players
     * @param maxPlayers Maximum number of players
     * @param isTimed Is the game timed
     */
    public BoardGame_2(int minPlayers, int maxPlayers, boolean isTimed) {
        super("Board Game");
        setMaxNumberOfPlayers(maxPlayers);
        setMinNumberOfPlayers(minPlayers);
        setIsTimed(isTimed);
    }
    
    /**
     * Provides BoardGame_2 in string format.
     * 
     * @return Returns printable version of BoardGame_2
     */
    @Override
    public String toString() {
        String returnValue = getDescription();
        returnValue += "\nMinimum number of Players: " + getMinPlayers();
        returnValue += "\nMaximum number of Players: " + getMaxPlayers();
        returnValue += "\nIs game timed: " + isTimed();
        return returnValue;
    }
    
    /**
     * Provides minimum number of players.
     * 
     * @return Returns the minimum number of players
     */
    public int getMinPlayers() {
        return minPlayers;
    }
    
    /**
     * Provides maximum number of players.
     * 
     * @return Returns the maximum number of players
     */
    public int getMaxPlayers() {
        return maxPlayers;
    }
    
    /**
     * Provides if a game is timed.
     * 
     * @return Returns if a game is timed
     */
    public boolean isTimed() {
        return isTimed;
    }
    
    /**
     * Sets the minimum number of players.
     * 
     * @param minPlayers Minimum number of players
     */
    public void setMinNumberOfPlayers(int minPlayers) {
        if (minPlayers < maxPlayers && minPlayers > 0) {
            this.minPlayers = minPlayers;
        } else if (minPlayers > maxPlayers) {
            // Error Handling
            System.out.println("ERROR: MINIMUM NUMBER OF PLAYERS NEEDS TO BE LESS THAN " + getMaxPlayers());
            this.minPlayers = -1;
        } else if (minPlayers <= 0) {
            // Error Handling
            System.out.println("ERROR: MINIMUM NUMBER OF PLAYERS NEEDS TO BE GREATER THAN 0");
            this.minPlayers = -1;
        } else {
            // Error Handling
            System.out.println("ERROR: MINIMUM NUMBER OF PLAYERS IS INVALID");
            this.minPlayers = -1;
        }
    }
    
    /**
     * Sets the maximum number of players.
     * 
     * @param maxPlayers Maximum number of players
     */
    public void setMaxNumberOfPlayers(int maxPlayers) {
        if (maxPlayers > minPlayers) {
            this.maxPlayers = maxPlayers;
        } else {
            // Error Handling
            System.out.println("ERROR: MAXIMUM NUMBER OF PLAYERS NEEDS TO BE GREATER THAN " + getMinPlayers());
            this.maxPlayers = -1;
        }
    }
    
    /**
     * Sets if a game is timed.
     * 
     * @param isTimed If a game is timed
     */
    public void setIsTimed(boolean isTimed) {
        this.isTimed = isTimed;
    }
}
