/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.awt.*;
import javax.swing.*;

/**
 * This class will test and display the CalculatorIOPanel and CalculatorButtons
 * class in a JFrame.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see javax.swing.JFrame
 * @see CalculatorIOPanel
 * @see CalculatorButtons
 */
public class Calculator extends JFrame {
    
    /**
     * Main constructor that will setup the JFrame for testing.
     * 
     * @param title Title
     */
    public Calculator(String title) {
        setTitle(title);
        setSize(300, 400);
        setLayout(new GridLayout(2, 1));
        setMinimumSize(new Dimension(250, 250));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        CalculatorIOPanel panel = new CalculatorIOPanel();
        
        add(panel);
        add(new CalculatorButtons(panel));
        
        setVisible(true);
    }
    
    /**
     * Main method.
     * 
     * @param args Main arguments
     */
    public static void main(String[] args) {
        Calculator calc = new Calculator("Calculator");
    }
}
