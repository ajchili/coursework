/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

/**
 * This class is a container for all information pertaining to GraduateApplicant.
 *
 * @author Kirin Patel
 * @verion 1.0
 * @see com.kirinpatel.CollegeApplicant
 */
public class GraduateApplicant extends CollegeApplicant {
    
    private String undergraduateSchool;
    
    /**
     * Main constructor that will set the name, school, and undergraduate school
     * for a graduate applicant.
     * 
     * @param name Student name
     * @param school Student school
     * @param undergraduateSchool Undergraduate school
     */
    public GraduateApplicant(String name, String school, String undergraduateSchool) {
        super(name, school);
        setUndergraduateSchool(undergraduateSchool);
    }
    
    /**
     * Provides the undergraduate school that was attended.
     * 
     * @return Returns undergraduate school
     */
    public String getUndergraduateSchool() {
        return undergraduateSchool;
    }
    
    /**
     * Provides if the student is an alumni.
     * 
     * @return Returns if the student is alumni
     */
    public boolean isAlumni() {
        return school.equals(undergraduateSchool);
    }
    
    /**
     * Sets the undergraduate school attended.
     * 
     * @param undergraduateSchool Undergraduate school 
     */
    public void setUndergraduateSchool(String undergraduateSchool) {
        if (undergraduateSchool.length() > 0) {
            this.undergraduateSchool = undergraduateSchool;
        } else {
            // Error handling
            System.out.println("ERROR: UNDERGRADUATE SCHOOL MUST BE PROVIDED");
            this.undergraduateSchool = "";
        }
    }
}
