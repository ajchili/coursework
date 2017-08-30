/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.awt.*;
import javax.swing.*;

/**
 * This class contains all the information pertaining to Label and its creation.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see javax.swing.JLabel
 */
public class Label extends JLabel {
    
    /**
     * Main constructor that will create the label by setting the text, text
     * color, and tool tip.
     * 
     * @param text Text
     * @param c Color of text
     * @param toolTipText Tool tip
     */
    public Label(String text, Color c, String toolTipText) {
        super();
                
        setBackground(Color.WHITE);
        setForeground(c);
        setFont(new Font("Times New Roman", Font.BOLD, 20));
        setToolTipText(toolTipText);
        setText(text);
    }
}
