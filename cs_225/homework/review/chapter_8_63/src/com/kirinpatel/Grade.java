/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.util.Random;

/**
 * This class is a container for information pertaining to a students grade.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see java.util.Random
 */
public class Grade {
    
    private int numberOfStudents;
    private int[] grades;
    private Random random = new Random();
    
    /**
     * Main constructor for the Grade class, this will set the number of
     * students and randomly assign their grades.
     * 
     * @param numberOfStudents Number of students in the class
     */
    public Grade(int numberOfStudents) {
        setNumberOfStudents(numberOfStudents);
    }
    
    /**
     * Secondary constructor for the Grade class, this will set the number of
     * students and assign their grades based on inputed values.
     * 
     * @param numberOfStudents Number of students in the class
     * @param grades Array of student grades
     */
    public Grade(int numberOfStudents, int[] grades) {
        setNumberOfStudents(numberOfStudents);
        setGrades(grades);
    }
    
    /**
     * Provides all grades for students in string format.
     * 
     * @return Returns a printable version of Grade Class
     */
    public String toString() {
       String returnValue = "Total number of students: " + numberOfStudents + "\nStudent grades:";
       for (int grade : grades) {
           returnValue += "  " + grade;
       }
       returnValue += "\nAverage grade: " + getAverageGrade();
       returnValue += "\nHighest grade: " + getHighestGrade();
       returnValue += "\nMedian grade: " + getMedianGrade();
       returnValue += "\nMode grade: " + getModeGrade() + "\n";
       return returnValue;
    }
    
    /**
     * Checks to see if the input is equal to this Grade.
     * 
     * @param o Object to be tested
     * @return Boolean value of if inputed object is equal to this object
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Grade)) {
            return false;
        } else {
            Grade objG = (Grade) o;
            if (numberOfStudents != objG.getNumberOfStudents()) {
                return false;
            }
            
            if (grades.length != objG.getGrades().length) {
                return false;
            }
            
            if (this.getAverageGrade() != objG.getAverageGrade()) {
                return false;
            }
            
            if (this.getMedianGrade() != objG.getMedianGrade()) {
                return false;
            }
            
            if (this.getModeGrade() != objG.getModeGrade()) {
                return false;
            }
            
            return true;
        }
    }
    
    /**
     * Sets the number of students and reassigns the grades randomly for each
     * student.
     * 
     * @param numberOfStudents Number of students in the class
     */
    public void setNumberOfStudents(int numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
        grades = new int[this.numberOfStudents];
        for (int i = 0; i < this.numberOfStudents; i++) {
            grades[i] = random.nextInt(100);
        }
    }
    
    /**
     * Sets the grades based upon inputed value for each student.
     * 
     * @param grades Array of student grades
     */
    public void setGrades(int[] grades) {
        if (grades.length == numberOfStudents) {
            this.grades = grades;
        } else if (grades.length < numberOfStudents) {
            // Error handling larger number of students than grades
            for (int i = 0; i < numberOfStudents; i++) {
                if (i < grades.length) {
                    this.grades[i] = grades[i];
                } else {
                    this.grades[i] = random.nextInt(100);
                }
            }
        } else {
            // Error handling for smaller number of students than grades
            numberOfStudents = grades.length;
            this.grades = grades;
        }
    }
    
    /**
     * Set a specified student's grades.
     * 
     * @param student Student who's grade will be changed
     * @param grade New grade
     */
    public void setStudentGrade(int student, int grade) {
        if (student <= numberOfStudents && student >= 0) {
            if (grade > 0 && grade <= 100) {
                grades[student] = grade;
            } else {
                // Error handling
                System.out.println("Grade out of bounds, please enter a grade between 0 and 100");
                grades[student] = 50;
            }
        } else {
            // Error handling
            numberOfStudents++;
            if (grade > 0 && grade <= 100) {
                grades[numberOfStudents - 1] = grade;
            } else {
                // Error handling
                System.out.println("Grade out of bounds, please enter a grade between 0 and 100");
                grades[numberOfStudents - 1] = 50;
            }
        }
    }
    
    /**
     * This method will return the number of students.
     * 
     * @return Number of students
     */
    public int getNumberOfStudents() {
        return numberOfStudents;
    }
    
    /**
     * This method will return an array of student grades.
     * 
     * @return Array of student grades
     */
    public int[] getGrades() {
        return grades;
    }
    
    /**
     * This method will return a sorted array of student grades.
     * 
     * @return Ascending array of student grades
     */
    public int[] getSortedGrades() {
        boolean isRunning;
        int iteration = 0;
        int grade;
        int[] sortedGrades = grades;
        
        do {
            isRunning = false;
            iteration++;
            for (int i = 0; i < sortedGrades.length - iteration; i++) {
                if (sortedGrades[i] > sortedGrades[i + 1]) {
                    grade = sortedGrades[i];
                    sortedGrades[i] = sortedGrades[i + 1];
                    sortedGrades[i + 1] = grade;
                    isRunning = true;
                }
            }
        } while(isRunning);
        
        return sortedGrades;
    }
    
    /**
     * This method will return the highest grade obtained by a student.
     * 
     * @return Highest grade in the class
     */
    public int getHighestGrade() {
        int[] g = getSortedGrades();
        return g[g.length - 1];
    }
    
    /**
     * This method will return the average grade of the students.
     * 
     * @return Average grade of the students
     */
    public double getAverageGrade() {
        double avg = 0;
        
        for (int grade : grades) {
            avg += (double) grade;
        }
        
        return avg / grades.length;
    }
    
    /**
     * This method will return the median grade of the students.
     * 
     * @return Median grade of the students
     */
    public int getMedianGrade() {
        int[] g = getSortedGrades();
        return g[g.length / 2];
    }
    
    /**
     * This method will return the mode grade of the students.
     * 
     * @return Mode grade of the students
     */
    public int getModeGrade() {
        int mode = 0;
        int[] modeGrade = new int[101];
        
        for (int grade : grades) {
            modeGrade[grade]++;
        }
        
        for (int i = 0; i < modeGrade.length; i++) {
            if (modeGrade[i] > mode) {
                mode = i;
            }
        }
        
        return mode;
    }
    
}
