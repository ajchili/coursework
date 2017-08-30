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
import java.util.concurrent.*;
import javax.swing.*;

/**
 * This class pertains all the information for the ChatServer.
 * 
 * @author Kirin Patel
 * @version 1.0
 */
public class ChatServer extends JFrame {
    
    private static JTextArea textArea;
    private static ArrayList<String> messages = new ArrayList<>();
    
    /**
     * Main constructor that will create ChatServer.
     * 
     * @param title Title
     */
    public ChatServer(String title) {
        super(title);
        
        setSize(300, 200);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        textArea = new JTextArea();
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
                
                executor.execute(new ChatServerThread(socket));
            }
        } catch (IOException ex) {
            // Error handling
            textArea.append("Error creating server: " + ex.getMessage());
            System.exit(0);
        } finally {
            try {
                executor.shutdown();
                
                while(!executor.isTerminated()) {
                    
                }
                
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
        ChatServer server = new ChatServer("Chat - Server");
    }
    
    /**
     * Thread that handles chat server.
     */
    class ChatServerThread implements Runnable {
        
        private Socket socket;
        private ArrayList<String> clientMessages = new ArrayList<>();
        
        public ChatServerThread(Socket socket) {
            this.socket = socket;
        }
        
        @Override
        public void run() {
            Date date = new Date();
            
            DataInputStream input = null;
            DataOutputStream output = null;
            
            try {
                input = new DataInputStream(socket.getInputStream());
                output = new DataOutputStream(socket.getOutputStream());
                
                String username = input.readUTF();
                
                textArea.append(date.getHours() + ":" + date.getMinutes() + " - " + username + " connected - " + socket.getInetAddress() + ":" + socket.getPort() + "\n");
            
                while(true) {
                    if (input.readInt() == 1)
                        messages.add(input.readUTF());
                    
                    if (messages.size() > clientMessages.size()) {
                        for (String message : messages) {
                            if (!clientMessages.contains(message)) {
                                output.writeUTF(message);
                                clientMessages.add(message);
                            }
                        }
                    }
                }
            } catch (IOException ex) {
                // Error handling
                textArea.append("Error connecting client: " + ex.getMessage() + "\n");
            } finally {
                try {
                    input.close();
                    output.close();
                    socket.close();
                } catch (IOException ex) {
                    // Error handling
                    textArea.append("Error disconnecting client: " + ex.getMessage());
                }
            }
        }
    }
}
