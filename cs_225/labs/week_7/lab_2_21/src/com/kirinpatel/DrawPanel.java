/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.awt.*;
import javax.swing.*;

/**
 * This class contains all the information pertaining to the Draw Panel class.
 * 
 * @author Kirin Patel
 * @version 1.0
 */
public class DrawPanel extends JPanel {
    
    private Color c;
    
    /**
     * Main constructor that will create the DrawPanel and set the color that  
     * it will use to draw.
     * 
     * @param c Color
     */
    public DrawPanel(Color c) {
       super();
       
       setColor(c);
    }
    
    /**
     * Displays given graphics to the screen.
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        g.setColor(c);
        g.drawLine(0, 0, getWidth(), getHeight());
        g.drawLine(getWidth(), 0, 0, getHeight());
    }
    
    /**
     * Sets the color that will be used to draw.
     * 
     * @param c Color
     */
    public void setColor(Color c) {
        this.c = c;
    }
    
    /**
     * Provides the color that is being used to draw.
     * 
     * @return Color
     */
    public Color getColor() {
        return c;
    }
}
