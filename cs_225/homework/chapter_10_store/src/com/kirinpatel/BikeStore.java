/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

/**
 * This class is a container for information pertaining to bike store.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see com.kirinpatel.Store
 */
public class BikeStore extends Store{
    
    private int numberOfBrands;
    private boolean isSponsor;
    
    /**
     * Main constructor that will set the number of bands and if the store
     * sponsors bike clubs.
     * 
     * @param numberOfBrands Number of brands
     * @param isSponsor Sponsor bike clubs
     */
    public BikeStore(int numberOfBrands, boolean isSponsor) {
        super("Bike Store");
        setNumberOfBrands(numberOfBrands);
        setSponsored(isSponsor);
    }
    
    /**
     * Provides BikeStore in string format.
     * 
     * @return Returns printable version of BikeStore
     */
    @Override
    public String toString() {
        String returnValue = super.toString();
        returnValue += "\nNumber of brands sold: " + getNumberOfBrands();
        returnValue += "\nSponsor bike clubs: " + isSponsor();
        return returnValue;
    }
    
    /**
     * Provides number of brands.
     * 
     * @return Returns the number of bands
     */
    public int getNumberOfBrands() {
        return numberOfBrands;
    }
    
    /**
     * Provides if the store sponsors bike clubs
     * 
     * @return Returns if the store sponsors bike clubs
     */
    public boolean isSponsor() {
        return isSponsor;
    }
    
    /**
     * Sets the number of brands sold.
     * 
     * @param numberOfBrands Number of brands
     */
    public void setNumberOfBrands(int numberOfBrands) {
        if (numberOfBrands > 0) {
            this.numberOfBrands = numberOfBrands;
        } else {
            // Error handling
            System.out.println("ERROR: NUMBER OF BRANDS MUST BE GREATER THAN 0");
            this.numberOfBrands = -1;
        }
    }
    
    /**
     * Sets if the store sponsors bike clubs.
     * 
     * @param isSponsor Sponsor bike clubs 
     */
    public void setSponsored(boolean isSponsor) {
        this.isSponsor = isSponsor;
    }
    
}
