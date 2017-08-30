/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel.gui;

import com.kirinpatel.util.*;
import java.awt.*;
import javax.swing.*;

/**
 * This class is used to display the game to the server or client.
 *
 * @author Kirin Patel
 * @version 1.0
 */
public class GamePane extends JPanel {
    
    private Map map;
    
    /**
     * Main constructor that will create the game pane.
     */
    public GamePane() {
        super();
        
        setPreferredSize(new Dimension(140, getHeight()));
        setMaximumSize(new Dimension(140, getHeight()));
    }
    
    /**
     * This method will paint the GamePane.
     * 
     * @param g Graphics
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        g.setColor(Color.BLACK);
        g.fillRect(20, 18, getWidth() - 20, getHeight() - 60);
        
        g.setColor(Color.CYAN);
        g.fillRect(0, 0, getWidth(), 18);
        g.fillRect(0, 0, 20, getHeight());
        g.fillRect(getWidth() - 20, 0, 20, getHeight());
        g.fillRect(0, getHeight() - 50, getWidth(), 70);
        
        g.setColor(Color.BLUE);
        g.drawString("JTetris", (getWidth() / 2) - (getFontMetrics(getFont()).stringWidth("JTetris") / 2), getHeight() - 25);
        
        drawPieces(g);
    }
    
    /**
     * Draws map to GamePane.
     * 
     * @param g Graphics
     */
    private void drawPieces(Graphics g) {
        if (map != null)
            for (int i = 0; i < 10; i++)
                for (int j = 0; j < 20; j++)
                if (map.getPiece(i, j) != null) {
                        Block block = map.getPiece(i, j);
                        g.setColor(block.getColor());
                        g.fillRect(20 + (23 * i), 18 + (23 * j), 23, 23);
                    }
    }
    
    /**
     * Sets the map of the GamePane.
     * 
     * @param map Map
     */
    public void setMap(Map map) {
        this.map = map;
        repaint();
    }
}
