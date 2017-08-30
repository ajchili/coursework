/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel.gui;

import java.awt.*;
import javax.swing.*;

/**
 * This class provides statistical information for the server or client.
 *
 * @author Kirin Patel
 * @version 1.0
 */
public class StatusPane extends JPanel {
    
    private int type;
    private JLabel numbOfUsers;
    private JLabel votes;
    private JTextArea connectedUsers;
    private VotePane votePane;
    private GamePiecesPane gamePiecesPane;
    
    /**
     * Main constructor that will create the StatusPane. 
     * Use type of 0 for a server. 
     * Use type of 1 for a client.
     * 
     * @param type Type of panel
     */
    public StatusPane(int type) {
        this.type = type;
        setLayout(new GridLayout(3, 1));
        gamePiecesPane = new GamePiecesPane();
        add(gamePiecesPane);
        
        if (type == 0) {
            JPanel usersPanel = new JPanel(new GridLayout(4, 1));
            numbOfUsers = new JLabel("Connected Users: 0");
            usersPanel.add(numbOfUsers);
            usersPanel.add(new JLabel("   rr      rl      mr    ml"));
            votes = new JLabel("(0/0) (0/0) (0/0) (0/0)");
            usersPanel.add(votes);
            connectedUsers = new JTextArea();
            connectedUsers.setEditable(false);
            JScrollPane scroll = new JScrollPane(connectedUsers);
            usersPanel.add(scroll);
            add(usersPanel);
            add(new ViewLeaderBoardsButton());  
        } else {
            votePane = new VotePane();
            add(votePane);
        }
    }
    
    /**
     * Secondary constructor that will create the StatusPane. This constructor
     * should only be used for creating the client StatusPane.
     * 
     * @param type Type of panel
     * @param ip Server ip address
     */
    public StatusPane(int type, String ip) {
        this(type);
        add(new ViewLeaderBoardsButton(ip));
    }
    
    /**
     * Sets the vote panel string of the StatusPAne.
     * 
     * @param votes Votes
     */
    public void setVotes(String votes) {
        this.votes.setText(votes);
    }
   
    /**
     * Sets the number of connected users. This should only be used for the
     * server StatusPane.
     * 
     * @param numbOfUsers Number of connected users.
     */
    public void setNumberOfConnectedUsers(int numbOfUsers) {
        this.numbOfUsers.setText("Connected Users: " + numbOfUsers);
        setVotes("(0/" + numbOfUsers + ") " + "(0/" + numbOfUsers + ") " + "(0/" + numbOfUsers + ") " + "(0/" + numbOfUsers + ") ");
    }
    
    /**
     * Provides the GamePiecesPane of the StatusPane.
     * 
     * @return Returns GamePiecesPane
     */
    public GamePiecesPane getGamePiecesPane() {
        return gamePiecesPane;
    }
    
    /**
     * Provides the VotePane of the StatusPane. This should only be used for
     * the client StatusPane.
     * 
     * @return Returns VotePane
     */
    public VotePane getVotePane() {
        return votePane;
    }
    
    /**
     * Provides the ConnectedUsersArea of the StatusPane. This should only be
     * used for the server StatusPane.
     * 
     * @return Returns ConnectedUsersArea
     */
    public JTextArea getConnectedUsersArea() {
        return connectedUsers;
    }
}
