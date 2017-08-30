/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.util.Scanner;

/**
 * This class is a tester for the Vehicle class as well as its subclasses.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see com.kirinpatel.Bicycle
 * @see com.kirinpatel.MotorizedVehicle
 */
public class VehicleTester {
    
    /**
     * Tests all of the methods of the Vehicle class as well as its 
     * subclasses.
     * 
     * @param args Main arguments
     */
    public static void main(String[] args) {
        // Initialize variables
        Scanner s = new Scanner(System.in);
        Bicycle bicycle = new Bicycle("Tommy");
        MotorizedVehicle motorizedVehicle = new MotorizedVehicle("John", 4, 50.0);
        
        // Display information
        System.out.println(bicycle.toString());
        System.out.println("\n" + motorizedVehicle.toString());
        
        // Get input
        System.out.print("\nPlease enter a bike owners name: ");
        Bicycle bicycle_2 = new Bicycle(s.next());
        
        MotorizedVehicle motorizedVehicle_2 = new MotorizedVehicle(" ", 1, 1);
        System.out.print("\nPlease enter a vehicle owners name: ");
        motorizedVehicle_2.setOwnersName(s.next());
        
        do {
            System.out.print("Please enter the number of wheels the vehicle has: ");
            while (!s.hasNextInt()) {
                System.out.print("Please enter the number of wheels the vehicle has: ");
                s.nextInt();
            }
            motorizedVehicle_2.setNumberOfWheels(s.nextInt());
        } while(motorizedVehicle_2.getNumberOfWheels() < 0);
        
        do {
            System.out.print("Please enter the evd of the vehicle: ");
            while (!s.hasNextDouble()) {
                System.out.print("Please enter the evd of the vehicle: ");
                s.nextDouble();
            }
            motorizedVehicle_2.setEVD(s.nextDouble());
        } while(motorizedVehicle_2.getEVD() < 0.0);
        
        
        // Display information
        System.out.println("\n" + bicycle_2.toString());
        System.out.println("\n" + motorizedVehicle_2.toString());
    }
}
