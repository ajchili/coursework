/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Kirin Patel
 * @version 1.0
 */
public class LabelAndTextFieldWindow extends JFrame {
    /**
     * Main constructor that will setup the LabelAndTextFieldWindow.
     * 
     * @param title Title
     */
    public LabelAndTextFieldWindow(String title) {
        setTitle(title);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        add(new LabelPanel(), BorderLayout.CENTER);
        
        setVisible(true);
    }
    
    /**
     * Main method.
     * 
     * @param args Main arguments
     */
    public static void main(String[] args) {
        LabelAndTextFieldWindow window = new LabelAndTextFieldWindow("Label and TextField");
        // window.setSize(40, 40);
        window.pack();
        
        /* JFrame other = new JFrame("Other");
        other.setLocationRelativeTo(window);
        other.setVisible(true); */
    }
}
