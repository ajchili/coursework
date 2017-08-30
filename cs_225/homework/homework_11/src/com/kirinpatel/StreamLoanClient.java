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
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.*;
import javax.json.stream.*;
import javax.swing.*;

/**
 * This class sends and received JSON streams from a server.
 *
 * @author Kirin Patel
 * @version 1.0
 */
public class StreamLoanClient extends JFrame {
    
    private Random random;
    private JTextArea textArea;
    private JTextField airTextField;
    private JTextField tiyTextField;
    private JTextField aTextField;
    Socket service = null;
    
    /**
     * Main constructor that will create the LoanClient.
     * 
     * @param title Title
     */
    public StreamLoanClient(String title) {
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
        
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        JButton randomize = new JButton("Generate Loan");
        randomize.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                airTextField.setText(random.nextInt(25) + "." + random.nextInt(100));
                tiyTextField.setText("" + (random.nextInt(25) + 1));
                aTextField.setText(random.nextInt(10000) + "." + random.nextInt(100));
            }
        });
        JButton sendData = new JButton("Send Loan");
        sendData.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    service = new Socket("localhost", 8000);

                    new Thread(new LoanThread(service)).start();
                } catch (IOException ex) {
                    // Error handling
                    textArea.append("Error connecting to server.");
                    System.exit(0);
                }
            }
        });
        buttonPanel.add(randomize);
        buttonPanel.add(sendData);
        add(buttonPanel);
        
        setLocationRelativeTo(null);
        
        setVisible(true);
    }
    
    /**
     * Main method.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        new StreamLoanClient("Stream Loan Client");
    }
    
    /**
     * This class runs the JSON communication between client and server.
     */
    class LoanThread implements Runnable {
        
        private Socket socket;
        
        /**
         * Main constructor that will get the socket used to connect to a server.
         * 
         * @param socket Socket
         */
        public LoanThread(Socket socket) {
            this.socket = socket;
        }
        
        /**
         * Method that will run when thread is started.
         */
        @Override
        public void run() {
            try {
                textArea.append("Connected to server. (" + socket.getInetAddress() + ":" + socket.getPort() + ")\n");
                
                Loan loan = new Loan(Double.parseDouble(airTextField.getText()), Integer.parseInt(tiyTextField.getText()), Double.parseDouble(aTextField.getText()));
                textArea.append("Sending Loan:\n" + loan + "\n");
                
                JsonGenerator generator = Json.createGenerator(socket.getOutputStream());
                generator.writeStartObject();
                generator.write("annual_rate", loan.getAnnualInterestRate());
                generator.write("years", loan.getNumberOfYears());
                generator.write("amount", loan.getLoanAmount());
                generator.writeEnd();
                generator.flush();
                
                textArea.append("Loan sent.\nWaiting for Calculations.\n");
                
                while(socket.getInputStream().available() < 0) {
                    
                }
                
                textArea.append("Calculations Recieved.\n");
		JsonParser parser = Json.createParser(socket.getInputStream());
                
                String keyName = null;
                double monthlyPayment = 0;
                double totalPayment = 0;
                while(parser.hasNext()) {
                    JsonParser.Event event = parser.next();
                    switch(event) {
                        case KEY_NAME:
                            keyName = parser.getString();
                            break;
                        case VALUE_NUMBER:
                            String value = parser.getString();
                            switch(keyName) {
                                case "monthly_payment":
                                    monthlyPayment = Double.parseDouble(value);
                                    break;
                                case "total_payment":
                                    totalPayment = Double.parseDouble(value);
                                    break;
                            }
                            break;
                    }
                }
                
                textArea.append("Loan Calculations:\nMonthly Payment: " + monthlyPayment + "\tTotal Payment: " + totalPayment + "\n");
                
                parser.close();
                generator.close();
            } catch (IOException ex) {
                Logger.getLogger(StreamLoanClient.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    service.close();
                } catch (IOException ex) {
                    Logger.getLogger(StreamLoanClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            textArea.append("Disconnecting from server.\n");
        }
    }
}
