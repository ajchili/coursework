/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

/**
 * This class is a container for information pertaining to Games.
 * 
 * @author Kirin Patel
 * @version 1.0
 */
public class Game {
    
    private String description;
    
    /**
     * Basic constructor for a Game.
     * 
     * @param description Game description
     */
    public Game(String description) {
        setDescription(description);
    }
    
    /**
     * Provides Game in string format.
     * 
     * @return Printable version of Game
     */
    @Override
    public String toString() {
        return "Description: " + description;
    }
    
    /**
     * Provides the game description.
     * 
     * @return Returns game description
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Sets the description of a game.
     * 
     * @param description Game description
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
}
