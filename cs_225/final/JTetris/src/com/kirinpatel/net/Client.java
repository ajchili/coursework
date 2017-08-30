/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel.net;

import com.kirinpatel.Main;
import com.kirinpatel.gui.*;
import com.kirinpatel.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import javax.json.*;
import javax.swing.*;

/**
 *
 * @author Kirin Patel
 * @verion 1.0
 */
public class Client extends JFrame {
    
    private String ip;
    private static int vote = -1;
    private boolean isConnected = false;
    private boolean disconnect = false;
    private boolean hasVoted = false;
    private Socket socket;
    private ArrayList<GameObject> nextPieces = new ArrayList<>();
    private com.kirinpatel.util.Map map = new com.kirinpatel.util.Map();
    
    private DBConnection connection;
    private GamePane gamePane;
    private StatusPane statusPane;
    private GamePiecesPane gamePiecesPane;
    
    /**
     * Primary constructor that will create the client.
     * 
     * @param title Title
     * @param ip Server ip address
     */
    public Client(String title, String ip) {
        super(title);
        this.ip = ip;
        connection = new DBConnection();
        
        setSize(410, 550);
        setResizable(false);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        addComponentListener(new ClientComponentListener());
        
        gamePane = new GamePane();
        add(gamePane, BorderLayout.CENTER);
        
        statusPane = new StatusPane(1, ip);
        add(statusPane, BorderLayout.EAST);
        
        gamePiecesPane = statusPane.getGamePiecesPane();
        
        new Thread(new ClientTask()).start();
        
        setVisible(true);
    }
    
    /**
     * Stops client.
     */
    private void stop() {
        disconnect = true;
    }
    
    /**
     * Sets if the client can vote.
     * 
     * @param clientVote Can the client vote
     */
    public static void sendVote(int clientVote) {
        vote = clientVote;
    }
    
    /**
     * Custom ComponentListener that will be used to handle client GUI events.
     */
    class ClientComponentListener implements ComponentListener {

        /**
         * Unsupported.
         * 
         * @param e ComponentEvent
         */
        @Override
        public void componentResized(ComponentEvent e) {
            // Not supported
        }

        /**
         * Unsupported.
         * 
         * @param e ComponentEvent
         */
        @Override
        public void componentMoved(ComponentEvent e) {
            // Not supported
        }

        /**
         * Unsupported.
         * 
         * @param e ComponentEvent
         */
        @Override
        public void componentShown(ComponentEvent e) {
            // Not supported
        }

        /**
         * Stop server on hide.
         * 
         * @param e ComponentEvent
         */
        @Override
        public void componentHidden(ComponentEvent e) {
            stop();
        }
    }
    
    /**
     * Custom Runnable that handles basic start of client connection to a
     * server.
     */
    class ClientTask implements Runnable {
        
        /**
         * This method will establish the base connection.
         */
        @Override
        public void run() {
            try {
                socket = new Socket(ip, 8000);
                socket.setKeepAlive(true);

                new Thread(new ClientSocketThread()).start();
            } catch (IOException ex) {
                // Error handling
                stop();
                dispose();
                JOptionPane.showMessageDialog(null, "Unable to connect to server.");
                new Main("JTetris");
            }
            
        }
    }
    
    /**
     * Custom Runnable that handles all communication between the client and
     * server.
     */
    class ClientSocketThread implements Runnable {
        
        private boolean isGameRunning = false;
        private boolean hasSentName = false;
        
        /**
         * This method will send and receive JSON from the server.
         */
        @Override
        public void run() {
            try {
                connectToServer();
                waitForMessage();
                
                JsonObject object = Json.createReader(socket.getInputStream()).readObject();
                if (object.getInt("type") == 0)
                    isConnected = (object.getInt("message") == 1);
                
                while(isConnected) {
                    if (socket.getInputStream().available() > 0) {
                        object = Json.createReader(socket.getInputStream()).readObject();
                        System.out.println(object);
                        switch(object.getInt("type")) {
                            case 0:
                                switch(object.getInt("message")) {
                                    case 0:
                                        // Server closed
                                        isConnected = false;
                                        dispose();
                                        System.exit(0);
                                        break;
                                }
                                break;
                            case 2:
                                // Recieve game state
                                isGameRunning = object.getBoolean("message");
                                statusPane.getVotePane().enableTimebar(isGameRunning);
                                break;
                            case 3:
                                // Recieve map
                                if (isGameRunning) {
                                    statusPane.getVotePane().enableTimebar(isGameRunning);
                                    statusPane.getVotePane().setTimeBarProgress(1000);
                                    vote = -1;
                                    hasVoted = false;
                                }
                                map.parseMap(object.getString("message"));
                                gamePane.setMap(map);
                                break;
                            case 4:
                                // Recieve next piece
                                nextPieces = new ArrayList<>();
                                String blocks = object.getString("message").replace("[", "").replace("]", " ");
                                for (char c : blocks.toCharArray())
                                    nextPieces.add(new GameObject(0, 0, Integer.parseInt(c + "")));
                                gamePiecesPane.setGamePieces(nextPieces);
                                break;
                        }
                    }
                    
                    if (vote != -1 && !hasVoted) {
                        hasVoted = true;
                        sendVote(vote);
                        vote = -1;
                    }
                    
                    if (!hasSentName) {
                        sendUsername();
                        hasSentName = true;
                    }
                    
                    if (disconnect) {
                        disconnectFromServer();
                    }
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            } finally {
                try {
                    if (socket != null)
                        socket.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Unable to close client. Please forecfully close the client.");
                }
            }
        }
        
        /**
         * Waits for message from client.
         * 
         * @throws IOException
         */
        private synchronized void waitForMessage() throws IOException {
            while (socket.getInputStream().available() < 0 && isConnected) {
            
            }
        }
        
        /**
         * Flushes output stream to client.
         * 
         * @throws IOException 
         */
        private synchronized void flush() throws IOException {
            socket.getOutputStream().flush();
        }
        
        /**
         * Connects client to server.
         * 
         * @throws IOException 
         */
        private synchronized void connectToServer() throws IOException {
            JsonObjectBuilder messageBuilder = Json.createObjectBuilder();
            messageBuilder.add("type", 0);
            messageBuilder.add("message", 1);
            Json.createWriter(socket.getOutputStream()).writeObject(messageBuilder.build());
            flush();
        }
        
        /**
         * Connects client to server.
         * 
         * @throws IOException 
         */
        public synchronized void disconnectFromServer() throws IOException {
            JsonObjectBuilder messageBuilder = Json.createObjectBuilder();
            messageBuilder.add("type", 0);
            messageBuilder.add("message", 0);
            Json.createWriter(socket.getOutputStream()).writeObject(messageBuilder.build());
            flush();
            isConnected = false;
            dispose();
            System.exit(0);
        }
        
        /**
         * Sends the system username of connected client to the server.
         * 
         * @throws IOException 
         */
        private synchronized void sendUsername() throws IOException {
            JsonObjectBuilder messageBuilder = Json.createObjectBuilder();
            messageBuilder.add("type", 1);
            messageBuilder.add("message", connection.createUser(System.getProperty("user.name"), 1));
            Json.createWriter(socket.getOutputStream()).writeObject(messageBuilder.build());
            flush();
        }
        
        /**
         * Sends the clients vote to the server.
         * 
         * @param vote Vote
         * @throws IOException
         */
        private synchronized void sendVote(int vote) throws IOException {
            JsonObjectBuilder messageBuilder = Json.createObjectBuilder();
            messageBuilder.add("type", 5);
            messageBuilder.add("message", vote);
            Json.createWriter(socket.getOutputStream()).writeObject(messageBuilder.build());
            flush();
        }
    }
}
