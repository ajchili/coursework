/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

/**
 * This class is a tester for the Drawing class.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see com.kirinpatel.Drawing
 */
public class GuiTester {
    
    /**
     * Tests the Drawing class.
     * 
     * 
     * @param args Main argument
     */
    public static void main(String[] args) {
        Drawing d = new Drawing();
        
        d.setTitle("Drawing a circle");
        d.setSize(500, 500);
        d.setResizable(true);
        d.setDefaultCloseOperation(d.EXIT_ON_CLOSE);
        
        d.setVisible(true);
    }
    
}
