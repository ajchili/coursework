/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * This class contains all the information pertaining to CalculatorButtons and
 * its construction.
 *
 * @author Kirin Patel
 * @version 1.0
 * @see javax.swing.JPanel
 */
public class CalculatorButtons extends JPanel {
    
    private JButton add, subtract, multiply, divide;
    
    /**
     * Main constructor that will setup the button panel of the calculator.
     * 
     * @param panel CalculatorIOPanel object
     */
    public CalculatorButtons(final CalculatorIOPanel panel) {
        super();
        
        setLayout(new GridLayout(1, 4, 5, 5));
        
        add = new JButton("+");
        subtract = new JButton("-");
        multiply = new JButton("*");
        divide = new JButton("/");
        add(add);
        add(subtract);
        add(multiply);
        add(divide);
        
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setProduct(panel.getNumber(1) + panel.getNumber(2));
            }
        });
        
        subtract.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setProduct(panel.getNumber(1) - panel.getNumber(2));
            }
        });
        
        multiply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setProduct(panel.getNumber(1) * panel.getNumber(2));
            }
        });
        
        divide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setProduct(panel.getNumber(1) / panel.getNumber(2));
            }
        });
    }
}
