/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import com.kirinpatel.inheritance.Student;
import java.util.Date;

/**
 * This class is a tester for the MyArrayList class.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see com.kirinpatel.MyArrayList
 */
public class MyArrayListTester {

    /**
     * Tests all of the getters and setters of the MyArrayList class.
     *
     * @param args Main arguments
     */
    public static void main(String[] args) {
        MyArrayList list = new MyArrayList();
        
        System.out.println(list.toString());
        System.out.println(list.isEmpty());
        
        list.add(new Circle(5.0));
        list.add(new Date());
        list.add(new Square(3, 3));
        list.add(new Student(1234567890));
        
        System.out.println("\n" + list.toString());
        
        list.set(1, new Square(2, 2));
        list.set(2, new Date());
        
        System.out.println("\n" + list.toString());
        
        list.remove(2);
        System.out.println("\n" + list.toString());
        
        list.clear();
        System.out.println("\n" + list.toString());
        System.out.println(list.isEmpty());
        
        list.optimize();
        System.out.println("\n" + list.toString());
        
        Student student = new Student(1);
        list.add(new Circle(5.0));
        System.out.println("\n" + list.search(student));
        list.add(student);
        list.add(new Circle(5.0));
        list.add(new Circle(5.0));
        System.out.println(list.search(student));
        System.out.println(list.indexOf(student));
        System.out.println(list.size());
        System.out.println("\n" + list.toString());
    }
}
