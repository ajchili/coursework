/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.util.Scanner;

/**
 * This class is a tester for the CollegeApplicant class as well as its subclasses.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see com.kirinpatel.UndergraduateApplicant
 * @see com.kirinpatel.GraduateApplicant
 */
public class CollegeApplicantTester {
    
    
    /**
     * Tests all of the methods of the CollegeApplicant class as well as its 
     * subclasses.
     * 
     * @param args Main arguments
     */
    public static void main(String[] args) {
        // Initialize variables
        Scanner s = new Scanner(System.in);
        UndergraduateApplicant ua = new UndergraduateApplicant(" ", " ", 1, .1);
        GraduateApplicant ga = new GraduateApplicant(" ", " ", " ");
        
        // Get input
        do {
            System.out.print("Please enter a name for the undergraduate student: ");
            while(!s.hasNextLine()) {
            System.out.print("Please enter a name for the undergraduate student: ");
                s.next();
            }
            ua.setName(s.nextLine());
        } while(ua.getName().length() <= 0);
        
        do {
            System.out.print("Please enter a school name for the undergraduate student: ");
            while(!s.hasNextLine()) {
            System.out.print("Please enter a school name for the undergraduate student: ");
                s.next();
            }
            ua.setSchool(s.nextLine());
        } while(ua.getSchool().length() <= 0);
        
        do {
            System.out.print("Please enter the undergraduate student's SAT score: ");
            while(!s.hasNextInt()) {
                System.out.print("Please enter the undergraduate student's SAT score: ");
                s.nextInt();
            }
            ua.setSATScore(s.nextInt());
        } while (ua.getSATScore() < 0 || ua.getSATScore() > 1600);
        
        do {
            System.out.print("Please enter the undergraduate student's GPA: ");
            while(!s.hasNextDouble()) {
                System.out.print("Please enter the undergraduate student's GPA: ");
                s.nextDouble();
            }
            ua.setGPA(s.nextDouble());
        } while (ua.getGPA() < 0.0 || ua.getGPA() > 4.0);
        do {
            System.out.print("Please enter a name for the graduate student: ");
            while(!s.hasNextLine()) {
            System.out.print("Please enter a name for the graduate student: ");
                s.next();
            }
            ga.setName(s.nextLine());
        } while(ga.getName().length() <= 0);
        
        do {
            System.out.print("Please enter a undergraduate school name for the graduate student: ");
            while(!s.hasNextLine()) {
            System.out.print("Please enter a undergraduate school name for the graduate student: ");
                s.next();
            }
            ga.setUndergraduateSchool(s.nextLine());
        } while(ga.getUndergraduateSchool().length() <= 0);
        
        do {
            System.out.print("Please enter a school name for the graduate student: ");
            while(!s.hasNextLine()) {
            System.out.print("Please enter a school name for the graduate student: ");
                s.next();
            }
            ga.setSchool(s.nextLine());
        } while(ga.getSchool().length() <= 0);
        
        // Display info
        System.out.println("\n" + ua.toString() + "\nSAT score: " + ua.getSATScore() + "\nGPA: " + ua.getGPA());
        System.out.println("\n" + ga.toString() + "\nUndergraduate school: " + ga.getUndergraduateSchool() + "\nSame school: " + ga.isAlumni());
    }
}
