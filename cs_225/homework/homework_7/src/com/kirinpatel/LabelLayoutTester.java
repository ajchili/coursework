/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.awt.*;
import javax.swing.*;

/**
 * This class will test and display the Label class in a JFrame.
 *
 * @author Kirin Patel
 * @version 1.0
 * @see Label
 */
public class LabelLayoutTester extends JFrame {
    
    /**
     * Main constructor that will setup the JFrame for testing.
     * 
     * @param title Title
     */
    public LabelLayoutTester(String title) {
        setTitle(title);
        setSize(300, 400);
        setLayout(new GridLayout(3, 2));
        setMinimumSize(new Dimension(250, 250));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        add(new Label("Label 1", Color.black, "black"));
        add(new Label("Label 2", Color.blue, "blue"));
        add(new Label("Label 3", Color.cyan, "cyan"));
        add(new Label("Label 4", Color.green, "green"));
        add(new Label("Label 5", Color.magenta, "magenta"));
        add(new Label("Label 6", Color.orange, "orange"));
        
        setVisible(true);
    }
    
    /**
     * Main method.
     * 
     * @param args Main arguments
     */
    public static void main(String[] args) {
        LabelLayoutTester llt = new LabelLayoutTester("Labels");
    }
}
