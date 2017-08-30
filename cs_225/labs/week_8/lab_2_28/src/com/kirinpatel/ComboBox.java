/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;

/**
 *
 * @author Kirin Patel
 * @version 1.0
 */
public class ComboBox extends JFrame {
    
    public static JTextField major; 
    
    private String[] MAJORS = {"CS", "IA", "EE"};
    
    public ComboBox(String title) {
        super(title);
        
        setSize(640, 480);
        setLayout(new GridLayout(2, 1));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JComboBox majorBox = new JComboBox(MAJORS);
        major = new JTextField(10);
        
        majorBox.addItemListener(new MajorListener());
        
        add(majorBox);
        add(major);
        
        pack();
        setLocationRelativeTo(null);
        
        setVisible(true);
    }
    
    public static void main(String[] args) {
        ComboBox window = new ComboBox("ComboBox GUI");
    }
    
    public class MajorListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            major.setText((String) e.getItem() + " is selected...");
        }
        
    }
}

