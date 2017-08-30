/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

/**
 * This class is a container for all information pertaining to CollegeApplicant.
 *
 * @author Kirin Patel
 * @version 1.0
 */
public class CollegeApplicant {
    
    protected String name;
    protected String school;
    
    /**
     * Main constructor that will set the name and school of a college
     * applicant.
     * 
     * @param name Student name
     * @param school School student is attending
     */
    public CollegeApplicant(String name, String school) {
        setName(name);
        setSchool(school);
    }
    
    /**
     * Provides CollegeApplicant class in string format.
     * 
     * @return Returns printable version of CollegeApplicant
     */
    @Override
    public String toString() {
        String returnValue = "College applicant: " + name;
        returnValue += "\nSchool: " + school;
        return returnValue;
    }
    
    /**
     * Tests to see if the provided object equals this object.
     * 
     * @param o Object to be tested
     * @return Returns if the two objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CollegeApplicant)) {
            return false;
        } else {
            CollegeApplicant objC = (CollegeApplicant) o;
            return (name.equals(objC.name) && school.equals(objC.school));
        }
    }
    
    /**
     * Provides college applicant's name.
     * 
     * @return Returns name of college applicant
     */
    public String getName() {
        return name;
    }
    
    /**
     * Provides college applicant's school.
     * 
     * @return Returns school of college applicant
     */
    public String getSchool() {
        return school;
    }
    
    /**
     * Sets college applicant's name.
     * 
     * @param name Student name
     */
    public void setName(String name) {
        if (name.length() > 0) {
            this.name = name;
        } else {
            // Error handling
            System.out.println("ERROR: NAME MUST BE PROVIDED");
            this.name = "";
        }
    }
    
    /**
     * Sets college applicant's school.
     * 
     * @param school Student school
     */
    public void setSchool(String school) {
        if (school.length() > 0) {
            this.school = school;
        } else {
            // Error handling
            System.out.println("ERROR: SCHOOL MUST BE PROVIDED");
            this.school = "";
        }
    }
}
