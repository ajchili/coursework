/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

/**
 * This class will create a GUI to interact with a database containing the STAFF
 * table. This class can view, insert, and update the table.
 * 
 * @author Kirin Patel
 * @version 1.0
 */
public class StaffGUI extends JFrame {
    
    private final String[] STAFF_COLUMN_NAMES = {"ID", "LASTNAME", "FIRSTNAME", "MI", "ADDRESS", "CITY", "STATE", "TELEPHONE", "EMAIL"};
    
    /**
     * Main constructor that will create the GUI.
     * 
     * @param title Title
     */
    public StaffGUI(String title) {
        super(title);
        
        setLayout(new GridLayout(3, 9));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        add(new JLabel("ID"));
        add(new JLabel("LASTNAME"));
        add(new JLabel("FIRSTNAME"));
        add(new JLabel("MI"));
        add(new JLabel("ADDRESS"));
        add(new JLabel("CITY"));
        add(new JLabel("STATE"));
        add(new JLabel("TELEPHONE"));
        add(new JLabel("EMAIL"));
        
        TextField id = new TextField();
        add(id);
        TextField lastname = new TextField();
        add(lastname);
        TextField firstname = new TextField();
        add(firstname);
        TextField mi = new TextField();
        add(mi);
        TextField address = new TextField();
        add(address);
        TextField city = new TextField();
        add(city);
        TextField state = new TextField();
        add(state);
        TextField telephone = new TextField();
        add(telephone);
        TextField email = new TextField();
        add(email);
        
        add(new JLabel());
        JButton view = new JButton("View");
        view.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] data = viewInformation(id.getText());
                if (data[0] != null) {
                    id.setText(data[0]);
                    lastname.setText(data[1]);
                    firstname.setText(data[2]);
                    mi.setText(data[3]);
                    address.setText(data[4]);
                    city.setText(data[5]);
                    state.setText(data[6]);
                    telephone.setText(data[7]);
                    email.setText(data[8]);
                } else {
                    JOptionPane.showMessageDialog(null, "No staff member found with specified ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(view);
        add(new JLabel());
        add(new JLabel());
        JButton insert = new JButton("Insert");
        insert.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                insertInformation(id.getText(), lastname.getText(), firstname.getText(), mi.getText(), address.getText(), city.getText(), state.getText(), telephone.getText(), email.getText());
            }
        });
        add(insert);
        add(new JLabel());
        add(new JLabel());
        JButton update = new JButton("Update");
        update.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                updateInformation(id.getText(), lastname.getText(), firstname.getText(), mi.getText(), address.getText(), city.getText(), state.getText(), telephone.getText(), email.getText());
            }
        });
        add(update);
        JButton clear = new JButton("Clear");
        clear.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                id.setText("");
                lastname.setText("");
                firstname.setText("");
                mi.setText("");
                address.setText("");
                city.setText("");
                state.setText("");
                telephone.setText("");
                email.setText("");
            }
        });
        add(clear);
        
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
        new StaffGUI("Staff Database Viewer");
    }
    
    /**
     * Pull information from STAFF table with given ID.
     * 
     * @param staff_id Staff ID
     * @return Returns information of specified Staff ID
     */
    private String[] viewInformation(String staff_id) {
        String[] data = new String[STAFF_COLUMN_NAMES.length];
        try {
            Statement statement = establishDatabaseConnection().createStatement();
            ResultSet result = statement.executeQuery("select * from STAFF where ID = \'" + staff_id + "\'");
            
            while(result.next()) {
                for (int i = 0; i < STAFF_COLUMN_NAMES.length; i++) {
                    data[i] = result.getString(STAFF_COLUMN_NAMES[i]);
                }
            }
            
            return data;
        } catch (SQLException ex) {
            // Error handling
            System.out.println("ERROR: Unable to execute statement. - (" + ex.getMessage() + ")");
            return null;
        }
    }
    
    /**
     * Inserts new Staff member into Staff table with given inputs.
     * 
     * @param id ID
     * @param lastname Last name
     * @param firstname First name
     * @param mi Middle initial
     * @param address Address
     * @param city City
     * @param state State
     * @param telephone Telephone
     * @param email Email address
     */
    private void insertInformation(String id, String lastname, String firstname, String mi, String address, String city, String state, String telephone, String email) {
        try {
            Statement statement = establishDatabaseConnection().createStatement();
            statement.executeUpdate("insert into STAFF(ID, LASTNAME, FIRSTNAME, MI, ADDRESS, CITY, STATE, TELEPHONE, EMAIL) values(\'" + id + "\',\'" + lastname + "\',\'" + firstname + "\',\'" + mi + "\',\'" + address + "\',\'" + city + "\',\'" + state + "\',\'" + telephone + "\',\'" + email + "\')");
            
            JOptionPane.showMessageDialog(null, "Staff member added", "Staff member created", JOptionPane.PLAIN_MESSAGE);
        } catch (SQLException ex) {
            // Error handling
            System.out.println("ERROR: Unable to execute statement. - (" + ex.getMessage() + ")");
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Unable to create staff member", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Updates a Staff member in the Staff table with given inputs.
     * 
     * @param id ID
     * @param lastname Last name
     * @param firstname First name
     * @param mi Middle initial
     * @param address Address
     * @param city City
     * @param state State
     * @param telephone Telephone
     * @param email Email address
     */
    private void updateInformation(String id, String lastname, String firstname, String mi, String address, String city, String state, String telephone, String email) {
        try {
            Statement statement = establishDatabaseConnection().createStatement();
            statement.executeUpdate("update STAFF set ID = \'" + id + "\', LASTNAME = \'" + lastname + "\', FIRSTNAME = \'" + firstname + "\', MI = \'" + mi + "\', ADDRESS = \'" + address + "\', CITY = \'" + city + "\', STATE = \'" + state + "\', TELEPHONE = \'" + telephone + "\', EMAIL = \'" + email + "\'  where ID = \'" + id + "\'");
            
            JOptionPane.showMessageDialog(null, "Staff member updated.", "Staff member updated", JOptionPane.PLAIN_MESSAGE);
        } catch (SQLException ex) {
            // Error handling
            System.out.println("ERROR: Unable to execute statement. - (" + ex.getMessage() + ")");
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Unable to update staff member", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Connects GUI client to database.
     * 
     * @return Database connection
     */
    private Connection establishDatabaseConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException ex) {
            // Error handling
            System.out.println("ERROR: Unable to locate drivers.");
        }
        
        Connection connection = null;
        
        try {
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
        } catch (SQLException ex) {
            // Error handling
            System.out.println("ERROR: Unable to connect to database. - (" + ex.getMessage() + ")");
        }
        
        return connection;
    }
}
