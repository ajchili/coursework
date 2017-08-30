/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Kirin Patel
 * @version 1.0
 */
public class ActionButton extends JFrame {
    
    private JTextField textField;
    
    public ActionButton(String title) {
        setTitle(title);
        setSize(300, 400);
        setLayout(new FlowLayout());
        setMinimumSize(new Dimension(250, 250));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JButton button = new JButton("GO!");
        textField = new JTextField(20);
        button.addActionListener(new GoButtonListener());
        textField.addActionListener(new GoButtonListener());
        add(textField);
        add(button);
        
        pack();
        
        setVisible(true);
    }
    
    public class GoButtonListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            System.out.println("Button pushed! " + textField.getText());
        }
    }
    
    public static void main(String[] args) {
        ActionButton window = new ActionButton("ActionButton Window");
    }
}
