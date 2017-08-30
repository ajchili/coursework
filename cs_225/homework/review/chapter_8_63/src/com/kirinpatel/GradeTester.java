/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

/**
 * This class is a tester for the Grade class.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see com.kirinpatel.Grade
 */
public class GradeTester {
    
    /**
     * Tests all of the methods of the Grade class.
     * 
     * @param args Main arguments 
     */
    public static void main(String[] args) {
        // Create variables
        Grade grade = new Grade(100);
        
        // Display grades
        System.out.println(grade.toString());
        
        // Recreate data and display grades
        grade.setNumberOfStudents(5);
        System.out.println(grade.toString());
        grade.setNumberOfStudents(10);
        System.out.println(grade.toString());
        
        // Test all methods
        Grade newGrade = new Grade(5);
        grade.setNumberOfStudents(5);
        int[] grades = {50, 68, 75, 47, 89};
        
        System.out.println(newGrade.toString());
        System.out.println(grade.toString());
        
        System.out.println(grade.equals(newGrade));
        newGrade = grade;
        System.out.println(grade.equals(newGrade));
        
        grade.setGrades(grades);
        System.out.println(grade.toString());
        grade.setStudentGrade(2, 50);
        System.out.println(grade.toString());
        
        System.out.println("Total number of students: " + grade.getNumberOfStudents());
        System.out.print("Student grades:");
        for (int g : newGrade.getSortedGrades()) {
            System.out.print("  " + g);
        }
        
        System.out.println("\nAverage grade: " + grade.getAverageGrade());
        System.out.println("Highest grade: " + grade.getHighestGrade());
        System.out.println("Median grade: " + grade.getMedianGrade());
        System.out.println("Mode grade: " + grade.getModeGrade());
        
    }
    
}
