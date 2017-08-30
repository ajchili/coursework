/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * This class contains all the information pertaining to Button and its
 * construction.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see javax.swing.JButton
 */
public class Button extends JButton {
    
    /**
     * Main constructor that will create a button that will change from one
     * specified icon to another.
     * 
     * @param icon1 Starting icon
     * @param icon2 Ending icon
     */
    public Button(Icon icon1, final Icon icon2) {
        super();
        
        setIcon(icon1);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setIcon(icon2);
            }
      });
    }
    
}
