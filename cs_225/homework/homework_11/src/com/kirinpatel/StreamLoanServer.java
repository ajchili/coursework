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
import java.util.logging.*;
import javax.json.*;
import javax.json.stream.*;
import javax.swing.*;

/**
 * This class sends and received JSON streams from a client.
 *
 * @author Kirin Patel
 * @version 1.0
 */
public class StreamLoanServer extends JFrame {
    
    private JTextArea textArea;
    
    /**
     * Main constructor that will create the LoanServer.
     * 
     * @param title Title
     */
    public StreamLoanServer(String title) {
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
        new StreamLoanServer("Stream Loan Server");
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
            
            try {
                while (socket.getInputStream().available() < 0) {
                    
                }
                
		JsonParser parser = Json.createParser(socket.getInputStream());
                
                String keyName = null;
                Loan loan = new Loan();
                int i = 0;
                while(parser.hasNext() && i < 2) {
                    JsonParser.Event event = parser.next();
                    switch(event) {
                        case KEY_NAME:
				keyName = parser.getString();
				break;
			case VALUE_NUMBER:
                                String value = parser.getString();
                                switch(keyName) {
                                    case "annual_rate":
                                        loan.setAnnualInterestRate(Double.parseDouble(value));
                                        break;
                                    case "years":
                                        loan.setNumberOfYears(Integer.parseInt(value));
                                        break;
                                    case "amount":
                                        loan.setLoanAmount(Double.parseDouble(value));
                                        break;
                                }
                                i++;
				break;
                    }
                }
                
                textArea.append("Current Loan:\n" + loan + "\n");
                textArea.append("Calculations:\nMonthly Payment: " + loan.getMonthlyPayment() + "\nTotal Payment: " + loan.getTotalPayment() + "\n");
                textArea.append("Sending Calculations.\n");
                
                JsonGenerator generator = Json.createGenerator(socket.getOutputStream());
                generator.writeStartObject();
                generator.write("monthly_payment", loan.getMonthlyPayment());
                generator.write("total_payment", loan.getTotalPayment());
                generator.writeEnd();
                generator.flush();
                textArea.append("Calculations Sent.\n");
                
                parser.close();
                generator.close();
            } catch (IOException ex) {
                Logger.getLogger(StreamLoanServer.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    socket.close();
                } catch (IOException ex) {
                    Logger.getLogger(StreamLoanServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            textArea.append("Client disconnected. (" + socket.getInetAddress() + ":" + socket.getPort() + ")\n");
        }
    }
}
