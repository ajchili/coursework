/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Kirin Patel
 * @version 1.0
 */
public class List extends JFrame {
    
    public static JTextField major; 
    
    private String[] MAJORS = {"CS", "IA", "EE"};
    
    public List(String title) {
        super(title);
        
        setSize(300, 400);
        setLayout(new GridLayout(2, 1));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JList list = new JList(MAJORS);
        list.setVisibleRowCount(2);
        JScrollPane scroll = new JScrollPane(list);
        list.addListSelectionListener(new ListListener());
        major = new JTextField(10);
        
        add(scroll);
        add(major);
        
        pack();
        
        setLocationRelativeTo(null);
        
        setVisible(true);
    }
    
    public static void main(String[] args) {
        List window = new List("List GUI");
    }
    
    public class ListListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            JList list = (JList) e.getSource();
            major.setText(MAJORS[list.getSelectedIndex()] + " is selected...");
        }
    }
}
