/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.io.Serializable;

/**
 * This class contains all the information pertaining to the Loan class.
 * 
 * @author Kirin Patel
 * @version 1.0
 */
public class Loan implements Serializable {
    
    private double annualInterestRate;
    private int numberOfYears;
    private double loanAmount;
    private java.util.Date loanDate; 
    
    /**
     * Default constructor that will generate a default loan.
     */
    public Loan() {
        this(2.5, 1, 1000);
    }
    
    /**
     * Construct a loan with specified annual interest rate,
     * number of years, and loan amount.
     * 
     * @param annualInterestRate
     * @param numberOfYears
     * @param loanAmount 
     */
    public Loan(double annualInterestRate, int numberOfYears, double loanAmount) {
        this.annualInterestRate = annualInterestRate;
        this.numberOfYears = numberOfYears;
        this.loanAmount = loanAmount;
        loanDate = new java.util.Date();
    }
    
    @Override
    public String toString() {
        return "Loan Amount: " + getLoanAmount() + " --- " + "Interest Rate: " + getAnnualInterestRate() + " --- " + "Time: " + getNumberOfYears();
    }
    
    /**
     * Provides the annual interest rate.
     * 
     * @return Annual interest rate
     */
    public double getAnnualInterestRate() {
        return annualInterestRate;
    }
    
    /**
     * Sets the annual interest rate.
     * 
     * @param annualInterestRate Annual interest rate
     */
    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }
    
    /**
     * Provides the number of years a loan is active for.
     * 
     * @return Number of years a loan is active for
     */
    public int getNumberOfYears() {
        return numberOfYears;
    }
    
    /**
     * Sets the number of years a loan is active for.
     * 
     * @param numberOfYears Number of years a loan is active for
     */
    public void setNumberOfYears(int numberOfYears) {
        this.numberOfYears = numberOfYears;
    }
    
    /**
     * Provides size of loan.
     * 
     * @return Size of loan
     */
    public double getLoanAmount() {
        return loanAmount;
    }
    
    /**
     * Sets the size of loan.
     * 
     * @param loanAmount Size of loan
     */
    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }
    
    /**
     * Provides the required monthly payment amount.
     * 
     * @return Monthly payment amount
     */
    public double getMonthlyPayment() {
        double monthlyInterestRate = annualInterestRate / 1200;
        double monthlyPayment = loanAmount * monthlyInterestRate / (1 - (1 / Math.pow(1 + monthlyInterestRate, numberOfYears * 12)));
        
        return monthlyPayment;
    }
    
    /**
     * Provides the total number of payments required.
     * 
     * @return Total number of payments
     */
    public double getTotalPayment() {
        double totalPayment = getMonthlyPayment() * numberOfYears * 12;
        
        return totalPayment;
    }
    
    /**
     * Provides the date of loan.
     * 
     * @return Date loan is issued 
     */
    public java.util.Date getLoanDate() {
        return loanDate;
    }
}
