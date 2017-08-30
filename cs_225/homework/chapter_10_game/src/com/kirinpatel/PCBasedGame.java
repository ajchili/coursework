/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

/**
 * This class is a container for information pertaining to PC-based games.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see com.kirinpatel.Game
 */
public class PCBasedGame extends Game {
    
    private int minimumRam;
    private int minimumStorage;
    private double minimumGHz;
    
    /**
     * Main constructor that will set the minimum required RAM, storage, and CPU
     * GHz for a game.
     * 
     * @param minimumRam Minimum required RAM in megabytes
     * @param minimumStorage Minimum required storage space in megabytes
     * @param minimumGHz Minimum required CPU GHz
     */
    public PCBasedGame(int minimumRam, int minimumStorage, double minimumGHz) {
        super("PC Based Game");
        setMinimumRam(minimumRam);
        setMinimumStorage(minimumStorage);
        setMinimumGHz(minimumGHz);
    }
    
    /**
     * Provides PCBasedGame in string format.
     * 
     * @return Returns printable version of PCBasedGame
     */
    @Override
    public String toString() {
        String returnValue = getDescription();
        returnValue += "\nMinimum required RAM: " + getMinimumRam();
        returnValue += "\nMinimum required Storage: " + getMinimumStorage();
        returnValue += "\nMinimum required GHz CPU: " + getMinimumGHz();
        return returnValue;
    }
    
    /**
     * This method will return the minimum required RAM in megabytes
     * 
     * @return Returns minimum required RAM
     */
    public int getMinimumRam() {
        return minimumRam;
    }
    
    /**
     * This method will return the minimum required storage space in megabytes
     * 
     * @return Returns minimum required storage space
     */
    public int getMinimumStorage() {
        return minimumStorage;
    }
    
    /**
     * This method will return the minimum required CPU frequency in GHz
     * 
     * @return Returns minimum CPU GHz
     */
    public double getMinimumGHz() {
        return minimumGHz;
    }
    
    /**
     * Sets the minimum required RAM
     * 
     * @param minimumRam Minimum required RAM
     */
    public void setMinimumRam(int minimumRam) {
        if (minimumRam > 0) {
            this.minimumRam = minimumRam;
        } else {
            // Error handling
            System.out.println("ERROR: MEMORY REQUIRED HAS TO BE GREATER THAN 0");
            this.minimumRam = -1;
        }
    }
    
    /**
     * Sets the minimum required storage
     * 
     * @param minimumStorage Minimum required storage
     */
    public void setMinimumStorage(int minimumStorage) {
        if (minimumStorage > 0) {
            this.minimumStorage = minimumStorage;
        } else {
            // Error handling
            System.out.println("ERROR: STORAGE REQUIRED HAS TO BE GREATER THAN 0");
            this.minimumStorage = -1;
        }
    }
    
    /**
     * Sets the minimum required frequency of a CPU
     * 
     * @param minimumGHz Minimum required frequency
     */
    public void setMinimumGHz(double minimumGHz) {
        if (minimumGHz > 0.0) {
            this.minimumGHz = minimumGHz;
        } else {
            // Error handling
            System.out.println("ERROR: GHz REQUIRED HAS TO BE GREATER THAN 0");
            this.minimumGHz = -1.0;
        }
    }
    
}
