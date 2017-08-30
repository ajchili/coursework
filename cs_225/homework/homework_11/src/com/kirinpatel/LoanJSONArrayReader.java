/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.io.*;
import java.util.logging.*;
import javax.json.*;

/**
 * This class reads a JSON object array from a file.
 *
 * @author Kirin Patel
 * @version 1.0
 */
public class LoanJSONArrayReader {
    
    /**
     * Main method.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        try {
            JsonReader jsonReader = Json.createReader(new FileInputStream("loan.txt"));
            JsonObject jsonObject = jsonReader.readObject();
            
            jsonReader.close();
            
            JsonArray jsonArray = jsonObject.getJsonArray("loans");
            for (JsonValue value : jsonArray) {
                JsonObject object = (JsonObject) value;
                
                Loan loan = new Loan(Double.parseDouble("" + object.get("annual_rate")), object.getInt("years"), Double.parseDouble("" + object.get("amount")));
                System.out.println(loan + " --- Monthly Payment: " + object.get("monthly_payment") + " --- Total Payment: " + object.get("total_payment"));
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoanJSONArrayReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
