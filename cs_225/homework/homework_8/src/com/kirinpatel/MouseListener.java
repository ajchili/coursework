/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.*;

/**
 * This class will create a GUI that displays a JPanel. This JPanel has a
 * rectangle drawn in it. This rectangle is used to visualize the mouse
 * detection, if the mouse is or is not inside of the region.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see javax.swing.JPanel
 * @see java.awt.Color
 * @see java.awt.event.MouseEvent
 * @see java.awt.event.MouseMotionListener
 */
public class MouseListener extends JFrame {
    
    /**
     * Creates MouseListener GUI.
     * 
     * @param title Title
     */
    public MouseListener(String title) {
        super(title);
        
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        DrawPanel drawPanel = new DrawPanel(Color.RED);
        drawPanel.addMouseMotionListener(new MousePointerListener());
        add(drawPanel);
        
        setVisible(true);
    }
    
    /**
     * Main method.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        MouseListener window = new MouseListener("Mouse Listener");
    }
    
    /**
     * Custom MouseMotionListener.
     */
    public class MousePointerListener implements MouseMotionListener {

        /**
         * Unused method.
         * 
         * @param e MouseEvent
         */
        @Override
        public void mouseDragged(MouseEvent e) {
            
        }

        /**
         * Determines if mouse is inside or outside of rectangle.
         * 
         * @param e MouseEvent
         */
        @Override
        public void mouseMoved(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            
            if (x >= 50 && x <= 150) {
                if (y >= 40 && y <= 80) {
                    System.out.println("Inside of rectangle.");
                } else {
                    System.out.println("Outside of rectangle.");
                }
            } else {
                System.out.println("Outside of rectangle.");
            }
        }
    }
}
