/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadexamples;

import java.awt.event.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author esabbah
 */
public class HGWellsThreads extends JFrame {
    
    public HGWellsThreads() {

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Thread t = new Thread(new EloiTask());
        t.start();

        HGWellsThreads window = new HGWellsThreads();
        window.setSize(400, 400);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    private static class EloiTask implements Runnable {

        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println("" + new Date());

                    Thread.sleep(1000);

                }
            } catch (InterruptedException ex) {
                System.out.println("Interupt request received");
            }
        }

    }

}
