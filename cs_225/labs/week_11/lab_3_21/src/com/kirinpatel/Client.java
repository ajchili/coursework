/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.io.*;
import java.net.*;
import java.util.Random;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Kirin Patel
 * @version 1.0
 */
public class Client extends JFrame {
    
    public Client(String title) {
        super(title);
        
        setSize(300, 200);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JTextArea textArea = new JTextArea();
        add(textArea, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        
        setVisible(true);
        
        try {
            Socket service = new Socket("localhost", 8000);
        
            textArea.append("Conneted to server. " + service.getInetAddress());
            
            DataInputStream input = new DataInputStream(service.getInputStream());
            DataOutputStream output = new DataOutputStream(service.getOutputStream());
            
            Random random = new Random();
            output.writeDouble(random.nextInt(1000) + random.nextDouble());
            output.writeDouble(random.nextInt(1000) + random.nextDouble());
            
            textArea.append("\n" + input.readUTF());
            textArea.append("\nCalculated Average: " + input.readDouble());
        } catch (IOException ex) {
            // Error handling
            textArea.append("Error creating client: " + ex.getMessage());
            System.exit(0);
        }
    }
    /**
     * Main method.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        new Client("Client GUI");
    }
}
