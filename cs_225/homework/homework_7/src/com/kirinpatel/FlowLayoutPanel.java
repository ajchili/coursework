/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.awt.*;
import javax.swing.*;

/**
 * This class contains all the information pertaining to FlowLayoutPanel and its
 * setup.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see javax.swing.JPanel;
 */
public class FlowLayoutPanel extends JPanel {
    
    /**
     * Main constructor that will create the JPanel.
     */
    public FlowLayoutPanel() {
        super();
        
        /* setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        add(new JButton("Button 1"));
        add(new JButton("Button 2"));
        add(new JButton("Button 3")); */
        
        setLayout(new GridLayout(2, 3, 5, 5));
        add(new JLabel("1"));
        add(new JLabel("2"));
        add(new JLabel("3"));
        add(new JLabel("4"));
        add(new JLabel("5"));
        add(new JLabel("6"));
    }
}
