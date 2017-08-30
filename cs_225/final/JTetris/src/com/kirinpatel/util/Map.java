/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel.util;

/**
 * This class holds Blocks in a 2D Block array to allow for the game to
 * reference the Map. This map is used to calculate how the game will be played.
 * 
 * @author Kirin Patel
 * @version 1.0
 */
public class Map {
    
    private Block[][] map = new Block[10][20];
    
    /**
     * Provides the Map. The returned value is a 2D array of blocks.
     * 
     * @return Returns map
     */
    public Block[][] getMap() {
        return map;
    }
    
    /**
     * Provides the Map in string form. Only blocks that are non-null are
     * provided. This is done to reduce network strain and computer usage when
     * accessing a Map.
     * 
     * @return Returns map in string form
     */
    @Override
    public String toString() {
        String string = "";
        int iteration = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                if (getPiece(i, j) != null) {
                    iteration++;
                    string += "[" + i + "," + j + "," + getPiece(i, j).getType() + "]";
                }
            }
        }
        
        return iteration + string;
    }
    
    /**
     * Sets the Map to the provided string. This method takes in a map of string
     * form and converts it to a Map object.
     * 
     * @param map String form of map
     */
    public void parseMap(String map) {
        clearMap();
        
        int iterations = 0;
        if (map.length() > 2)
            iterations = Integer.parseInt(map.substring(0, map.indexOf("[")));
        if (iterations == 0) return;
        map = map.substring(map.indexOf("["));
        map = map.replace("[", "").replace("]", " ");
        for (int i = 0; i < iterations; i++) {
            String block;
            if (i + 1 != iterations) {
                block = map.substring(0, map.indexOf(" "));
                map = map.substring(block.length() + 1);
            } else
                block = map;
            
            int x = Integer.parseInt(block.substring(0, block.indexOf(",")));
            block = block.substring(block.indexOf(",") + 1);
            int y = Integer.parseInt(block.substring(0, block.indexOf(",")));
            block = block.substring(block.indexOf(",") + 1);
            int type = Integer.parseInt(block.charAt(0) + "");
            addBlock(new Block(x, y, type));
        }
    }
    
    /**
     * Sets Map.
     * 
     * @param map Map
     */
    public void setMap(Map map) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                this.map[i][j] = map.getMap()[i][j];
            }
        }
    }
    
    /**
     * Clears Map.
     */
    public void clearMap() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                map[i][j] = null;
            }
        }
    }
    
    /**
     * Adds fallen object to Map. This method is used once a FallingObject can
     * no longer fall. This will convert the FallingObject to blocks and then
     * add them to the Map.
     * 
     * @param fallingObject FallingObject
     */
    public void addFallingObject(FallingObject fallingObject) {
        for (Block block : fallingObject.getObject()) {
            map[block.getCoords()[0] + fallingObject.getX()][block.getCoords()[1] + fallingObject.getY()] = block;
        }
    }
    
    /**
     * Adds a block to the Map.
     * 
     * @param block New Block
     */
    public void addBlock(Block block) {
        int[] coords = block.getCoords();
        map[coords[0]][coords[1]] = block;
    }
    
    /**
     * Provides the Block at a given location on the map.
     * 
     * @param x X coordinate
     * @param y Y coordinate
     * @return Returns block
     */
    public Block getPiece(int x, int y) {
        return map[x][y];
    }
    
    /**
     * Sets the Block at a given location on the map.
     * 
     * @param block Block
     * @param x X coordinate
     * @param y Y coordinate
     */
    public void setPiece(Block block, int x, int y) {
        map[x][y] = block;
        if (y - 1 != -1)
            map[x][y - 1] = null;
    }
}
