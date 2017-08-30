/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel.util;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class serves as the main game piece.
 *
 * @author Kirin Patel
 * @version 1.0
 */
public class GameObject {
    
    private static final int WIDTH = 23;
    private static final int HEIGHT = 23;
    private static final int SIZE = 10;
    private static final Color[] COLOR = { Color.YELLOW, Color.CYAN, new Color(138, 43, 226), Color.GREEN, Color.RED, Color.BLUE, Color.ORANGE};
    private Random random = new Random();
    private int x, y, type;
    private int rotation = 0;
    
    /**
     * Main constructor that creates a GameObject.
     * 
     * @param x X position
     * @param y Y position
     */
    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
        type = random.nextInt(7);
    }
    
    /**
     * Secondary constructor that creates a GameObject.
     * 
     * @param x X position
     * @param y Y position
     * @param type Piece type
     * 
     * Position of pieces are determined by their top left corner.
     * 
     * Types of pieces.
     * 0. **
     *    **
     * 
     * 1. *
     *    *
     *    *
     *    *
     * 
     * 2.  *
     *    ***
     * 
     * 3.  **
     *    **
     * 
     * 4. **
     *     **
     * 
     * 5. *
     *    ***
     * 
     * 6.   *
     *    ***
     */
    public GameObject(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }
    
    /**
     * Provides GameObject in string form.
     * 
     * @return Returns printable form of GameObject
     */
    @Override
    public String toString() {
        return "[" + x + "," + y + "," + type + "]";
    }
    
    /**
     * Parses a given string into a GameObject.
     * 
     * @param gameObject String version of GameObject
     */
    public void parseGameObject(String gameObject) {
        gameObject = gameObject.substring(1, gameObject.length() - 1);
        int x = Integer.parseInt(gameObject.substring(0, gameObject.indexOf(',')));
        gameObject = gameObject.substring(gameObject.indexOf(','), gameObject.length());
        int y = Integer.parseInt(gameObject.substring(0, gameObject.indexOf(',')));
        gameObject = gameObject.substring(gameObject.indexOf(','), gameObject.length());
        int type = Integer.parseInt(gameObject);
        setCoords(x, y);
        setType(type);
    }
    
    /**
     * Sets GameObject coordinates.
     * 
     * @param x X position
     * @param y Y position
     */
    public void setCoords(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Sets GameObject type.
     * 
     * @param type Type
     */
    public void setType(int type) {
        this.type = type;
    }
    
    /**
     * Provides GameObject location
     * 
     * @return Returns array of coordinates (x, y)
     */
    public int[] getCoords() {
        int[] coords = {x, y};
        return coords;
    }
    
    /**
     * Provides GameObject type.
     * 
     * @return Returns type
     */
    public int getType() {
        return type;
    }
    
    /**
     * Provides GameObject rotation.
     * 
     * @return Returns rotation
     */
    public int getRotation() {
        return rotation;
    }
    
    /**
     * Provides GameObject color.
     * 
     * @return Returns color
     */
    public Color getColor() {
        return COLOR[type];
    }
    
    /**
     * Provides the GameObjecg as Blocks.
     * 
     * @return Returns an ArrayList of Blocks
     */
    public ArrayList<Block> getAsBlock() {
        ArrayList<Block> blocks = new ArrayList<>();
        switch(type) {
            case 0:
                blocks.add(new Block(0, 0, 0));
                blocks.add(new Block(0, 1, 0));
                blocks.add(new Block(1, 0, 0));
                blocks.add(new Block(1, 1, 0));
                break;
            case 1:
                if (rotation == 0 || rotation == 2) {
                    blocks.add(new Block(0, 0, 1));
                    blocks.add(new Block(0, 1, 1));
                    blocks.add(new Block(0, 2, 1));
                    blocks.add(new Block(0, 3, 1));
                } else {
                    blocks.add(new Block(0, 0, 1));
                    blocks.add(new Block(1, 0, 1));
                    blocks.add(new Block(2, 0, 1));
                    blocks.add(new Block(3, 0, 1));
                }
                break;
            case 2:
                switch (rotation) {
                    case 0:
                        blocks.add(new Block(1, 0, 2));
                        blocks.add(new Block(0, 1, 2));
                        blocks.add(new Block(1, 1, 2));
                        blocks.add(new Block(2, 1, 2));
                        break;
                    case 1:
                        blocks.add(new Block(1, 1, 2));
                        blocks.add(new Block(0, 0, 2));
                        blocks.add(new Block(0, 1, 2));
                        blocks.add(new Block(0, 2, 2));
                        break;
                    case 2:
                        blocks.add(new Block(1, 1, 2));
                        blocks.add(new Block(0, 0, 2));
                        blocks.add(new Block(1, 0, 2));
                        blocks.add(new Block(2, 0, 2));
                        break;
                    case 3:
                        blocks.add(new Block(0, 1, 2));
                        blocks.add(new Block(1, 0, 2));
                        blocks.add(new Block(1, 1, 2));
                        blocks.add(new Block(1, 2, 2));
                        break;
                }
                break;
            case 3:
                if (rotation == 0 || rotation == 2) {
                    blocks.add(new Block(1, 0, 3));
                    blocks.add(new Block(2, 0, 3));
                    blocks.add(new Block(0, 1, 3));
                    blocks.add(new Block(1, 1, 3));
                } else {
                    blocks.add(new Block(1, 0, 3));
                    blocks.add(new Block(1, 1, 3));
                    blocks.add(new Block(0, 1, 3));
                    blocks.add(new Block(0, 2, 3));
                }
                break;
            case 4:
                if (rotation == 0 || rotation == 2) {
                    blocks.add(new Block(0, 0, 4));
                    blocks.add(new Block(1, 0, 4));
                    blocks.add(new Block(1, 1, 4));
                    blocks.add(new Block(2, 1, 4));
                } else {
                    blocks.add(new Block(1, 0, 4));
                    blocks.add(new Block(1, 1, 4));
                    blocks.add(new Block(0, 1, 4));
                    blocks.add(new Block(0, 2, 4));
                }
                break;
            case 5:
                switch (rotation) {
                    case 0:
                        blocks.add(new Block(0, 0, 5));
                        blocks.add(new Block(0, 1, 5));
                        blocks.add(new Block(1, 1, 5));
                        blocks.add(new Block(2, 1, 5));
                        break;
                    case 1:
                        blocks.add(new Block(1, 0, 5));
                        blocks.add(new Block(0, 0, 5));
                        blocks.add(new Block(0, 1, 5));
                        blocks.add(new Block(0, 2, 5));
                        break;
                    case 2:
                        blocks.add(new Block(2, 1, 5));
                        blocks.add(new Block(0, 0, 5));
                        blocks.add(new Block(1, 0, 5));
                        blocks.add(new Block(2, 0, 5));
                        break;
                    case 3:
                        blocks.add(new Block(0, 2, 5));
                        blocks.add(new Block(1, 0, 5));
                        blocks.add(new Block(1, 1, 5));
                        blocks.add(new Block(1, 2, 5));
                        break;
                }
                break;
            case 6:
                switch (rotation) {
                    case 0:
                        blocks.add(new Block(2, 0, 6));
                        blocks.add(new Block(0, 1, 6));
                        blocks.add(new Block(1, 1, 6));
                        blocks.add(new Block(2, 1, 6));
                        break;
                    case 1:
                        blocks.add(new Block(1, 2, 6));
                        blocks.add(new Block(0, 0, 6));
                        blocks.add(new Block(0, 1, 6));
                        blocks.add(new Block(0, 2, 6));
                        break;
                    case 2:
                        blocks.add(new Block(0, 1, 6));
                        blocks.add(new Block(0, 0, 6));
                        blocks.add(new Block(1, 0, 6));
                        blocks.add(new Block(2, 0, 6));
                        break;
                    case 3:
                        blocks.add(new Block(0, 0, 6));
                        blocks.add(new Block(1, 0, 6));
                        blocks.add(new Block(1, 1, 6));
                        blocks.add(new Block(1, 2, 6));
                        break;
                }
                break;
        }
        
        return blocks;
    }
    
    /**
     * Draws the GameObject as a 'Next Object'. This will display the GameObject
     * in a smaller form.
     * 
     * @param g Graphics
     */
    public void drawNextObject(Graphics g) {
        g.setColor(COLOR[type]);
        switch(type) {
            case 0:
                g.fillRect(x, y, SIZE * 2, SIZE * 2);
                break;
            case 1:
                g.fillRect(x, y, SIZE, SIZE * 4);
                break;
            case 2:
                g.fillRect(x + SIZE, y, SIZE, SIZE);
                g.fillRect(x, y + SIZE, SIZE * 3, SIZE);
                break;
            case 3:
                g.fillRect(x + SIZE, y, SIZE * 2, SIZE);
                g.fillRect(x, y + SIZE, SIZE * 2, SIZE);
                break;
            case 4:
                 g.fillRect(x, y, SIZE * 2, SIZE);
                 g.fillRect(x + SIZE, y + SIZE, SIZE * 2, SIZE);
                 break;
            case 5:
                g.fillRect(x, y, SIZE, SIZE);
                g.fillRect(x, y + SIZE, SIZE * 3, SIZE);
                break;
            case 6:
                g.fillRect(x + (SIZE * 2), y, SIZE, SIZE);
                g.fillRect(x, y + SIZE, SIZE * 3, SIZE);
                break;
     
        }
    }
    
    /**
     * This will rotate the object left.
     */
    public void rotateObjectLeft() {
        if (rotation > 0)
            rotation--;
        else
            rotation = 3;
    }
    
    /**
     * This will rotate the object right.
     */
    public void rotateObjectRight() {
        if (rotation < 3)
            rotation++;
        else
            rotation = 0;
    }
}
