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
public class BoardGame extends Game {
    
    private int numberOfPlayers;
    private boolean isAbleToTie;
    
    /**
     * Main constructor that will set the number of players and if a game can be
     * tied.
     * 
     * @param numberOfPlayers Number of players
     * @param isAbleToTie If game can be tied
     */
    public BoardGame(int numberOfPlayers, boolean isAbleToTie) {
        super("Board Game");
        setNumberOfPlayers(numberOfPlayers);
        setIsAbleToTie(isAbleToTie);
    }
    
    /**
     * Provides BoardGame in string format.
     * 
     * @return Returns printable version of BoardGame
     */
    @Override
    public String toString() {
        String returnValue = getDescription();
        returnValue += "\nNumber of players: " + getNumberOfPlayers();
        returnValue += "\nAllow ties: " + isAbleToTie();
        return returnValue;
    }
    
    /**
     * Provides number of players.
     * 
     * @return Returns the number of players
     */
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }
    
    
    /**
     * Provides if a game can be tied.
     * 
     * @return Returns if a game can be tied
     */
    public boolean isAbleToTie() {
        return isAbleToTie;
    }
    
    /**
     * Sets the number of players.
     * 
     * @param numberOfPlayers Number of players
     */
    public void setNumberOfPlayers(int numberOfPlayers) {
        if (numberOfPlayers > 0) {
            this.numberOfPlayers = numberOfPlayers;
        } else {
            System.out.println("ERROR: MINIMUM NUMBER OF PLAYERS NEEDS TO BE GREATER THAN 0");
            this.numberOfPlayers = -1;
        }
    }
    
    /**
     * Sets if a game can be tied.
     * 
     * @param isAbleToTie If a game can be tied
     */
    public void setIsAbleToTie(boolean isAbleToTie) {
        this.isAbleToTie = isAbleToTie;
    }
    
}
