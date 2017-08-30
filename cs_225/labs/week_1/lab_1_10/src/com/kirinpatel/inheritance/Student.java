/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel.inheritance;

import com.kirinpatel.Person;

/**
 * This class is a container for information pertaining to a student.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see com.kirinpatel.Person
 * @see com.kirinpatel.inheritance.Major
 */
public class Student extends Person implements Cloneable, Comparable {
    
    private double gpa;
    private int credits;
    private Major major;
    
    /**
     * Main constructor that set Social Security Number and creates a student profile.
     * 
     * @param ssn Social Security Number
     */
    public Student(int ssn) {
        super(ssn);
        setGPA(3.0);
        setCredits(30);
        setMajor(Major.CS);
    }
    
    /**
     * Secondary constructor that set Social Security Number, gpa, credits, and 
     * Major of a student.
     * 
     * @param ssn
     * @param gpa
     * @param credits
     * @param major 
     */
    private Student(int ssn, double gpa, int credits, Major major) {
        super(ssn);
        setGPA(gpa);
        setCredits(credits);
        setMajor(major); 
    }
    
    /**
     * Provides the student's age.
     * 
     * @return Returns student's age
     */
    @Override
    public int getAge() {
        // return 100;
        System.out.println("Overriden version");
        return super.getAge();
    }
    
    /**
     * Provides the student's gpa.
     * 
     * @return Returns student's gpa
     */
    public double getGPA() {
        return gpa;
    }
    
    /**
     * Provides the student's number of credits.
     * 
     * @return Returns student's number of credits
     */
    public int getCredits() {
        return credits;
    }
    
    /**
     * Provides the student's major.
     * 
     * @return Returns student's major
     */
    public Major getMajor() {
        return major;
    }
    
    /**
     * Sets the student's gpa.
     * 
     * @param gpa Student's gpa
     */
    public void setGPA(double gpa) {
        if (gpa >= 0.0 && gpa <= 4.0) {
            this.gpa = gpa;
        } else {
            // Error handling
            System.out.println("ERROR: GPA MUST BE GREATER THAN OR EQUAL TO 0.0 AND LESS THAN OR EQUAL TO 4.0");
            this.gpa = -1.0;
        }
    }
    
    /**
     * Sets the student's number of credits.
     * 
     * @param credits Student's number of credits
     */
    public void setCredits(int credits) {
        if (credits >= 0) {
            this.credits = credits;
        } else {
            // Error handling
            System.out.println("ERROR: NUMBER OF CREDITS MUST BE GREATHER THAN OR EQUAL TO 0");
            this.credits = -1;
        }
    }
    
    /**
     * Sets the student's major.
     * 
     * @param major Student's major.
     */
    public void setMajor(Major major) {
        this.major = major;
    }
    
    /**
     * Provides a new object with identical data to the object provided.
     * 
     * @return Returns a new object with the same data as the object provided
     * @throws java.lang.CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        Student objS = (Student) super.clone();
        return new Student(objS.getSSN(), objS.gpa, objS.credits, objS.major);
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Student)) {
            return 0;
        } else {
            Student objS = (Student) o;
            return getSSN() - objS.getSSN();
        }
    }
    
}
