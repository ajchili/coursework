/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import com.kirinpatel.graphics.DrawPanel;
import java.awt.*;
import java.awt.event.*;
import static java.awt.event.KeyEvent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Kirin Patel
 * @version 1.0
 */
public class Main extends JFrame {
    
    private int speed = 50;
    private DrawPanel drawPanel;
    private Timer timer;
    private Thread thread;
    private boolean isThreadPaused = false;
    
    /**
     * Main constructor that will create a JFrame with a DrawPanel inside of it.
     * This panel will either be controlled by a timer or thread depending on
     * its specified type.
     * 
     * @param title Title
     * @param type Type of window
     */
    public Main(String title, int type) {
        super(title);
        
        setSize(new Dimension(300, 400));
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        drawPanel = new DrawPanel();
        add(drawPanel);
        
        if (type == 0) {
            addKeyListener(new TimerKeysListener());
            timer = new Timer(50, new MovementListener());
            timer.start();
        } else {
            addKeyListener(new ThreadKeysListener());
            thread = new Thread(new MovementThread());
            thread.start();
        }
        
        setVisible(true);
    }
    
    /**
     * Main method.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        Main timerWindow = new Main("Pendulum Swinging Timer", 0);
        Main threadWindow = new Main("Pendulum Swinging Thread", 1);
    }
    
    /**
     * Custom ActionListener that will move a pendulum based on the Timer class.
     */
    class MovementListener implements ActionListener {

        private int x = 0;
        private int y = 0;
        private boolean moveRight = false;
        private boolean moveDown = false;
        private boolean moveY = false;
        
        /**
         * Code will be executed on each call of this method. This method
         * determines the next position of the pendulum.
         * 
         * @param e ActionEvent
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            moveY = !moveY;
            
            if (x == 0) {
                x = drawPanel.getWidth() / 2;
            } else if (moveRight) {
                x--;
                if (x == 20) {
                    moveRight = false;
                    moveDown = true;
                }
            } else {
                x++;
                if (x == drawPanel.getWidth() - 20) {
                    moveRight = true;
                    moveDown = true;
                }
            }
            
            if (x == getWidth() / 2) {
                moveDown = false;
            }
            
            if(moveY) {
                if (y == 0 || y == -40) {
                    y = drawPanel.getHeight() - 40;
                } else if (moveDown) {
                    y++;
                } else {
                    y--;
                }
            }
             
            drawPanel.setX(x);
            drawPanel.setY(y);
            drawPanel.repaint();
        }
    }
    
    /**
     * Custom Runnable that will move a pendulum based on the Runnable and
     * Thread class.
     */
    class MovementThread implements Runnable {

        private int x = 0;
        private int y = 0;
        private boolean moveRight = false;
        private boolean moveDown = false;
        private boolean moveY = false;

        /**
         * Code that will be run in a Thread. This method determines the next
         * position of the pendulum.
         */
        @Override
        public void run() {
            while(true) {
                if (!isThreadPaused) {
                    moveY = !moveY;

                    if (x == 0) {
                        x = drawPanel.getWidth() / 2;
                    } else if (moveRight) {
                        x--;
                        if (x == 20) {
                            moveRight = false;
                            moveDown = true;
                        }
                    } else {
                        x++;
                        if (x == drawPanel.getWidth() - 20) {
                            moveRight = true;
                            moveDown = true;
                        }
                    }

                    if (x == getWidth() / 2) {
                        moveDown = false;
                    }

                    if(moveY) {
                        if (y == 0 || y == -40) {
                            y = drawPanel.getHeight() - 40;
                        } else if (moveDown) {
                            y++;
                        } else {
                            y--;
                        }
                    }

                    drawPanel.setX(x);
                    drawPanel.setY(y);
                    drawPanel.repaint();
                }

                try {
                    thread.sleep(speed);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    /**
     * Custom KeyListener for a Timer based DrawPanel.
     */
    class TimerKeysListener implements KeyListener {

        /**
         * Unused method that detects when a key is typed.
         * 
         * @param e KeyEvent
         */
        @Override
        public void keyTyped(KeyEvent e) {
            // Unused Method
        }

        /**
         * Determines if a key is pressed, if a specified key is pressed an
         * action will be completed.
         * 
         * @param e KeyEvent
         */
        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()) {
                case VK_R:
                    timer.start();
                    break;
                case VK_P:
                    timer.stop();
                    break;
                case VK_UP:
                    if (speed > 5) {
                        speed -= 5;
                    }
                    timer.setDelay(speed);
                    break;
                case VK_DOWN:
                    if (speed < 100) {
                        speed += 5;
                    }
                    timer.setDelay(speed);
                    break;
            }
        }

        /**
         * Unused method that detects when a key is released.
         * 
         * @param e KeyEvent
         */
        @Override
        public void keyReleased(KeyEvent e) {
            // Unused method
        }
    }
    
    /**
     * Custom KeyListener for a Thread based DrawPanel.
     */
    class ThreadKeysListener implements KeyListener {

        /**
         * Unused method that detects when a key is typed.
         * 
         * @param e KeyEvent
         */
        @Override
        public void keyTyped(KeyEvent e) {
            // Unused Method
        }

        /**
         * Determines if a key is pressed, if a specified key is pressed an
         * action will be completed.
         * 
         * @param e KeyEvent
         */
        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()) {
                case VK_R:
                    isThreadPaused = false;
                    break;
                case VK_P:
                    isThreadPaused = true;
                    break;
                case VK_UP:
                    if (speed > 5) {
                        speed -= 5;
                    }
                    break;
                case VK_DOWN:
                    if (speed < 100) {
                        speed += 5;
                    }
                    break;
            }
        }

        /**
         * Unused method that detects when a key is released.
         * 
         * @param e KeyEvent
         */
        @Override
        public void keyReleased(KeyEvent e) {
            // Unused method
        }
    }
}
