/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.*;
import javax.json.stream.*;

/**
 * This class parses a JSON stream from a local file.
 *
 * @author Kirin Patel
 * @version 1.0
 */
public class LoanJSONArrayParser {
    
    /**
     * Main method.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        try {
            JsonParser jsonParser = Json.createParser(new FileInputStream("loan_stream.txt"));
            
            String keyName = null;
            while (jsonParser.hasNext()) {
                JsonParser.Event event = jsonParser.next();
                switch (event) {
                    case KEY_NAME:
                            keyName = jsonParser.getString();
                            if (keyName.equals("loans")) {
                                jsonParser.next();
                                jsonParser.next();
                                
                                for (int i = 0; i < 5; i++) {
                                    double monthlyPayment = 0;
                                    double totalPayment = 0;
                                    Loan loan = new Loan();
                                    
                                    for (int j = 0; j < 5; j++) {
                                        switch (jsonParser.next()) {
                                            case KEY_NAME:
                                                String loanKeyName = jsonParser.getString();
                                                jsonParser.next();
                                                switch (loanKeyName) {
                                                    case "annual_rate":
                                                        loan.setAnnualInterestRate(Double.parseDouble(jsonParser.getString()));
                                                        break;
                                                    case "years":
                                                        loan.setNumberOfYears(jsonParser.getInt());
                                                        break;
                                                    case "amount":
                                                        loan.setLoanAmount(Double.parseDouble(jsonParser.getString()));
                                                        break;
                                                    case "monthly_payment":
                                                        monthlyPayment = Double.parseDouble(jsonParser.getString());
                                                        break;
                                                    case "total_payment":
                                                        totalPayment = Double.parseDouble(jsonParser.getString());
                                                        break;
                                                }
                                                break;
                                        }
                                    }
                                    
                                    jsonParser.next();
                                    jsonParser.next();
                                    
                                    System.out.println(loan + " --- Monthly Payment: " + monthlyPayment + " --- Total Payment: " + totalPayment);
                                }
                            }
                            break;
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoanJSONArrayParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
