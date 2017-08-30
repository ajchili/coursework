/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * @author Kirin Patel
 * @version 1.0
 */
public class ScrollSlideWindow extends JFrame {
    
    public ScrollSlideWindow()  {
        JScrollBar bar = new JScrollBar(JScrollBar.HORIZONTAL);
        JSlider slide = new JSlider(JSlider.VERTICAL);
        
        bar.addAdjustmentListener(new BarListener());
        slide.addChangeListener(new SlideListener());
        
        this.add(bar, BorderLayout.NORTH);
        this.add(slide, BorderLayout.SOUTH);
    }
    
    private class BarListener implements AdjustmentListener {

        @Override
        public void adjustmentValueChanged(AdjustmentEvent e) {
            System.out.println("Scroll location: " + e.getValue());
        }
    }
    
    private class SlideListener implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            JSlider slide = (JSlider) e.getSource();
            System.out.println("Slider location: " + slide.getValue());
        }
    }
    
     public static void main(String[] args) {
        ScrollSlideWindow window = new ScrollSlideWindow();
        window.setTitle("Demo ItemListeners");
        window.pack();
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}
