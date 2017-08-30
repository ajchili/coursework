/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

/**
 * This class will create a GUI to interact with a user to establish a
 * connection with a database from given information.
 * 
 * @author Kirin Patel
 * @version 1.0
 */
public class ConnectToDatabaseGUI extends JFrame {
    
    /**
     * Main constructor that will create the GUI.
     * 
     * @param title Title
     */
    public ConnectToDatabaseGUI(String title) {
        super(title);
        
        setLayout(new GridLayout(5, 2));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        add(new JLabel("JDBC Driver"));
        JTextField jdbcDriver = new JTextField("org.apache.derby.jdbc.ClientDriver");
        add(jdbcDriver);
        add(new JLabel("URL"));
        JTextField url = new JTextField("jdbc:derby://localhost:1527/sample");
        add(url);
        add(new JLabel("Username"));
        JTextField username = new JTextField("app");
        add(username);
        add(new JLabel("Password"));
        JTextField password = new JTextField("app");
        add(password);
        JButton connect = new JButton("Connect");
        connect.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection connection = getConnection(jdbcDriver.getText(), url.getText(), username.getText(), password.getText());
                if (connection != null)
                    new DatabaseViewer("Database Viewer", connection);
            }
        });
        add(connect);
        
        pack();
        setLocationRelativeTo(null);
        
        setVisible(true);
    }
    
    /**
     * Main method.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        new ConnectToDatabaseGUI("Database Connector");
    }
    
    /**
     * This method will establish a connection to a database and return that
     * connection.
     * 
     * @param driver Database drivers
     * @param url Database url
     * @param username Database username
     * @param password Database password
     * @return Returns connection
     */
    private Connection getConnection(String driver, String url, String username, String password) {
       Connection connection = null;
       
       try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            // Error handling
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Unable to get database drivers", JOptionPane.ERROR_MESSAGE);
        }
        
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            // Error handling
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Unable to connect to database", JOptionPane.ERROR_MESSAGE);
        }
        
       return connection; 
    }
}
