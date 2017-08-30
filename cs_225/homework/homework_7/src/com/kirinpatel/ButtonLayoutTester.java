/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.awt.*;
import javax.swing.*;

/**
 * This class will test and display the Button class in a JFrame.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see Button
 */
public class ButtonLayoutTester extends JFrame {
    
    /**
     * Main constructor that will setup the JFrame for testing.
     * 
     * @param title Title
     */
    public ButtonLayoutTester(String title) {
        setTitle(title);
        setSize(300, 400);
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(250, 250));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        add(new Button(new ImageIcon("icon1.png"), new ImageIcon("icon2.png")), BorderLayout.NORTH);
        add(new Button(new ImageIcon("icon1.png"), new ImageIcon("icon2.png")), BorderLayout.SOUTH);
        add(new Button(new ImageIcon("icon1.png"), new ImageIcon("icon2.png")), BorderLayout.EAST);
        add(new Button(new ImageIcon("icon1.png"), new ImageIcon("icon2.png")), BorderLayout.WEST);
        add(new Button(new ImageIcon("icon1.png"), new ImageIcon("icon2.png")), BorderLayout.CENTER);
        
        setVisible(true);
    }
    
    /**
     * Main method.
     * 
     * @param args Main arguments
     */
    public static void main(String[] args) {
        ButtonLayoutTester blt = new ButtonLayoutTester("Button");
    }
}
