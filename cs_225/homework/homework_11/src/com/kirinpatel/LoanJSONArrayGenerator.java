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
import javax.json.stream.*;

/**
 * This class generates a JSON stream and outputs it to a local file.
 *
 * @author Kirin Patel
 * @version 1.0
 */
public class LoanJSONArrayGenerator {
    
    /**
     * Main method.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        try {
            JsonGenerator jsonGenerator = Json.createGenerator(new FileOutputStream("loan_stream.txt"));
            Random random = new Random();
            
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStartArray("loans");
            
            for (int i = 0; i < 5; i++) {
                Loan loan = new Loan(Double.parseDouble(random.nextInt(25) + "." + random.nextInt(100)), (random.nextInt(25) + 1), Double.parseDouble(random.nextInt(10000) + "." + random.nextInt(100)));
                
                jsonGenerator.writeStartObject();
                jsonGenerator.write("annual_rate", loan.getAnnualInterestRate());
                jsonGenerator.write("years", loan.getNumberOfYears());
                jsonGenerator.write("amount", loan.getLoanAmount());
                jsonGenerator.write("monthly_payment", loan.getMonthlyPayment());
                jsonGenerator.write("total_payment", loan.getTotalPayment());
                jsonGenerator.writeEnd();
            }
            
            jsonGenerator.writeEnd();
            jsonGenerator.writeEnd();
            jsonGenerator.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoanJSONArrayGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
