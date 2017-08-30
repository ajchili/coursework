/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel.graphics;

import java.awt.*;
import javax.swing.*;

/**
 * This class contains all the information pertaining to the DrawPanel class.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see javax.swing.JPanel
 * @see java.awt.Graphics
 */
public class DrawPanel extends JPanel {
    
    private int ballX, ballY;
    
    /**
     * Main constructor that will create the DrawPanel.
     */
    public DrawPanel() {
        super();
    }
    
    /**
     * Paints the DrawPanel. Use repaint() to update DrawPanel.
     * 
     * @param g Graphics
     */
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        g.setColor(Color.WHITE);
        g.drawLine(getWidth() / 2, 10, ballX, ballY);
        g.fillOval(ballX - 15, ballY - 15, 30, 30);
    }
    
    /**
     * Provides the x position of the pendulum.
     * 
     * @return X position of pendulum
     */
    public int getXPos() {
        return ballX;
    }
    
    /**
     * Provides the y position of the pendulum.
     * 
     * @return Y position of pendulum
     */
    public int getYPos() {
        return ballY;
    }
    
    /**
     * Sets the x position of the pendulum.
     * 
     * @param x X position of pendulum
     */
    public void setX(int x) {
        if (x > 0 && x < getWidth()) {
            ballX = x;
        } else {
            ballX = getWidth() / 2;
            System.out.println("ERROR: INVALID X COORDINATE");
        }
    }
    
    /**
     * Sets the y position of the pendulum.
     * 
     * @param y Y position of pendulum
     */
    public void setY(int y) {
        if (y > 0 && y < getHeight()) {
            ballY = y;
        } else {
            ballY = getHeight() / 2;
            System.out.println("ERROR: INVALID Y COORDINATE");
        }
    }
}
