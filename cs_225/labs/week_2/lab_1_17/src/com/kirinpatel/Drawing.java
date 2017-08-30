/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.awt.*;
import javax.swing.*;

/**
 * This class is a container for drawing to a JFrame.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see javax.swing.JFrame;
 */
public class Drawing extends JFrame {
    
    /**
     * Displays graphics to the screen.
     * 
     * @param g Graphics
     */
    public void paint(Graphics g) {
        super.paint(g);
        
        g.drawOval(this.getX() + (this.getWidth() / 4), this.getY() + (this.getHeight() / 4), this.getWidth() / 2, this.getWidth() / 2);
    }
    
}
