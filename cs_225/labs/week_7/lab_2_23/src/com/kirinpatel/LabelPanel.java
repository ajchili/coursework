/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Kirin Patel
 * @verion 1.0
 */
public class LabelPanel extends JPanel {
    
    public LabelPanel() {
        super();
        
        JLabel label = new JLabel("Label");
        ImageIcon icon = new ImageIcon("icon1.png");
        label.setVerticalTextPosition(SwingConstants.TOP);
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setIcon(icon);
        // label.setVerticalAlignment(SwingConstants.TOP);
        // label.setHorizontalAlignment(SwingConstants.CENTER);
        // label.setPreferredSize(new Dimension(300, 250));
        
        setLayout(new BorderLayout());
        
        add(label, BorderLayout.CENTER);
    }
}
