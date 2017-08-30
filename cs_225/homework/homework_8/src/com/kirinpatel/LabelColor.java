/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This class will create a GUI that displays a JLabel. This JLabel can have its
 * foreground and background changed by sliders on screen.
 *
 * @author Kirin Patel
 * @version 1.0
 * @see javax.swing.JFrame
 * @see javax.swing.JLabel
 * @see javax.swing.JScrollBar
 * @see javax.swing.JPanel
 * @see javax.swing.border.TitledBorder
 * @see java.awt.GridLayout
 * @see java.awt.BorderLayout
 * @see javax.swing.event.ChangeEvent
 * @see javax.swing.event.ChangeListener
 */
public class LabelColor extends JFrame {
    
    private static JLabel text;
    private static JSlider foregroundRed, foregroundGreen, foregroundBlue;
    private static JSlider backgroundRed, backgroundGreen, backgroundBlue;
    
    /**
     * Creates LabelColor GUI.
     * 
     * @param title Title
     */
    public LabelColor(String title) {
        super(title);
        
        setLayout(new GridLayout(3, 1));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JPanel textPanel = new JPanel(new BorderLayout());
        text = new JLabel("Text");
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setOpaque(true);
        textPanel.add(text);
        add(textPanel);
        
        JPanel foregroundSliders = new JPanel();
        TitledBorder foregroundTtile = new TitledBorder("Label Text Color");
        foregroundSliders.setBorder(foregroundTtile);
        foregroundSliders.setLayout(new GridLayout(2, 3));
        foregroundSliders.add(new JLabel("Red Value"));
        foregroundSliders.add(new JLabel("Green Value"));
        foregroundSliders.add(new JLabel("Blue Value"));
      
        foregroundRed = new JSlider(0, 254, 0);
        foregroundGreen = new JSlider(0, 254, 0);
        foregroundBlue = new JSlider(0, 254, 0);
        foregroundRed.addChangeListener(new ForegrountSliderListener());
        foregroundGreen.addChangeListener(new ForegrountSliderListener());
        foregroundBlue.addChangeListener(new ForegrountSliderListener());
        foregroundSliders.add(foregroundRed);
        foregroundSliders.add(foregroundGreen);
        foregroundSliders.add(foregroundBlue);
        
        add(foregroundSliders);
        
        JPanel backgroundSliders = new JPanel();
        TitledBorder backgroundTtile = new TitledBorder("Label Background Color");
        backgroundSliders.setBorder(backgroundTtile);
        backgroundSliders.setLayout(new GridLayout(2, 3));
        backgroundSliders.add(new JLabel("Red Value"));
        backgroundSliders.add(new JLabel("Green Value"));
        backgroundSliders.add(new JLabel("Blue Value"));
      
        backgroundRed = new JSlider(0, 254, 0);
        backgroundGreen = new JSlider(0, 254, 0);
        backgroundBlue = new JSlider(0, 254, 0);
        backgroundRed.addChangeListener(new BackgroundSliderListener());
        backgroundGreen.addChangeListener(new BackgroundSliderListener());
        backgroundBlue.addChangeListener(new BackgroundSliderListener());
        backgroundSliders.add(backgroundRed);
        backgroundSliders.add(backgroundGreen);
        backgroundSliders.add(backgroundBlue);
        
        add(backgroundSliders);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    /**
     * Main method.
     * 
     * @param args Main arguments
     */
    public static void main(String[] args) {
        LabelColor window = new LabelColor("Label Color");
    }
    
    /**
     * Change listener that will set the foreground of the JLabel based upon
     * JScrollBar values.
     */
    public class ForegrountSliderListener implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            text.setForeground(new Color(foregroundRed.getValue(), foregroundGreen.getValue(), foregroundBlue.getValue()));
        }
    }
    
    /**
     * Change listener that will set the background of the JLabel based upon
     * JScrollBar values.
     */
    public class BackgroundSliderListener implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            text.setBackground(new Color(backgroundRed.getValue(), backgroundGreen.getValue(), backgroundBlue.getValue()));
        }
    }
}
