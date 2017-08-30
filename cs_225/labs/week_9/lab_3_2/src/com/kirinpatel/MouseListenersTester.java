/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.*;

/**
 *
 * @author Kirin Patel
 * @version 1.0
 */
public class MouseListenersTester extends JFrame {
    
    public MouseListenersTester(String title) {
        super(title);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        addMouseListener(new MouseListeners());
        addMouseMotionListener(new MouseEventListeners());
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        MouseListenersTester window = new MouseListenersTester("MouseListenr GUI");
    }
    
    public class MouseListeners implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("Clicked!");
        }

        @Override
        public void mousePressed(MouseEvent e) {
            System.out.println("Pressed!");
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            System.out.println("Released!");
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            System.out.println("Entered window!");
        }

        @Override
        public void mouseExited(MouseEvent e) {
            System.out.println("Exited window!");
        }
    }
    
    public class MouseEventListeners implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {
            System.out.println("Mouse dragged! (" + e.getX() + ", " + e.getY() + ")");
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            System.out.println("Mouse moved! (" + e.getX() + ", " + e.getY() + ")");
        }
        
    }
}
