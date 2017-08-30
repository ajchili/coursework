/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.io.*;
import java.util.Random;

/**
 * Test the loan method by reading and writing it to a file.
 *
 * @author Kirin Patel
 * @version 1.0
 */
public class LoanTester {
    
    /**
     * Main method.
     * 
     * @param args Main arguments
     */
    public static void main(String[] args) {
        // Initialize variables
        Loan[] loans = new Loan[5];
        Random random = new Random();
        
        // Setup information
        for (int i = 0; i < 5; i++) {
            loans[i] = new Loan(random.nextDouble(), random.nextInt(3) + random.nextInt(10 - i), 1000 + random.nextInt(25000) + random.nextDouble());
        }
        
        // Save/Load information
        loadLoans();
        saveLoans(loans);
        loadLoans();
    }
    
    /**
     * Saves the provided Loan array to a file.
     * 
     * @param loans Array of loans
     */
    private static void saveLoans(Loan[] loans) {
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("loans.dat"));
            
            for (Loan loan : loans)
                output.writeObject(loan);
        } catch (FileNotFoundException ex) {
            // Error handling
            System.out.println("Unable to create/find file.");
        } catch (IOException ex) {
            // Error handling
            System.out.println("Unable to write to file.");
        }
    }
    
    /**
     * Reads the loans that are saved to a file
     */
    private static void loadLoans() {
        double totalBorrowed = 0;
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("loans.dat"));
            
            Loan loan;
            
            while (input.available() > -1) {
                Object readObject = input.readObject();
                
                if (readObject instanceof Loan) {
                    loan = (Loan) readObject;
                
                    System.out.println(loan.getAnnualInterestRate()+ "\t" + loan.getLoanDate() + "\t" + loan.getLoanAmount());
                    totalBorrowed += loan.getLoanAmount();
                }
            }
        } catch (FileNotFoundException ex) {
            // Error handling
            System.out.println("No file found.");
        } catch (EOFException ex) {
            // Error handling
            System.out.println("End of file.");
        } catch (IOException ex) {
            // Error handling
            System.out.println("Error reading file.");
        } catch (ClassNotFoundException ex) {
            // Error handling
            System.out.println("Error class type not found.");
        }
        
        System.out.println("Total money borrowed: " + totalBorrowed);
    }
}
