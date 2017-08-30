/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * This class contains all the information pertaining to the LoanClient.
 * 
 * @author Kirin Patel
 * @version 1.0
 */
public class LoanClient extends JFrame {
    
    private Random random;
    private JTextArea textArea;
    private JTextField airTextField;
    private JTextField tiyTextField;
    private JTextField aTextField;
    private int getLoanInfo = -1;
    
    /**
     * Main constructor that will create the LoanClient.
     * 
     * @param title Title
     */
    public LoanClient(String title) {
        super(title);
        
        random = new Random();
        
        setSize(500, 300);
        setLayout(new GridLayout(3, 1));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        textArea = new JTextArea();
        JScrollPane scroll = new JScrollPane(textArea);
        add(scroll);
        
        JPanel inputPanel = new JPanel(new GridLayout(2, 3));
        inputPanel.add(new JLabel("Annual Interest Rate"));
        inputPanel.add(new JLabel("Time in Years"));
        inputPanel.add(new JLabel("Amount"));
        airTextField = new JTextField();
        tiyTextField = new JTextField();
        aTextField = new JTextField();
        inputPanel.add(airTextField);
        inputPanel.add(tiyTextField);
        inputPanel.add(aTextField);
        add(inputPanel);
        
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        JButton randomize = new JButton("Generate Loan");
        randomize.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                airTextField.setText("" + random.nextInt() + random.nextDouble() + 1);
                tiyTextField.setText("" + random.nextInt(25) + 1);
                aTextField.setText("" + random.nextInt() + random.nextDouble() + 1);
            }
        });
        JButton sendData = new JButton("Send as Data");
        sendData.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                getLoanInfo = 0;
            }
        });
        JButton sendObject = new JButton("Send as Object");
        sendObject.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                getLoanInfo = 1;
            }
        });
        buttonPanel.add(randomize);
        buttonPanel.add(sendData);
        buttonPanel.add(sendObject);
        add(buttonPanel);
        
        setLocationRelativeTo(null);
        
        setVisible(true);
        
        Socket service = null;
        
        try {
            service = new Socket("localhost", 8000);
            
            new Thread(new LoanThread(service)).start();
        } catch (IOException ex) {
            // Error handling
            textArea.append("Error connecting to server.");
            System.exit(0);
        }
    }
    
    /**
     * This method will provide requested information based upon provided loan.
     * 
     * @param type Type of data being sent
     * @return Two double values that are the annual interest rate and total
     * payment
     */
    private double[] getLoanData(int type) {
        double[] loanData = new double[2];
        
        textArea.append("Connecting to server");
        Socket service = null;
        ObjectInputStream input = null;
        ObjectOutputStream output = null;
        
        try {
            service = new Socket("localhost", 8000);
            
            textArea.append(" (" + service.getInetAddress() + ":" + service.getPort() + ")\n");
            
            input = new ObjectInputStream(service.getInputStream());
            output = new ObjectOutputStream(service.getOutputStream());
        
            if (input.readInt() == 1) {
                textArea.append("Connected!\n");
                
                output.writeInt(type);
                System.out.println(type);
                switch(type) {
                    case 0:
                        textArea.append("Sending data...\n");
                        break;
                    case 1:
                        textArea.append("Sending object...\n");
                        break;
                    default:
                        break;
                } 
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
                Logger.getLogger(CountingClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return loanData;
    }
    
    /**
     * Main method.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        new LoanClient("Loan Client");
    }
    
    /**
     * Thread that will process all loan requests made to the LoanServer.
     */
    class LoanThread implements Runnable {
        
        private Socket socket;
        
        public LoanThread(Socket socket) {
            this.socket = socket;
        }
        
        @Override
        public void run() {
            ObjectInputStream input = null;
            ObjectOutputStream output = null;
            
            try {
                input = new ObjectInputStream(socket.getInputStream());
                output = new ObjectOutputStream(socket.getOutputStream());
                
                while(true) {
                    switch(getLoanInfo) {
                        case 0:
                            output.writeInt(0);
                            output.writeDouble(Double.parseDouble(airTextField.getText()));
                            output.writeDouble(Integer.parseInt(tiyTextField.getText()));
                            output.writeDouble(Double.parseDouble(aTextField.getText()));
                            getLoanInfo = -1;
                            break;
                        case 1:
                            output.writeInt(1);
                            output.writeObject(new Loan(Double.parseDouble(airTextField.getText()), Integer.parseInt(tiyTextField.getText()), Double.parseDouble(aTextField.getText())));
                            getLoanInfo = -1;
                            break;
                    }
                    
                    System.out.println(input.readDouble());
                    System.out.println(input.readDouble());
                }
            } catch (IOException ex) {
                Logger.getLogger(LoanClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
