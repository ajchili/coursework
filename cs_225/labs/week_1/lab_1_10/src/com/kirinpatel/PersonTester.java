/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

/**
 * This class is a tester for the Person class.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see java.lang.string
 * @see com.kirinpatel.Person
 */
public class PersonTester {
    
    /**
     * Tests all of the getters and setters of the Person class.
     * 
     * @param args Main arguments
     */
    public static void main(String[] args) {
        // Create 3 persons
        Person jimmy = new Person(111111111);
        Person bobby = new Person(222222222);
        Person timmy = new Person(333333333);
        
        // Set eacher person's respected statistics
        jimmy.setAge(45);
        jimmy.setHeight(5.0);
        jimmy.setFirstName("Jimmy");
        
        bobby.setAge(13);
        bobby.setHeight(4.0);
        bobby.setFirstName("Bobby");
        
        timmy.setAge(18);
        timmy.setHeight(6.0);
        timmy.setFirstName("Timmy");
        
        // Print out each person's statistics
        System.out.println(jimmy.getFirstName());
        System.out.println("AGE: " + jimmy.getAge());
        System.out.println("HEIGHT: " + jimmy.getHeight());
        System.out.println("SSN: " + jimmy.getSSN() + "\n\n");
        
        System.out.println(bobby.getFirstName());
        System.out.println("AGE: " + bobby.getAge());
        System.out.println("HEIGHT: " + bobby.getHeight());
        System.out.println("SSN: " + bobby.getSSN() + "\n\n");
        
        System.out.println(timmy.getFirstName());
        System.out.println("AGE: " + timmy.getAge());
        System.out.println("HEIGHT: " + timmy.getHeight());
        System.out.println("SSN: " + timmy.getSSN());
    }
    
}
