/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel.util;

/**
 * This class serves as a interpolation of the class GameObject on a the
 * GamePane.
 *
 * @author Kirin Patel
 * @version 1.0
 */
public class Block extends GameObject {
    
    /**
     * Primary constructor that creates the block at a given location based upon
     * specified coordinates.
     * 
     * @param x X position
     * @param y Y position
     * @param type Block type
     */
    public Block(int x, int y, int type) {
        super(x, y, type);
    }
}
