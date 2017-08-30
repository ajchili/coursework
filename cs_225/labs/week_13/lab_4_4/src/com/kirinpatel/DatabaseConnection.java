/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.sql.*;

/**
 *
 * @author Kirin Patel
 * @version 1.0
 */
public class DatabaseConnection {
    
    /**
     * Main method.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException ex) {
            // Error handling
            System.out.println("ERROR: Driver class not found.");
        }
        
        Connection connection = null;
        
        try {
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
        } catch (SQLException ex) {
            // Error handling
            System.out.println("ERROR: Unable to connect to database. - (" + ex.getMessage() + ")");
        }
        
        int count = 0;
        
        try {
            Statement statement = connection.createStatement();
            // count = statement.executeUpdate("insert into DISCOUNT_CODE(DISCOUNT_CODE, RATE) values('A', 1.00)");
            // count = statement.executeUpdate("update DISCOUNT_CODE set RATE=0.02 where DISCOUNT_CODE='X'");
        } catch (SQLException ex) {
            // Error handling
            System.out.println("ERROR: Unable to insert/updata data. - (" + ex.getMessage() + ")");
        }
        
        System.out.println("Count is " + count);
        
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from DISCOUNT_CODE");
            
            System.out.println("CODE : RATE" );
            while(result.next()) {
                System.out.println("  " + result.getString("DISCOUNT_CODE") + "  : " + result.getDouble("RATE"));
            }
        } catch (SQLException ex) {
            // Error handling
            System.out.println("ERROR: Unable to insert/updata data. - (" + ex.getMessage() + ")");
        }
    }
}
