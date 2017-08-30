/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;
import java.util.logging.*;

/**
 * This class contains all the information pertaining to the LoanServer.
 *
 * @author Kirin Patel
 * @version 1.0
 */
public class LoanServer {
    
    /**
     * Main constructor that will create the LoanServer.
     */
    public LoanServer() {
        ServerSocket service = null;
        ExecutorService executor = Executors.newCachedThreadPool();
        
        try {
            service = new ServerSocket(8000);
            
            Socket socket;
            
            System.out.println("Server started...");
            
            while(true) {
                socket = service.accept();
                
                executor.execute(new LoanServerThread(socket));
            }
        } catch (IOException ex) {
            // Error handling
            System.out.println("Error creating server. " + ex.getMessage());
            System.exit(0);
        } finally {
            try {
                executor.shutdown();
                
                while(!executor.isTerminated()) {
                    
                }
                
                service.close();
            } catch (IOException ex) {
                // Error handling
                System.out.println("Error closing server: " + ex.getMessage());
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
        new LoanServer();
    }
    
    /**
     * Thread that will handle loan processing requests from clients.
     */
    class LoanServerThread implements Runnable {

        private Socket socket;
        
        public LoanServerThread(Socket socket) {
            this.socket = socket;
        }
        
        @Override
        public void run() {
            ObjectInputStream input = null;
            ObjectOutputStream output = null;
            
            try {
                input = new ObjectInputStream(socket.getInputStream());
                output = new ObjectOutputStream(socket.getOutputStream());
                
                Loan loan;
                
                while(true) {
                    switch(input.readInt()) {
                        case 0:
                            loan = new Loan(input.readDouble(), input.readInt(), input.readDouble());
                            break;
                        case 1:
                            loan = (Loan) input.readObject();
                            break;
                        default:
                            loan = new Loan();
                            break;
                    }
                    
                    System.out.println(loan);
                    
                    output.writeDouble(loan.getAnnualInterestRate());
                    output.writeDouble(loan.getTotalPayment());
                }
            } catch (IOException ex) {
                Logger.getLogger(LoanServer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LoanServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
