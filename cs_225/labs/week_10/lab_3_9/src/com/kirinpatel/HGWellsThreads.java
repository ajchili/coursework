/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;

/**
 * Demonstrates the use of Threads through an image switching JFrame.
 * 
 * @author esabbah, Kirin Patel
 * @version 1.0
 */
public class HGWellsThreads extends JFrame {

    private static HGWellsThreads window;
    private static JLabel imageLabel;
    private static boolean isChangingImage = true;
    
    /**
     * Main constructor that will create a JFrame with an image and button.
     */
    public HGWellsThreads() {
        setSize(400, 400);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        imageLabel = new JLabel();
        add(imageLabel, BorderLayout.CENTER);
        
        JButton pauseButton = new JButton("Pause");
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isChangingImage = !isChangingImage;
                if (pauseButton.getText().equals("Pause"))
                    pauseButton.setText("Resume");
                else
                    pauseButton.setText("Pause");
            }
        });
        add(pauseButton, BorderLayout.SOUTH);
        
        setVisible(true);
    }

    /**
     * Main method.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        window = new HGWellsThreads();
        
        Thread t = new Thread(new EloiTask());
        t.start();
    }

    /**
     * Custom Runnable class that will switch JLabel image.
     */
    private static class EloiTask implements Runnable {

        private int i = 1;
        
        /**
         * Method that will be called on the start of a Thread.
         */
        @Override
        public void run() {
            try {
                while (true) {
                    if (isChangingImage) {
                        i = i % 2 + 1;
                        imageLabel.setIcon(new ImageIcon("java_" + i + ".png"));
                    }
                    
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                System.out.println("Interupt request received");
            }
        }
    }
}
