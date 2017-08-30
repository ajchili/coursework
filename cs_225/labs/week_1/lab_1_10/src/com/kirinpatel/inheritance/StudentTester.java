/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel.inheritance;

/**
 * This class is a tester for the Student class.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see java.lang.string
 * @see com.kirinpatel.inheritance.Student
 */
public class StudentTester {
    
    /**
     * Tests all of the getters and setters of the Student class.
     * 
     * @param args Main arguments
     */
    public static void main(String[] args) {
        Student student = new Student(1234);
        
        Major m = student.getMajor();
        if (m.equals(Major.CS)) {
            System.out.println("John is " + student.getAge());
        }
        
        if (m.compareTo(Major.IA) < 0) {
            System.out.println("John's major is Computer Science");
        } else {
            System.out.println("John's major is not Computer Science");
        }
        
        System.out.println(m.ordinal());
    }
    
}
