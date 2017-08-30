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
 */
public class CloneTester  {
    
    /**
     * Tests the clone method of the Person class.
     * 
     * @param args Main arguments
     * @throws CloneNotSupportedException 
     */
    public static void main(String[] args) throws CloneNotSupportedException {
        Person a = new Person(1234567890);
        Person b = new Person(1111111111);
        Person c = a;
        Person d = (Person) a.clone();
        
        Family e = new Family(a, b, "Test");
        Family f = e;
        Family g = (Family) e.clone();
        
        
        System.out.println("Are object a and c referencing the same thing: " + (a == c));
        System.out.println("Are object a and c equal: " + a.equals(c));
        System.out.println("Are object a and d referencing the same thing: " + (a == d));
        System.out.println("Are object a and d equal: " + a.equals(d));
        System.out.println("\nAre object e and f referencing the same thing: " + (e == f));
        System.out.println("Are object e and f equal: " + e.equals(f));
        System.out.println("Are object e and g referencing the same thing: " + (e == g));
        System.out.println("Are object e and g equal: " + e.equals(g));
    }
}
