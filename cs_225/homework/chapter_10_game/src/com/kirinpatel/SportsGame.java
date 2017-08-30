/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

/**
 * This class is a container for information pertaining to sports games.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see com.kirinpatel.Game
 */
public class SportsGame extends Game {
    
    private boolean isTeamGame;
    private boolean isAbleToTie;
    
    /**
     * Main constructor that will set if it is a team game and if a game can be
     * tied.
     * 
     * @param isTeamGame Is it a team game
     * @param isAbleToTie Can the game be tied
     */
    public SportsGame(boolean isTeamGame, boolean isAbleToTie) {
        super("Sports Game");
        setIsTeamGame(isTeamGame);
        setIsAbleToTie(isAbleToTie);
    }
    /**
     * Provided SportsGame in string format.
     * 
     * @return Returns a printable version of SportsGame
     */
    @Override
    public String toString() {
        String returnValue = getDescription();
        returnValue += "\nTeam game: " + isTeamGame();
        returnValue += "\nAllow ties: " + isAbleToTie();
        return returnValue;
    }
    
    /**
     * Provides if it is a team game.
     * 
     * @return Returns if it is a team game
     */
    public boolean isTeamGame() {
        return isTeamGame;
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
     * Sets if a game is a team game.
     * 
     * @param isTeamGame Is the game a team game
     */
    public void setIsTeamGame(boolean isTeamGame) {
        this.isTeamGame = isTeamGame;
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
