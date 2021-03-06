/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.util.concurrent.*;
import javax.swing.*;

/**
 *
 * @author Kirin Patel
 * @version 1.0
 */
public class PoolServer extends JFrame {
    
    private static JTextArea textArea;
    
    public PoolServer(String title) {
        super(title);
        
        setSize(300, 200);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        textArea = new JTextArea();
        add(textArea, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        
        setVisible(true);
        
        ServerSocket service = null;
        ExecutorService executor = Executors.newFixedThreadPool(10);
        try {
            service = new ServerSocket(8000);
            
            Socket socket;
            
            while(true) {
                socket = service.accept();

                executor.execute(new ServerTask(socket));
            }
        } catch (IOException ex) {
            // Error handling
            textArea.append("Error creating server: " + ex.getMessage());
            System.exit(0);
        } finally {
            try {
                service.close();
            } catch (IOException ex) {
                // Error handling
                textArea.append("Error closing server: " + ex.getMessage());
                System.exit(0);
            }
        }
    }
    
    /**
     * Main method.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        new PoolServer("Pool Server GUI");
    }
    
    class ServerTask implements Runnable {
        
        Socket socket;
        
        public ServerTask(Socket socket) {
            this.socket = socket;
        }
        
        @Override
        public void run() {
            try {
                textArea.append("Client connected to server. " + socket.getInetAddress() + "\n");

                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                
                for (int i = 0; i < 1000; i++) {
                    double first = input.readDouble();
                    double second = input.readDouble();
                    double avg = (first + second) / 2;
                    textArea.append("Getting the average of " + first + " and " + second + "\n");
                    output.writeUTF("Getting the average of " + first + " and " + second);
                    textArea.append("Average: " + avg + "\n");
                    output.writeDouble(avg);
                }
            } catch (IOException ex) {
                // Error handling
                textArea.append("Error creating server: " + ex.getMessage());
                System.exit(0);
            }
        }
    }
}
