/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
 * @see javax.swing.JPanel
 * @see java.awt.Color
 * @see java.awt.Graphics
 */
public class DrawPanel extends JPanel {
    
    private Color color;
    
    /**
     * Creates DrawPanel.
     * 
     * @param color Color used to draw
     */
    public DrawPanel(Color color) {
        super();
        
        setColor(color);
    }
    
    /**
     * Paints rectangle to panel, this rectangle is 100x40 and is centered at
     * the point (100, 60).
     * 
     * @param g Graphics
     */
    @Override
    public void paint(Graphics g) {
        g.setColor(color);
        g.fillRect(50, 40, 100, 40);
    }
    
    /**
     * Provides the current color that is being used to draw.
     * 
     * @return Returns current color
     */
    public Color getColor() {
        return color;
    }
    
    /**
     * Sets the current color that is being used to draw.
     * 
     * @param color New color to be used to draw
     */
    public void setColor(Color color) {
        this.color = color;
    }
}
