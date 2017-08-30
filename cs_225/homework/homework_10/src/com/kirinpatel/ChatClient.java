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
import javax.swing.*;

/**
 * This class contains all the information pertaining to ChatClient.
 *
 * @author Kirin Patel
 * @version 1.0
 */
public class ChatClient extends JFrame {
    
    private static String username = "";
    private static boolean sendMessage = false;
    private static JTextArea textArea;
    private static JTextField text;
    
    /**
     * Main constructor that will create the ChatClient.
     * 
     * @param title Title
     */
    public ChatClient(String title) {
        super(title);
        
        while (username.length() == 0) {
            username = JOptionPane.showInputDialog("Please enter a username:");
        }
        
        setSize(500, 300);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(textArea);
        add(scroll, BorderLayout.CENTER);
        
        JPanel pane = new JPanel(new GridLayout(1, 2));
        text = new JTextField();
        JButton send = new JButton("Send Message");
        send.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage = true;
            }
        });
        pane.add(text);
        pane.add(send);
        add(pane, BorderLayout.SOUTH);
        
        setLocationRelativeTo(null);
        
        setVisible(true);
        
        Socket service = null;
        
        try {
            service = new Socket("localhost", 8000);
            
            new Thread(new MessageThread(service)).start();
        } catch (IOException ex) {
            // Error handling
            textArea.append("Error connecting to server.");
            System.exit(0);
        }
    }
    
    /**
     * Main method.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        new ChatClient("Chat - Client");
    }
    
    /**
     * Thread that will handle message transmissions for client.
     */
    class MessageThread implements Runnable {

        private Socket socket;
        
        public MessageThread(Socket socket) {
            this.socket = socket;
        }
        
        @Override
        public void run() {
            try {
                DataInputStream input = null;
                DataOutputStream output = null;
                
                input = new DataInputStream(socket.getInputStream());
                output = new DataOutputStream(socket.getOutputStream());
                
                output.writeUTF(username);
                setTitle(getTitle() + " : " + username);
                
                while(true) {
                    if (sendMessage && text.getText().length() > 0) {
                        output.writeInt(1);
                        output.writeUTF(username + ": " + text.getText());
                        text.setText("");
                        sendMessage = false;
                    }
                    
                    if (input.available() > 0) {
                        textArea.append(input.readUTF() + "\n");
                    }
                }
            } catch (IOException ex) {
                // Error handling
                textArea.append("Error communicating with server.");
            }
        }
    }
}
