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
public class Button extends JFrame {
    
    public static JTextField major; 
    
    public Button(String title) {
        super(title);
        
        setSize(640, 480);
        setLayout(new GridLayout(3, 1));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JCheckBox cs = new JCheckBox("CS");
        JCheckBox ia = new JCheckBox("IA");
        major = new JTextField(10);
        
        cs.addItemListener(new MajorListener());
        ia.addItemListener(new MajorListener());
        
        add(cs);
        add(ia);
        add(major);
        
        pack();
        setLocationRelativeTo(null);
        
        setVisible(true);
    }
    
    public static void main(String[] args) {
        Button window = new Button("Button GUI");
    }
    
    public class MajorListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            JCheckBox cBox = (JCheckBox) e.getSource();
            String major = cBox.getText();
            if (e.getStateChange() == ItemEvent.SELECTED) {
                Button.major.setText(major + " selected...");
            }
        }
        
    }
}
