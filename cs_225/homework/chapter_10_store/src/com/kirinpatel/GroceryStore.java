/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

/**
 * This class is a container for information pertaining to grocery store.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see com.kirinpatel.Store
 */
public class GroceryStore extends Store {
    
    private double annualRevenue;
    private boolean isChain;
    
    /**
     * Main constructor that will set the annual revenue and if the store is
     * part of a chain.
     * 
     * @param annualRevenue Annual Revenue
     * @param isChain Is a part of a chain
     */
    public GroceryStore(double annualRevenue, boolean isChain) {
        super("Grocery Store");
        setAnnualRevenue(annualRevenue);
        setChain(isChain);
    }
    
    /**
     * Provides GroceryStore in string format.
     * 
     * @return Returns printable version of GroceryStore
     */
    @Override
    public String toString() {
        String returnValue = super.toString();
        returnValue += "\nAnnual reveneu: $" + getAnnualRevenue();
        returnValue += "\nChain store: " + isChain();
        returnValue += "\nAnnual tax: $" + getAnnualTaxes();
        return returnValue;
    }
    
    /**
     * Provides annual revenue.
     * 
     * @return Returns the annual revenue
     */
    public double getAnnualRevenue() {
        return annualRevenue;
    }
    
    /**
     * Provides if the store is a part of a chain.
     * 
     * @return Returns if the store is a part of a chain
     */
    public boolean isChain() {
        return isChain;
    }
    
    /**
     * Provides annual taxes.
     * 
     * @return Returns the annual tax
     */
    public double getAnnualTaxes() {
        if (isChain) {
            return ((annualRevenue * super.SALES_TAX_RATE) + (annualRevenue * .03));
        } else {
            return (annualRevenue * super.SALES_TAX_RATE);
        }
    }
    
    /**
     * Sets the annual revenue.
     * 
     * @param annualRevenue Annual revenue
     */
    public void setAnnualRevenue(double annualRevenue) {
        if (annualRevenue > 0.0) {
            this.annualRevenue = annualRevenue;
        } else {
            // Error handling
            System.out.println("ERROR: ANNUAL REVENUE MUST BE GREATER THAN 0");
            this.annualRevenue = -1.0;
        }
    }
    
    /**
     * Sets if the store is a part of a chain.
     * 
     * @param isChain Is a part of chain
     */
    public void setChain(boolean isChain) {
        this.isChain = isChain;
    }
}
