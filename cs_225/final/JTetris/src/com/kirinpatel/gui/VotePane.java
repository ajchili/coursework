/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel.gui;

import com.kirinpatel.Main;
import com.kirinpatel.net.Client;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * This class is used with the client to allow for user interaction with the
 * game through voting.
 * 
 * @author Kirin Patel
 * @version 1.0
 */
public class VotePane extends JPanel {
    
    private boolean isRunning = false;
    private JProgressBar timeBar;
    
    /**
     * Main constructor that will create the VotePane.
     */
    public VotePane() {
        setPreferredSize(new Dimension(140, getHeight() / 3));
        setMaximumSize(new Dimension(140, getHeight() / 3));
                
        setLayout(new GridLayout(3, 1));
        timeBar = new JProgressBar(0, 1000);
        add(timeBar);
        
        JPanel rotateButtons = new JPanel(new GridLayout(1, 2));
        Button rl = new Button("\u21ba");
        rl.addActionListener(new CustomActionListener(0));
        rotateButtons.add(rl);
        Button rr = new Button("\u21bb");
        rr.addActionListener(new CustomActionListener(1));
        rotateButtons.add(rr);
        add(rotateButtons);
        
        JPanel moveButtons = new JPanel(new GridLayout(1, 2));
        Button mr = new Button("\u25c4");
        mr.addActionListener(new CustomActionListener(2));
        Button ml = new Button("\u25ba");
        ml.addActionListener(new CustomActionListener(3));
        moveButtons.add(mr);
        moveButtons.add(ml);
        add(moveButtons);
        
        new Thread(new TimeBarThread()).start();
    }
    
    /**
     * Provides a boolean to determine if the timebar is enabled.
     * 
     * @return Returns if timebar is enabled
     */
    public boolean isTimebarEnabled() {
        return isRunning;
    }
    
    /**
     * Sets if the timebar is enabled.
     * 
     * @param enabled IS timebar enabled
     */
    public void enableTimebar(boolean enabled) {
        isRunning = enabled;
    }
    
    /**
     * Sets timebar progress.
     * 
     * @param time Time in milliseconds
     */
    public void setTimeBarProgress(int time) {
        if (time < 0)
            timeBar.setValue(0);
        else if (time <= Main.GAME_TICK - 500) 
            timeBar.setValue(time);
        else
            timeBar.setValue(Main.GAME_TICK - 500);
    }
    
    /**
     * Custom ActionListener that will interact with a client on vote.
     */
    class CustomActionListener implements ActionListener {

        private int vote;
        
        /**
         * Main constructor.
         * 
         * @param vote Vote type
         */
        public CustomActionListener(int vote) {
            this.vote = vote;
        }
        
        /**
         * Sends vote to client on button press.
         * 
         * @param e ActionEvent
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            Client.sendVote(vote);
        }
    }
    
    /**
     * This class will decrease the playable time of the current vote.
     */
    class TimeBarThread implements Runnable {
        
        /**
         * This method decreases the time left by one per millisecond until the
         * time left is zero. Once at zero, the time left is reset.
         */
        @Override
        public void run() {
            int time;
            
            while(true) {
                time = timeBar.getValue();
                
                if (time > 0)
                    time--;
                
                if (isRunning)
                    timeBar.setValue(Main.GAME_TICK - 500);
                
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(VotePane.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
