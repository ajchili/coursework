/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.io.*;
import java.util.Random;
import java.util.logging.*;
import javax.json.*;

/**
 * This class writes a JSON object array to a file.
 *
 * @author Kirin Patel
 * @version 1.0
 */
public class LoanJSONArrayWriter {
    
    /**
     * Main method.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        JsonObjectBuilder loanFileBuilder = Json.createObjectBuilder();
	JsonArrayBuilder loanArray = Json.createArrayBuilder();
        
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            Loan loan = new Loan(Double.parseDouble(random.nextInt(25) + "." + random.nextInt(100)), (random.nextInt(25) + 1), Double.parseDouble(random.nextInt(10000) + "." + random.nextInt(100)));
            JsonObjectBuilder loanBuilder = Json.createObjectBuilder();
            
            loanBuilder.add("annual_rate", loan.getAnnualInterestRate());
            loanBuilder.add("years", loan.getNumberOfYears());
            loanBuilder.add("amount", loan.getLoanAmount());
            loanBuilder.add("monthly_payment", loan.getMonthlyPayment());
            loanBuilder.add("total_payment", loan.getTotalPayment());
            
            loanArray.add(loanBuilder.build());
        }
        
        loanFileBuilder.add("loans", loanArray.build());
        try {
            JsonWriter jsonWriter = Json.createWriter(new FileOutputStream("loan.txt"));

            jsonWriter.writeObject(loanFileBuilder.build());
            jsonWriter.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoanJSONArrayWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
