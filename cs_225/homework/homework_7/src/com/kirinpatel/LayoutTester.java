/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.awt.*;
import javax.swing.*;

/**
 * This class will test and display the FlowLayoutPaenl class in a JFrame.
 *
 * @author Kirin Patel
 * @version 1.0
 * @see FlowLayoutPanel
 */
public class LayoutTester extends JFrame {
    
    /**
     * Main constructor that will setup the JFrame for testing.
     * 
     * @param title Title
     */
    public LayoutTester(String title) {
        setTitle(title);
        setSize(300, 400);
        setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        // setLayout(new BorderLayout());
        setMinimumSize(new Dimension(250, 250));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        add(new FlowLayoutPanel(), BorderLayout.SOUTH);
        add(new FlowLayoutPanel(), BorderLayout.CENTER);
        
        setVisible(true);
    }
    
    /**
     * Main method that will be run on start of application.
     * 
     * @param args Main arguments
     */
    public static void main(String[] args) {
        LayoutTester lt = new LayoutTester("Layout Tester");
    }
}
