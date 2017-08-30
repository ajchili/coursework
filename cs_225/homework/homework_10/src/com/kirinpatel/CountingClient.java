/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;

/**
 * This class contains all the information pertaining to the CountingClient.
 *
 * @author Kirin Patel
 * @verion 1.0
 */
public class CountingClient extends JFrame {
    
    /**
     * Main constructor that will create Client.
     * 
     * @param title Title
     */
    public CountingClient(String title) {
        super(title);
        
        setSize(300, 200);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JLabel countLabel = new JLabel("Connecting to server...");
        add(countLabel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        
        setVisible(true);
        
        Socket service = null;
        DataInputStream input = null;
        try {
            service = new Socket("localhost", 8000);
            
            input = new DataInputStream(service.getInputStream());
            countLabel.setText("You are visitor number " + input.readByte() + ".");
        } catch (IOException ex) {
            // Error handling
            countLabel.setText("Error creating client: " + ex.getMessage());
            System.exit(0);
        } finally {
            try {
                input.close();
                service.close();
            } catch (IOException ex) {
                Logger.getLogger(CountingClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Main method.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        new CountingClient("Counter Client");
    }
}
