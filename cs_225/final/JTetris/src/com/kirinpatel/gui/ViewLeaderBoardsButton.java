/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel.gui;

import com.kirinpatel.DatabaseViewer;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Kirin Patel
 * @version 1.0
 */
public class ViewLeaderBoardsButton extends JButton {
    
    /**
     * Main constructor that will create a JButton to view leader board
     * statistics.
     */
    public ViewLeaderBoardsButton() {
        super("Stats");
        
        addActionListener(new ViewLeaderBoardsButtonListener());
    }
    
    /**
     * Secondary constructor that will create a JButton to view leader board
     * statistics from a server.
     */
    public ViewLeaderBoardsButton(String ip) {
        super("Stats");
        
        addActionListener(new ViewLeaderBoardsButtonListener(ip));
    }
    
    /**
     * Custom ActionListener that will handle all click events.
     */
    class ViewLeaderBoardsButtonListener implements ActionListener {

        private String ip = "localhost";
        
        /**
         * Main constructor.
         */
        public ViewLeaderBoardsButtonListener() {
            
        }
        
        /**
         * Secondary constructor used when a remote server needs to be accessed
         * for statistics.
         * 
         * @param ip Server ip address
         */
        public ViewLeaderBoardsButtonListener(String ip) {
            this.ip = ip;
        }
        
        /**
         * Method that will be run on click event.
         * 
         * @param e ActionEvent
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            
            new Thread(new Runnable() {
               @Override
               public void run() {
                   
               }
            });
            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                
                Connection connection;
                connection = DriverManager.getConnection("jdbc:derby://" + ip + ":1527/sample", "app", "app");
                if (connection != null)
                    new DatabaseViewer(connection);
            } catch (SQLException ex) {
                Logger.getLogger(ViewLeaderBoardsButton.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ViewLeaderBoardsButton.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
}
