/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Kirin Patel
 * @version 1.0
 */
public class TextArea extends JFrame {
    
    public TextArea(String title) {
        super(title);
        
        setSize(640, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        
        JTextArea input = new JTextArea(10, 10);
        // input.setLineWrap(true);
        // input.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(input);
        
        add(scroll);
        
        setVisible(true);
    }
    
    public static void main(String[] args) {
        TextArea window = new TextArea("TextArea GUI");
    }
}
