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
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.*;
import javax.json.*;
import javax.swing.*;

/**
 *
 * @author Kirin Patel
 * @version 1.0
 */
public class Server extends JFrame {
    
    private ExecutorService connectionExecutor;
    private ServerSocket service;
    private int connectedClients = 0;
    private int clearedRow = 0;
    private boolean isRunning = true;
    private boolean isGameRunning = false;
    private boolean hasShutdown = false;
    private boolean sendGameBoard = false;
    private boolean sendNextPiece = false;
    private boolean hasLost = false;
    private boolean hasVotes = false;
    private boolean resetVote = false;
    private ArrayList<Integer> currentVotes = new ArrayList<>();
    private ArrayList<GameObject> nextPieces = new ArrayList<>();
    private com.kirinpatel.util.Map map = new com.kirinpatel.util.Map();
    private com.kirinpatel.util.Map ground = new com.kirinpatel.util.Map();
    private ArrayList<User> users = new ArrayList<>();
    
    private DBConnection connection;
    private GamePane gamePane;
    private StatusPane statusPane;
    private GamePiecesPane gamePiecesPane;
    
    /**
     * Primary constructor that will create the Server.
     * 
     * @param title Title
     */
    public Server(String title) {
        super(title);
        connection = new DBConnection();
        
        setSize(410, 550);
        setResizable(false);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        addComponentListener(new ServerComponentListener());
        
        gamePane = new GamePane();
        add(gamePane, BorderLayout.CENTER);
        
        statusPane = new StatusPane(0);
        add(statusPane, BorderLayout.EAST);
        
        gamePiecesPane = statusPane.getGamePiecesPane();
        int rr = 0, rl = 0, mr = 0, ml = 0;
        currentVotes.add(rr);
        currentVotes.add(rl);
        currentVotes.add(mr);
        currentVotes.add(ml);
        
        new Thread(new ServerTask()).start();
        new Thread(new GameTask()).start();
        
        setVisible(true);
    }
    
    /**
     * Stops server.
     */
    private void stop() {
        hasShutdown = true;
        isRunning = false;
        
        if(connectedClients == 0) {
            dispose();
            System.exit(0);
        }
    }
    
    /**
     * Custom ComponentListener that will be used to handle server GUI events.
     */
    class ServerComponentListener implements ComponentListener {

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
     * Custom Runnable that handles game logic.
     */
    class GameTask implements Runnable {
        
        private boolean getNewPiece = false;
        private FallingObject fallingObject;
        private GameObject currentObject;
        
        /**
         * This method handles all game logic.
         */
        @Override
        public void run() {
            while(isRunning) {
                if (connectedClients >= 2 && !isGameRunning) {
                    isGameRunning = true;
                } else if (connectedClients < 2 && isGameRunning) {
                    isGameRunning = false;
                    currentObject = null;
                    map = new com.kirinpatel.util.Map();
                    ground = map;
                    nextPieces.clear();
                    gamePiecesPane.setGamePieces(nextPieces);
                    gamePane.setMap(ground);
                }
                
                if (isGameRunning && !hasLost) {
                    int maxVotes = 0;
                    for (int a : currentVotes) 
                        if (a > maxVotes) {
                            maxVotes = a;
                            hasVotes = true;
                            resetVote = true;
                        }
                    statusPane.setVotes("(0/" + connectedClients + ") " + "(0/" + connectedClients + ") " + "(0/"+ connectedClients + ") " + "(0/" + connectedClients + ") ");
                    
                    
                    for (int i = nextPieces.size(); i < 10; i++) {
                        nextPieces.add(new GameObject(4, 0));
                    }
                    
                    if (currentObject == null) {
                        currentObject = nextPieces.get(0);
                        nextPieces.remove(0);
                        currentObject.setCoords(4, 0);
                        sendNextPiece = true;
                        fallingObject = new FallingObject(currentObject);
                    }
                    
                    map.clearMap();
                    map.setMap(ground);
                    if (fallingObject.canFall(ground.getMap())) {
                        fallingObject.setCoords(fallingObject.getX(), fallingObject.getY() + 1);
                        if (connectedClients >= 2 && hasVotes) {
                            for (int i = 0; i < currentVotes.size(); i++) {
                                if (currentVotes.get(i) == maxVotes) {
                                    switch(i) {
                                        case 0:
                                            if (fallingObject.checkIfRotateIsPossible(0, ground.getMap())) currentObject.rotateObjectLeft();
                                            break;
                                        case 1:
                                            if (fallingObject.checkIfRotateIsPossible(1, ground.getMap())) currentObject.rotateObjectRight();
                                            break;
                                        case 2:
                                            if (fallingObject.checkIfMoveIsPossible(0, ground.getMap())) fallingObject.setCoords(fallingObject.getX() - 1, fallingObject.getY());
                                            break;
                                        case 3:
                                            if (fallingObject.checkIfMoveIsPossible(1, ground.getMap())) fallingObject.setCoords(fallingObject.getX() + 1, fallingObject.getY());
                                            break;
                                    }

                                    if (currentObject != null) {
                                        currentObject.setCoords(fallingObject.getX(), fallingObject.getY());
                                        fallingObject = new FallingObject(currentObject);
                                    }
                                }

                                currentVotes.set(i, 0);
                            }

                            hasVotes = false;
                        }
                        map.addFallingObject(fallingObject);
                    } else {
                        if (!getNewPiece) {
                            if (fallingObject.getY() == 0)
                                hasLost = true;
                            
                            ground.addFallingObject(fallingObject);
                            map.setMap(ground);
                            getNewPiece = true;
                        } else {
                            for (int i = 19; i > 0; i--) {
                                boolean isRowFilled = false;
                                for (int j = 0; j < 10; j++) {
                                    if ((ground.getPiece(j, i) == null)) {
                                        isRowFilled = false;
                                        break;
                                    } else {
                                        isRowFilled = true;
                                    }
                                }

                                if (isRowFilled) {
                                    moveDown(i);
                                        
                                    clearedRow++;
                                    isRowFilled = false;
                                }
                            }
                            
                            currentObject = null;
                            getNewPiece = false;
                        }
                    }
                    
                    gamePiecesPane.setGamePieces(nextPieces);
                    gamePane.setMap(map);
                    
                    sendGameBoard = true;
                }
                
                try {
                    Thread.sleep(Main.GAME_TICK);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        /**
         * This method removes a specified row and then moves rows above this
         * row down.
         * 
         * @param row Filled row
         */
        public void moveDown(int row) {
            for (int i = 0; i < 10; i++) {
                for (int j = row; j > 0; j--) {
                    ground.setPiece(ground.getPiece(i, j - 1), i, j);
                }
            }
        }
    }
    
    /**
     * Custom Runnable that handles all establishments of server/client
     * connection.
     */
    class ServerTask implements Runnable {
        
        /**
         * This method handles the establishment of connections.
         */
        @Override
        public void run() {
            connectionExecutor = Executors.newFixedThreadPool(10);
            
            try {
                service = new ServerSocket(8000);
                service.setReuseAddress(true);
                
                Socket socket;
                
                do {
                    socket = service.accept();
                    
                    connectionExecutor.execute(new ServerSocketTask(socket));
                } while (isRunning);
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (service != null)
                    if (!service.isClosed())
                        try {
                            service.close();

                            connectionExecutor.shutdown();

                            while (!connectionExecutor.isTerminated()) {

                            }
                        } catch (IOException ex) {
                            // Error handling
                            System.out.println("Error closing server. " + ex.getMessage());
                            System.exit(1);
                        }
            }
        }
    }
    
    /**
     * Custom Runnable that handles communications between the server and
     * client.
     */
    class ServerSocketTask implements Runnable {
        
        private boolean hasConnected = false;
        private Socket socket;
        private User user;
        private boolean gameState;
        private boolean hasVoted;
        
        /**
         * Primary constructor that setups a connection to a client with a given
         * socket.
         * 
         * @param socket Socket
         */
        public ServerSocketTask(Socket socket) {
            this.socket = socket;
        }
        
        /**
         * This method handles all server/client communications.
         */
        @Override
        public void run() {
            try {
                waitForMessage();
                
                JsonObject object = Json.createReader(socket.getInputStream()).readObject();
                
                if (object.getInt("type") ==  0) {
                    hasConnected = object.getInt("message") == 1;
                    connectedClients++;
                    statusPane.setNumberOfConnectedUsers(connectedClients);
                    
                    // Send connection message
                    JsonObjectBuilder messageBuilder = Json.createObjectBuilder();
                    messageBuilder.add("type", 0);
                    messageBuilder.add("message", 1);
                    Json.createWriter(socket.getOutputStream()).writeObject(messageBuilder.build());
                    flush();
                }
                
                while(isRunning && hasConnected) {
                    if (socket.getInputStream().available() > 0) {
                        object = Json.createReader(socket.getInputStream()).readObject();
                        
                        if (user != null)
                            System.out.println(user.toString() + " - " + object);
                        else
                            System.out.println("Connecting user - " + object);
                        
                        switch(object.getInt("type")) {
                            case 0:
                                switch(object.getInt("message")) {
                                    case 0:
                                        // User disconnected
                                        connectedClients--;
                                        statusPane.setNumberOfConnectedUsers(connectedClients);
                                        users.remove(user);
                                        updateUsers();
                                        hasConnected = false;
                                        break;
                                }
                                break;
                            case 1:
                                // Get Username
                                user = connection.getUser(object.getInt("message"));
                                users.add(user);
                                updateUsers();
                                if (connectedClients >= 1) {
                                    sendGameState();
                                    sendNextPiece();
                                    sendGameBoard();
                                }
                                break;
                            case 5:
                                // Get Vote
                                if (!hasVoted && gameState) {
                                    int vote = currentVotes.get(object.getInt("message")) + 1;
                                    currentVotes.set(object.getInt("message"), vote);
                                    statusPane.setVotes("(" + currentVotes.get(0) + "/" + connectedClients + ") " + "(" + currentVotes.get(1) + "/" + connectedClients + ") " + "(" + currentVotes.get(2) + "/" + connectedClients + ") " + "(" + currentVotes.get(3) + "/" + connectedClients + ") ");
                                    hasVoted = true;
                                }
                                break;
                        }
                    }
                    
                    if (resetVote) {
                        hasVoted = false;
                        resetVote = false;
                    }
                    
                    if (clearedRow > 0) {
                        for (int i = 0; i < clearedRow; i++)
                            connection.updateStats(3, user.getID());
                        
                        clearedRow = 0;
                    }
                    
                    if (sendGameBoard) {
                        sendGameBoard();
                        sendGameBoard = false;
                        if (hasVoted) {
                            connection.updateStats(1, user.getID());
                        } else {
                            connection.updateStats(2, user.getID());
                        }
                    }
                    
                    if (sendNextPiece) {
                        sendNextPiece();
                        sendNextPiece = false;
                    }
                    
                    if (gameState != isGameRunning) {
                        sendGameState();
                        gameState = isGameRunning;
                        if (gameState == true)
                            sendNextPiece();
                        
                        connection.updateStats(0, user.getID());
                    }
                    
                    if (hasShutdown) {
                        closeServer();
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (socket != null)
                        socket.close();
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        /**
         * Waits for message from client.
         * 
         * @throws IOException
         */
        private synchronized void waitForMessage() throws IOException {
            while (socket.getInputStream().available() < 0 && isRunning) {
            
            }
        }
        
        /**
         * Flushes output stream to client.
         * 
         * @throws IOException 
         */
        private synchronized void flush() throws IOException {
            if (socket != null)
                socket.getOutputStream().flush();
        }
        
        /**
         * Closes server.
         * 
         * @throws IOException 
         */
        private synchronized void closeServer() throws IOException {
            JsonObjectBuilder messageBuilder = Json.createObjectBuilder();
            messageBuilder.add("type", 0);
            messageBuilder.add("message", 0);
            Json.createWriter(socket.getOutputStream()).writeObject(messageBuilder.build());
            flush();
            isRunning = false;
            dispose();
            System.exit(0);
        }
        
        /**
         * Updates user panel with current users.
         */
        private synchronized void updateUsers() {
            statusPane.getConnectedUsersArea().setText("");
            for(User user : users)
                statusPane.getConnectedUsersArea().append(user.toString() + "\n");
        }
        
        /**
         * Sends game state to users.
         * 
         * @throws IOException 
         */
        private synchronized void sendGameState() throws IOException {
            JsonObjectBuilder messageBuilder = Json.createObjectBuilder();
            messageBuilder.add("type", 2);
            messageBuilder.add("message", isGameRunning);
            Json.createWriter(socket.getOutputStream()).writeObject(messageBuilder.build());
            flush();
        }
        
        /**
         * Sends next pieces to users.
         * 
         * @throws IOException 
         */
        private synchronized void sendNextPiece() throws IOException {
            JsonObjectBuilder messageBuilder = Json.createObjectBuilder();
            messageBuilder.add("type", 4);
            String pieces = "";
            for (GameObject piece : nextPieces) 
                pieces += piece.getType();
            messageBuilder.add("message", pieces);
            Json.createWriter(socket.getOutputStream()).writeObject(messageBuilder.build());
            flush();
        }
        
        /**
         * Sends Map to users.
         * 
         * @throws IOException 
         */
        private synchronized void sendGameBoard() throws IOException {
            JsonObjectBuilder messageBuilder = Json.createObjectBuilder();
            messageBuilder.add("type", 3);
            messageBuilder.add("message", map.toString());
            Json.createWriter(socket.getOutputStream()).writeObject(messageBuilder.build());
            flush();
        }
    }
}
