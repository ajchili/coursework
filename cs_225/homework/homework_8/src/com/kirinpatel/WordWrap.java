/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This class will create a GUI that displays a JTextArea, JCheckBox, and
 * JRadioButtons. This JTextArea will have its word wrap editable by the
 * JCheckBox and JRadioButtons.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see javax.swing.JFrame
 * @see javax.swing.JTextArea
 * @see java.awt.GridLayout
 * @see javax.swing.JPanel
 * @see javax.swing.JComboBox
 * @see javax.swing.JRadioButton
 * @see javax.swing.ButtonGroup
 * @see javax.swing.event.ChangeEvent
 * @see javax.swing.event.ChangeListener
 */
public class WordWrap extends JFrame {
    
    public static JTextArea textArea;
    
    /**
     * Creates WordWrap GUI.
     * 
     * @param text Title
     */
    public WordWrap(String text) {
        super(text);
        
        setLayout(new GridLayout(3, 1));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        textArea = new JTextArea();
        textArea.setRows(2);
        add(textArea);
        
        JCheckBox wrapBox = new JCheckBox("Wordwrap");
        wrapBox.addChangeListener(new WrapListener());
        add(wrapBox);
        
        JPanel radioPanel = new JPanel(new GridLayout(1, 2));
        JRadioButton wordWrap = new JRadioButton("Wrap by words");
        wordWrap.addChangeListener(new WordWrapListener());
        JRadioButton charWrap = new JRadioButton("Wrap by characters");
        charWrap.addChangeListener(new CharWrapListener());
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(wordWrap);
        buttonGroup.add(charWrap);
        radioPanel.add(wordWrap);
        radioPanel.add(charWrap);
        add(radioPanel);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    /**
     * Main method.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        WordWrap window = new WordWrap("WordWrap");
    }
    
    /**
     * Custom ChangeListener.
     */
    public class WrapListener implements ChangeListener {

        /**
         * This method will determine if line wrap should be enabled.
         * 
         * @param e ChangeEvent
         */
        @Override
        public void stateChanged(ChangeEvent e) {
            JCheckBox box = (JCheckBox) e.getSource();
            textArea.setLineWrap(box.isSelected());
        }
    }
    
    /**
     * Custom ChangeListener.
     */
    public class WordWrapListener implements ChangeListener {

        /**
         * This method will set line wrap to be by word. 
         * 
         * @param e ChangeEvent
         */
        @Override
        public void stateChanged(ChangeEvent e) {
            JRadioButton radio = (JRadioButton) e.getSource();
            textArea.setWrapStyleWord(radio.isSelected());
        }
    }
    
    /**
     * Custom ChangeListener.
     */
    public class CharWrapListener implements ChangeListener {

        /**
         * This method will set line wrap to be by character.
         * 
         * @param e ChangeEvent
         */
        @Override
        public void stateChanged(ChangeEvent e) {
            JRadioButton radio = (JRadioButton) e.getSource();
            if (radio.isSelected()) 
                textArea.setWrapStyleWord(false);
        }
    }
}
