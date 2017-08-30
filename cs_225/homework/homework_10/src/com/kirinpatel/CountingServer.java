/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class contains all the information pertaining to the CountingServer.
 * 
 * @author Kirin Patel
 * @version 1.0
 */
public class CountingServer {
    
    private static byte timesWritten;
    
    /**
     * Main constructor that will create CountingServer.
     */
    public CountingServer() {
        ServerSocket service = null;
        
        try {
            service = new ServerSocket(8000);
            
            Socket socket;
            DataOutputStream output;
            
            while(true) {
                socket = service.accept();

                writeToFile();
                output = new DataOutputStream(socket.getOutputStream());
                output.writeByte(timesWritten);
            }
        } catch (IOException ex) {
            // Error handling
            System.out.println("Error creating server: " + ex.getMessage());
            System.exit(0);
        } finally {
            try {
                service.close();
            } catch (IOException ex) {
                // Error handling
                System.out.println("Error closing server: " + ex.getMessage());
                System.exit(0);
            }
        }
    }
    
    /**
     * Writes a generated char to a file.
     */
    private static void writeToFile() {
        try {
            RandomAccessFile raf = new RandomAccessFile("timesconnected.dat", "rw");
            
            if (raf.length() != 0)
                timesWritten = (byte) (raf.readByte() + 1);
            else
                timesWritten = 1;
            
            raf.seek(0);
            raf.writeByte(timesWritten);
            System.out.println("" + timesWritten);
        } catch (IOException ex) {
            // Error hadling
            System.out.println("File not able to be read/written to.");
        }
    }
    
    /**
     * Main method.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        new CountingServer();
    }
}
