/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

/**
 * This class is a container for information pertaining to restaurant.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see com.kirinpatel.Store
 */
public class Restaurant extends Store {
    
    private int numberOfYearlyCustomers;
    private double averageCustomerPrice;
    
    /**
     * Main constructor that will set the number of yearly customers and the
     * average customer price.
     * 
     * @param numberOfYearlyCustomers Number of customers per year
     * @param averageCustomerPrice Average price per customer
     */
    public Restaurant(int numberOfYearlyCustomers, double averageCustomerPrice) {
        super("Restaurant");
        setNumberOfYearlyCustomers(numberOfYearlyCustomers);
        setAverageCustomerPrice(averageCustomerPrice);
    }
    
    /**
     * Provides Restaurant in string format.
     * 
     * @return Returns printable version of Restaurant
     */
    @Override
    public String toString() {
        String returnValue = super.toString();
        returnValue += "\nNumber of yearly customers: " + getNumberOfYearlyCustomers();
        returnValue += "\nAverage customer expense: " + getAverageCustomerPrice();
        returnValue += "\nAverage yearly taxes: " + getAverageYearlyTaxes();
        return returnValue;
    }
    
    /**
     * Provides number of yearly customers.
     * 
     * @return Returns the number customers per year
     */
    public int getNumberOfYearlyCustomers() {
        return numberOfYearlyCustomers;
    }
    
    /**
     * Provides average customer price.
     * 
     * @return Returns the average price a customer pays
     */
    public double getAverageCustomerPrice() {
        return averageCustomerPrice;
    }
    
    /**
     * Provides average yearly tax.
     * 
     * @return Returns the average yearly tax
     */
    public double getAverageYearlyTaxes() {
        return ((numberOfYearlyCustomers * averageCustomerPrice) * super.SALES_TAX_RATE);
    }
    
    /**
     * Sets the number of yearly customers.
     * 
     * @param numberOfYearlyCustomers Number of yearly customers
     */
    public void setNumberOfYearlyCustomers(int numberOfYearlyCustomers) {
        if (numberOfYearlyCustomers > 0) {
            this.numberOfYearlyCustomers = numberOfYearlyCustomers;
        } else {
            // Error handling
            System.out.println("ERROR: NUMBER OF YEARLY CUSTOMERS MUST BE GREATER THAN 0");
            this.numberOfYearlyCustomers = -1;
        }
    }
    
    /**
     * Sets the average price of a customer.
     * 
     * @param averageCustomerPrice Average price per customer
     */
    public void setAverageCustomerPrice(double averageCustomerPrice) {
        if (averageCustomerPrice > 0.0) {
            this.averageCustomerPrice = averageCustomerPrice;
        } else {
            // Error handling
            System.out.println("ERROR: AVERAGE PRICE MUST BE GREATER THAN 0");
            this.averageCustomerPrice = -1.0;
        }
    }
}
