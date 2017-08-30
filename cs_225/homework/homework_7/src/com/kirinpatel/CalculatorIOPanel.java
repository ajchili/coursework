/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.awt.*;
import javax.swing.*;

/**
 * This class contains all the information pertaining to CalculatorIOPanel and
 * its construction.
 *
 * @author Kirin Patel
 * @version 1.0
 * @see javax.swing.JPanel
 */
public class CalculatorIOPanel extends JPanel {
    
    private TextField number1, number2, product;
    
    /**
     * Main constructor that will setup the input/output panel of the
     * calculator.
     */
    public CalculatorIOPanel() {
        super();
        
        setLayout(new GridLayout(2, 3, 25, 5));
        add(new JLabel("Number 1"));
        add(new JLabel("Number 2"));
        add(new JLabel("Product"));
        
        number1 = new TextField();
        number2 = new TextField();
        product = new TextField();
        
        add(number1);
        add(number2);
        add(product);
    }
    
    /**
     * Provides the number inside of the specified TextField.
     * 
     * @param number TextField number
     * @return Value of TextField
     */
    public double getNumber(int number) {
        switch(number) {
            case 1:
                return Double.parseDouble(number1.getText().toString());
            case 2:
                return Double.parseDouble(number2.getText().toString());
            default:
                return 0.0;
        }
    }
    
    /**
     * Sets the product TextField value.
     * 
     * @param product Value
     */
    public void setProduct(double product) {
        this.product.setText("" + product);
    }
}
