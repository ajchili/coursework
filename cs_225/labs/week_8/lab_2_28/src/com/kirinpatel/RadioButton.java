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
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author Kirin Patel
 * @version 1.0
 */
public class RadioButton extends JFrame {
    
    public static JTextField major; 
    
    public RadioButton(String title) {
        super(title);
        
        setSize(640, 480);
        setLayout(new GridLayout(3, 1));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JRadioButton cs = new JRadioButton("CS");
        JRadioButton ia = new JRadioButton("IA");
        major = new JTextField(10);
        
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(cs);
        buttonGroup.add(ia);
        
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
        RadioButton window = new RadioButton("RadioButton GUI");
    }
    
    public class MajorListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            JRadioButton rButton = (JRadioButton) e.getSource();
            String major = rButton.getText();
            if (e.getStateChange() == ItemEvent.SELECTED) {
                RadioButton.major.setText(major + " selected...");
            }
        }
        
    }
}
