/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import com.kirinpatel.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Main method that will run the game.
 *
 * @author Kirin Patel
 * @version 1.0
 */
public class Main extends JFrame {
    
    public static final int GAME_TICK = 1500;
    
    /**
     * Main constructor that will create launcher window.
     * 
     * @param title Title
     */
    public Main(String title) {
        super(title);
        
        setSize(200, 100);
        setResizable(false);
        setLayout(new GridLayout(1, 2));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JButton host = new JButton("Host");
        host.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // Launch server
                new Server("JTetris - Server");
                dispose();
            }
        });
        add(host);
        JButton join = new JButton("Join");
        join.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // Launch client
                String ip = JOptionPane.showInputDialog(null, "IP Address:", "Server IP Addresss", JOptionPane.QUESTION_MESSAGE);
                
                if (ip != null) {
                    if (!ip.equals(""))
                        new Client("JTetris - Client", ip);
                        dispose();
                } else
                    JOptionPane.showMessageDialog(null, "Invalid IP Addres.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(join);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    /**
     * Main method.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        new Main("JTetris");
    }
}
