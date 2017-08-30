/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

/**
 * This class is a container for all information pertaining to UndergraduateApplicant.
 *
 * @author Kirin Patel
 * @version 1.0
 * @see com.kirinpatel.CollegeApplicant
 */
public class UndergraduateApplicant extends CollegeApplicant {
 
    public int SAT;
    public double GPA;
    
    /**
     * Main constructor that will set the name, school, SAT, and GPA of an
     * undergraduate applicant.
     * 
     * @param name Student name
     * @param school Student school
     * @param SAT Student SAT score
     * @param GPA Student Grade Point Average
     */
    public UndergraduateApplicant(String name, String school, int SAT, double GPA) {
        super(name, school);
        setSATScore(SAT);
        setGPA(GPA);
    }
    
    /**
     * Provides SAT score.
     * 
     * @return Returns student SAT score
     */
    public int getSATScore() {
        return SAT;
    }
    
    /**
     * Provides GPA.
     * 
     * @return Returns student GPA
     */
    public double getGPA() {
        return GPA;
    }
    
    /**
     * Sets SAT score.
     * 
     * @param SAT SAT score
     */
    public void setSATScore(int SAT) {
        if (SAT < 0) {
            // Error handling
            System.out.println("ERROR: SAT SCORE MUST BE GREATER THAN OR EQUAL TO 0");
            this.SAT = -1;
        } else if (SAT > 1600) {
            // Error handling
            System.out.println("ERROR: SAT SCORE MUST BE LESS THAN OR EQUAL TO 1600");
            this.SAT = -1;
        } else {
            this.SAT = SAT;
        }
    }
    
    /**
     * Sets GPA.
     * 
     * @param GPA Grade Point Average
     */
    public void setGPA(double GPA) {
        if (GPA < 0.0) {
            // Error handling
            System.out.println("ERROR: GPA MUST BE GREATER THAN OR EQUAL TO 0.0");
            this.GPA = -1;
        } else if (GPA > 4.0) {
            // Error handling
            System.out.println("ERROR: GPA MUST BE LESS THAN OR EQUAL TO 4.0");
            this.GPA = -1;
        } else {
            this.GPA = GPA;
        }
    }
    
}
