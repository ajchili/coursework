/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.awt.*;
import javax.swing.*;

/**
 * This class contains all information pertaining to ButtonWindow and will test
 * the ButtonWindow and JButton classes.
 * 
 * @author Kirin Patel
 * @version 1.0
 */
public class ButtonWindow extends JFrame {
  
    /**
     * Main constructor that will setup a ButtonWindow.
     * 
     * @param title Title of window
     */
    public ButtonWindow(String title) {
        super(title);
        
        setSize(300, 400);
        setMinimumSize(new Dimension(250, 250));
        // setLayout(new GridLayout(3, 2);
        // setLayout(new BorderLayout(5, 5));
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        
        add(new JButton("North"), BorderLayout.NORTH);
        add(new JButton("South"), BorderLayout.SOUTH);
        add(new JButton("East"), BorderLayout.EAST);
        add(new JButton("West"), BorderLayout.WEST);
        add(new JButton("Center"), BorderLayout.CENTER);
    }
    
    /**
     * Main method that will be run on start of application.
     * 
     * @param args Main arguments
     */
    public static void main(String[] args) {
        ButtonWindow window = new ButtonWindow("Button Example");
    }
}
