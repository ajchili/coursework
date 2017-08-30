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
import java.util.logging.Level;
import java.util.logging.Logger;
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
        
        Socket service = null;
        DataInputStream input = null;
        DataOutputStream output = null;
        try {
            service = new Socket("localhost", 8000);
        
            textArea.append("Conneted to server. " + service.getInetAddress());
            
            input = new DataInputStream(service.getInputStream());
            output = new DataOutputStream(service.getOutputStream());
            
            for (int i = 0; i < 1000; i++) {
                Random random = new Random();
                output.writeDouble(random.nextInt(1000) + random.nextDouble());
                output.writeDouble(random.nextInt(1000) + random.nextDouble());

                textArea.append("\n" + input.readUTF());
                textArea.append("\nCalculated Average: " + input.readDouble());
            }
        } catch (IOException ex) {
            // Error handling
            textArea.append("Error creating client: " + ex.getMessage());
            System.exit(0);
        } finally {
            try {
                input.close();
                output.close();
                service.close();
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    /**
     * Main method.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        new Client("Client GUI - 1");
    }
}
