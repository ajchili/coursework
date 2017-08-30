/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * This class will display a table from a database.
 *
 * @author Kirin Patel
 * @version 1.0
 */
public class DatabaseViewer extends JFrame {
    
    private Connection connection;
    
    /**
     * Main constructor that will create the GUI.
     * 
     * @param title Title
     * @param connection Database connection
     */
    public DatabaseViewer(String title, Connection connection) {
        super(title);
        this.connection = connection;
        
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JTextArea tableView = new JTextArea();
        tableView.setColumns(50);
        tableView.setRows(20);
        tableView.setEditable(false);
        JScrollPane scroll = new JScrollPane(tableView);
        add(scroll, BorderLayout.CENTER);
        JComboBox tableBox = new JComboBox(getTables());
        tableBox.addItemListener(new ItemListener() {
            
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String table = (String) e.getItem();
                    tableView.setText(getTableContents(table));
                }
            }
        });
        add(tableBox, BorderLayout.SOUTH);
        
        pack();
        setLocationRelativeTo(null);
        
        setVisible(true);
    }
    
    /**
     * Secondary constructor that will create JTETRIS Statistic viewer.
     * 
     * @param connection Database connection
     */
    public DatabaseViewer(Connection connection) {
        super("JTETRIS Statistics");
        this.connection = connection;
        
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        JTextArea tableView = new JTextArea();
        tableView.setColumns(50);
        tableView.setRows(20);
        tableView.setEditable(false);
        JScrollPane scroll = new JScrollPane(tableView);
        add(scroll, BorderLayout.CENTER);
        tableView.setText(getTableContents("JTETRIS"));
        
        pack();
        setLocationRelativeTo(null);
        
        setVisible(true);
    }
    
    /**
     * Get all tables held in database.
     * 
     * @return Returns tables
     */
    private String[] getTables() {
        String[] tables = new String[0];
        ArrayList<String> tableNames = new ArrayList<>();
        
        try {
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet res = meta.getTables(null, null, null, new String[] {"TABLE"});
            while (res.next()) {
                tableNames.add(res.getString("TABLE_NAME"));
            }
            res.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseViewer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (String tableName : tableNames) {
            if (tables.length == 0) {
                tables = new String[1];
                tables[0] = tableName;
            } else {
                String[] oldTables = tables;
                tables = new String[tables.length + 1];
                for (int i = 0; i < tables.length; i++) {
                    if (i < oldTables.length)
                        tables[i] = oldTables[i];
                    else
                        tables[i] = tableName;
                }
            }
        }
        
        return tables;
    }
    
    /**
     * Gets all of the data in a specified table.
     * 
     * @param table Database Table
     * @return Data of table
     */
    private String getTableContents(String table) {
        String tableContents = "";
        
        try {
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet res = meta.getColumns(null, null, table, null);
            
            ArrayList<String> columns = new ArrayList<>();
            
            while (res.next()) {
                tableContents += res.getString("COLUMN_NAME") + "\t";
                columns.add(res.getString("COLUMN_NAME"));
            }
            
            Statement statement = connection.createStatement();
            res = statement.executeQuery("select * from " + table);
            
            while (res.next()) {
                tableContents += "\n";
                for (String column : columns) {
                    if (res.getString(column).length() > 10)
                        tableContents += res.getString(column).substring(0, 7) + "...\t";
                    else
                        tableContents += res.getString(column) + "\t";
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseViewer.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return tableContents;
    }
}
