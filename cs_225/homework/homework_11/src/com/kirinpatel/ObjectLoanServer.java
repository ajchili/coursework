/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.*;
import javax.swing.*;

/**
 * This class sends and received JSON objects from a client.
 *
 * @author Kirin Patel
 * @version 1.0
 */
public class ObjectLoanServer extends JFrame {
    
    private JTextArea textArea;
    
    /**
     * Main constructor that will create the LoanServer.
     * 
     * @param title Title
     */
    public ObjectLoanServer(String title) {
        super(title);
        
        setSize(300, 200);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(textArea);
        add(scroll);
        
        setLocationRelativeTo(null);
        
        setVisible(true);
        
        ServerSocket service = null;
        ExecutorService executor = Executors.newCachedThreadPool();
        
        try {
            service = new ServerSocket(8000);
            
            Socket socket;
            
            textArea.append("Server started...\n");
            
            while(true) {
                socket = service.accept();
                
                executor.execute(new LoanServerThread(socket));
            }
        } catch (IOException ex) {
            // Error handling
            textArea.append("Error creating server. " + ex.getMessage() + "\n");
        } finally {
            try {
                executor.shutdown();
                
                while(!executor.isTerminated()) {
                    
                }
                
                service.close();
            } catch (IOException ex) {
                // Error handling
                textArea.append("Error closing server: " + ex.getMessage() + "\n");
            }
        }
    }
    
    /**
     * Main method.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        new ObjectLoanServer("Object Loan Server");
    }
    
    /**
     * This class runs the JSON communication between client and server.
     */
    class LoanServerThread implements Runnable {
        
        private Socket socket;
        
        /**
         * Main constructor that will get the socket used to connect a client.
         * 
         * @param socket Socket
         */
        public LoanServerThread(Socket socket) {
            this.socket = socket;
        }
        
        /**
         * Method that will run when thread is started.
         */
        @Override
        public void run() {
            textArea.append("Client connected. (" + socket.getInetAddress() + ":" + socket.getPort() + ")\n");
            JsonReader reader = null;
            JsonWriter writer = null;
            
            try {
                while (socket.getInputStream().available() < 0) {
                    
                }
                
                reader = Json.createReader(socket.getInputStream());
                writer = Json.createWriter(socket.getOutputStream());
                
                JsonObject object = reader.readObject();
                
                Loan loan = new Loan();
                loan.setAnnualInterestRate(Double.parseDouble("" + object.get("annual_rate")));
                loan.setNumberOfYears(object.getInt("years"));
                loan.setLoanAmount(Double.parseDouble("" + object.get("amount")));
                
                textArea.append("Current Loan:\n" + loan + "\n");
                textArea.append("Calculations:\nMonthly Payment: " + loan.getMonthlyPayment() + "\nTotal Payment: " + loan.getTotalPayment() + "\n");
                textArea.append("Sending Calculations.\n");
                
                JsonObjectBuilder loanBuilder = Json.createObjectBuilder();
                loanBuilder.add("monthly_payment", loan.getMonthlyPayment());
                loanBuilder.add("total_payment", loan.getTotalPayment());
                writer.writeObject(loanBuilder.build());
                socket.getOutputStream().flush();
                textArea.append("Calculations Sent.\n");
                
                reader.close();
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(ObjectLoanServer.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    socket.close();
                } catch (IOException ex) {
                    Logger.getLogger(ObjectLoanServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            textArea.append("Client disconnected. (" + socket.getInetAddress() + ":" + socket.getPort() + ")\n");
        }
    }
}
