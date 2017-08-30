/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.awt.*;
import javax.swing.*;

/**
 * This class contains all information pertaining to GUIWindow and will test
 * the GUIWindow and DrawPanel classes.
 *
 * @author Kirin Patel
 * @version 1.0
 */
public class GUIWindow extends JFrame {
  
    /**
     * Main constructor that will setup a GUIWindow.
     * 
     * @param title Title of window
     */
    public GUIWindow(String title) {
        super(title);
        
        setSize(300, 400);
        setMinimumSize(new Dimension(100, 100));
        setLayout(new GridLayout(3, 2));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        
        add(new DrawPanel(Color.BLACK));
        add(new DrawPanel(Color.GRAY));
        add(new DrawPanel(Color.BLUE));
        add(new DrawPanel(Color.GREEN));
        add(new DrawPanel(Color.RED));
        add(new DrawPanel(Color.ORANGE));
    }
    
    /**
     * Main method that will be run on start of application.
     * 
     * @param args Main arguments
     */
    public static void main(String[] args) {
        GUIWindow window = new GUIWindow("GUI Example");
    }
}
