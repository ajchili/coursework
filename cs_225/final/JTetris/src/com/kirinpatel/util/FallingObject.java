/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel.util;

import java.util.ArrayList;

/**
 * This class serves as the secondary game piece.
 *
 * @author Kirin Patel
 * @version 1.0
 */
public class FallingObject {
    
    private int x;
    private int y;
    private int width = 0;
    private int height = 0;
    private GameObject gameObject;
    private ArrayList<Block> object;
    
    /**
     * Primary constructor that creates a FallingObject from a GameObject.
     * 
     * @param gameObject GameObject
     */
    public FallingObject(GameObject gameObject) {
        this.gameObject = gameObject;
        this.object = this.gameObject.getAsBlock();
        calculateBounds();
        calculateLocation();
    }
    
    /**
     * Calculates FallingObject location.
     */
    private void calculateLocation() {
        this.x = gameObject.getCoords()[0];
        this.y = gameObject.getCoords()[1];
    }
    
    /**
     * Calculates the bounds of a FallingObject.
     */
    private void calculateBounds() {
        for (Block block : object) {
            int[] coords = block.getCoords();
            if (coords[0] > width) width = coords[0];
            if (coords[1] > height) height = coords[1];
        }
        
        width++;
        height++;
    }
    
    /**
     * Provides X coordinate.
     * 
     * @return Returns x location
     */
    public int getX() {
        return x;
    }
    
    /**
     * Provides Y coordinate.
     * 
     * @return Returns y location
     */
    public int getY() {
        return y;
    }
    
    /**
     * Provides FallingObject type.
     * 
     * @return Returns type
     */
    public int getType() {
        return gameObject.getType();
    }
    
    /**
     * Provides FallingObject width.
     * 
     * @return Returns width
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * Provides FallingObject height.
     * 
     * @return Returns height
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * Provides FallingObject as Blocks.
     * 
     * @return Returns an ArrayList of Blocks to represent the FallingObject
     */
    public ArrayList<Block> getObject() {
        return object;
    }
    
    /**
     * Determines if the FallingObject can fall.
     * 
     * @param map Current map
     * @return Returns if FallingObject can fall
     */
    public boolean canFall(Block[][] map) {
        for (Block block : object)
            if (block.getCoords()[1] > 0) {
                if (y + block.getCoords()[1] + 1 > 19)
                    return false;
                else if (y + block.getCoords()[1] == 19)
                    return false;
                else if (map[x + block.getCoords()[0]][y + block.getCoords()[1] + 1] != null)
                    return false;
            } else {
                if (getType() == 1 && (gameObject.getRotation() == 1 || gameObject.getRotation() == 3)) {
                    if (y + block.getCoords()[1] + 1 > 19)
                        return false;
                    else if (y + block.getCoords()[1] == 19)
                        return false;
                    else if (map[x + block.getCoords()[0]][y + block.getCoords()[1] + 1] != null)
                        return false;
                }
            }
        
        return true;
    }
    
    /**
     * Determines if FallingObject can move.
     * 
     * @param movementType Movement type
     * @param map Map
     * @return Returns if FallingObject can move
     */
    public boolean checkIfMoveIsPossible(int movementType, Block[][] map) {
        for (Block block : object) 
            if (movementType == 0) {
                if (block.getCoords()[0] == 0)
                    if (x <= 0)
                        return false;
                    else if (map[x + block.getCoords()[0] - 1][y + block.getCoords()[1]] != null)
                        return false;
            } else {
                if (block.getCoords()[0] == width - 1)
                    if (x + block.getCoords()[0] >= 9)
                        return false;
                    else if (map[x + block.getCoords()[0] + 1][y + block.getCoords()[1]] != null)
                        return false;
            }
        
        return true;
    }
    
    /**
     * Determines if FallingObject can rotate.
     * 
     * @param rotationType Rotation type
     * @param map Map
     * @return Returns if FallingObject can rotate
     */
    public boolean checkIfRotateIsPossible(int rotationType, Block[][] map) {
        GameObject testGameObject = new GameObject(gameObject.getCoords()[0], gameObject.getCoords()[1], getType());
        
        if (rotationType == 0) {
            testGameObject.rotateObjectLeft();
        } else {
            testGameObject.rotateObjectRight();
        }
        
        FallingObject testRotation = new FallingObject(testGameObject);
        
        for (Block block : testRotation.getObject()) {
            if (block.getCoords()[0] < 0 || block.getCoords()[1] < 0) {
                return false;
            } else if (testRotation.getX() + block.getCoords()[0] > 9 || testRotation.getY() + block.getCoords()[1] > 19) {
                return false;
            } else if (map[testRotation.getX() + block.getCoords()[0]][testRotation.getY() + block.getCoords()[1]] != null) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Sets coordinates of FallingObject.
     * 
     * @param x X position
     * @param y Y position
     */
    public void setCoords(int x, int y) {
        if (x > 9) this.x = 9;
        else if (x < 0) this.x = 0;
        else this.x = x;
        if (y > 19) this.y = 19;
        else if (y < 0) this.y = 0;
        else this.y = y;
    }
}
