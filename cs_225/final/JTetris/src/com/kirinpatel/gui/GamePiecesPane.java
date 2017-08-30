/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel.gui;

import com.kirinpatel.util.GameObject;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * This class displays the next three pieces to the server or client while the
 * game is active.
 *
 * @author Kirin Patel
 * @version 1.0
 */
public class GamePiecesPane extends JPanel {
    
    private boolean isGameRunning = false;
    private ArrayList<GameObject> pieces = new ArrayList<>();
    
    /**
     * Main constructor that will create the GamePiecesPane.
     */
    public GamePiecesPane() {
        super();
        
        setPreferredSize(new Dimension(140, getHeight() / 3));
    }
    
    /**
     * This method will paint the GamePiecesPane.
     * 
     * @param g Graphics g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        g.setColor(Color.WHITE);
        g.drawString("Next Piece", (getWidth() / 2) - (getFontMetrics(getFont()).stringWidth("Next Piece") / 2), 15);
        
        for (int i = 0; i < pieces.size(); i++) {
            pieces.get(i).setCoords(10 + (i * (getWidth() / 3)), (getHeight() - 10) / 2);
            pieces.get(i).drawNextObject(g);
        }
    }
    
    /**
     * Sets the game pieces.
     * 
     * @param pieces Game pieces
     */
    public void setGamePieces(ArrayList<GameObject> pieces) {
        this.pieces = pieces;
        repaint();
    }
}
