/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel.util;

import java.io.*;
import java.io.IOException;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 * This class handles database connections for the game.
 *
 * @author Kirin Patel
 * @version 1.0
 */
public class DBConnection {
    
    private Connection connection;
            
    /**
     * Primary constructor that will create a connection for a server to the
     * database.
     */
    public DBConnection() {
        this("localhost");
    }
    
    /**
     * Secondary constructor that will create a connection for a client to the
     * database.
     * 
     * @param ip Database ip address
     */
    public DBConnection(String ip) {
        this.connection = establishDatabaseConnection(ip);
    }
    
    /**
     * Connects GUI client to database.
     * 
     * @return Database connection
     */
    private Connection establishDatabaseConnection(String ip) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException ex) {
            // Error handling
            System.out.println("ERROR: Unable to locate drivers.");
        }
        
        Connection connection = null;
        
        try {
            connection = DriverManager.getConnection("jdbc:derby://" + ip + ":1527/sample", "app", "app");
        } catch (SQLException ex) {
            // Error handling
            JOptionPane.showMessageDialog(null, "Unable to connect to database. - (" + ex.getMessage() + ")");
        }
        
        return connection;
    }
    
    /**
     * Gets whole table from database.
     * 
     * @return Table in ResultSet form
     */
    private ResultSet getTable() {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery("select * from JTETRIS");
        } catch (SQLException ex) {
            // Handle it
            JOptionPane.showMessageDialog(null, "Unable to get table from database. - (" + ex.getMessage() + ")");
        }
        
        return null;
    }
    
    /**
     * Provides a unique id for a new user.
     * 
     * @return Unique user id
     */
    private int getUniqueID() {
        try {
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery("select COUNT(*) AS rowcount from JTETRIS");
            res.next();
            return res.getInt("rowcount") + 1;
        } catch (SQLException ex) {
            // Handle it
            JOptionPane.showMessageDialog(null, "Unable to create user. - (" + ex.getMessage() + ")");
        }
        
        return 1;
    }
    
    /**
     * Provides the statistical data of a user.
     * 
     * @param id User id
     * @return Returns statistics of a given user id
     */
    public ResultSet getStats(int id) {
        try {
            if (connection != null) {
                Statement statement = connection.createStatement();
                return statement.executeQuery("select * from JTETRIS where ID = " + id);
            }
        } catch (SQLException ex) {
            // Handle it
            JOptionPane.showMessageDialog(null, "Unable to get user statistics. - (" + ex.getMessage() + ")");
        }
        
        return null;
    }
    
    /**
     * Creates a new user with a unique id.
     * 
     * @param username Username
     * @param id User id
     * @return Unique user id
     */
    public int createUser(String username, int id) {
        if (doesUserExist()) {
            try {
                DataInputStream in = new DataInputStream(new FileInputStream(new File("./userID.dat")));
                id = in.readInt();
                in.close();
                return id;
            } catch (FileNotFoundException ex) {
                // Handle it
                JOptionPane.showMessageDialog(null, "This is literally impossible to have achieved but you did it.");
            } catch (IOException ex) {
                // Handle it
                JOptionPane.showMessageDialog(null, "Unable to read user data. - (" + ex.getMessage() + ")");
            }
        } else {
            id = getUniqueID();
        
            try {
                DataOutputStream out = new DataOutputStream(new FileOutputStream(new File("./userID.dat")));
                out.writeInt(id);
                out.close();
            } catch (FileNotFoundException ex) {
                // Handle it
                JOptionPane.showMessageDialog(null, "This is literally impossible to have achieved but you did it.");
            } catch (IOException ex) {
                // Handle it
                JOptionPane.showMessageDialog(null, "Unable to write user data. - (" + ex.getMessage() + ")");
            }

            try {
                if (connection != null) {
                    Statement statement = connection.createStatement();
                    statement.executeUpdate("insert into JTETRIS(ID, USERNAME) values('" + id + "', '" + username + "')");
                }
            } catch (SQLException ex) {
                return createUser(username, id + 1);
            }
            return id;
        }
        
        return -1;
    }
    
    /**
     * Provides a user object of the user.
     * 
     * @param id User id
     * @return Returns new user object
     */
    public User getUser(int id) {
        try {
            if (connection != null) {
                Statement statement = connection.createStatement();
                ResultSet res = statement.executeQuery("select * from JTETRIS where ID = '" + id + "'");
                
                id = 1;
                String username = "";
                while(res.next()) {
                    id = res.getInt("ID");
                    username = res.getString("USERNAME");
                }
                return new User(id, username);
            }
        } catch (SQLException ex) {
                // Handle it
                JOptionPane.showMessageDialog(null, "Unable to retrieve user data. - (" + ex.getMessage() + ")");
        }
        
        return null;
    }
    
    /**
     * Updates user statistics.
     * 
     * @param column Specific statistic
     * @param id User id
     */
    public void updateStats(int column, int id) {
        Statement statement = null;
        
        try {
            statement = connection.createStatement();
            ResultSet res = statement.executeQuery("select * from JTETRIS where ID = '" + id + "'");
            
            while(res.next() && !res.isClosed()) {
                switch (column) {
                    case 0:
                        statement.executeUpdate("update JTETRIS set GAMES_PLAYED = " + (res.getInt("GAMES_PLAYED") + 1) + " where ID = '" + id + "'");
                        return;
                    case 1:
                        statement.executeUpdate("update JTETRIS set MOVES_MADE = " + (res.getInt("MOVES_MADE") + 1) + " where ID = '" + id + "'");
                        return;
                    case 2:
                        statement.executeUpdate("update JTETRIS set MOVES_MISSED = " + (res.getInt("MOVES_MISSED") + 1) + " where ID = '" + id + "'");
                        return;
                    case 3:
                        statement.executeUpdate("update JTETRIS set ROWS_COMPLETED = " + (res.getInt("ROWS_COMPLETED") + 1) + " where ID = '" + id + "'");
                        return;
                }
            }
        } catch (SQLException ex) {
            // Handle it
            JOptionPane.showMessageDialog(null, "Unable to update user data. - (" + ex.getMessage() + ")");
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException ex) {
                // Handle it
                JOptionPane.showMessageDialog(null, "Unable to update user data. - (" + ex.getMessage() + ")");
            }
        }
    }
    
    /**
     * Provides if the user id exists locally.
     * 
     * @return Returns if the user id exists locally.
     */
    public boolean doesUserExist() {
        return new File("./userID.dat").exists();
    }
}
