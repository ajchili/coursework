/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * This class is a tester for the ArrayList class.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see java.util.ArrayList
 */
public class ArrayListTester {
    
    /**
     * Tests the ArrayList class.
     * 
     * @param args Main arguments
     */
    public static void main(String[] args) {
        // Initialize variables
        ArrayList a = new ArrayList();
        ArrayList<Object> arrayListObjects = new ArrayList<>();
        ArrayList<String> words = new ArrayList<>(10000);
        Scanner s = new Scanner(System.in);
        
        // Ask for boolean
        System.out.println("Are you over 21?");
        while(!s.hasNextBoolean()) {
            // Error handling
            System.out.println("Are you over 21? (true/false)");
            s.next();
        }
        // Print results
        System.out.println("Your response was " + s.nextBoolean());
        
        a.add("Hello");
        a.add(new Date());
        // a.add(new Integer(99));
        a.add(99);
        
        words.add("Hello");
        words.add("Goodbye");
        words.add("Java is amazing");
        
        /* Integer number = (Integer) a.get(2);
           int i = number.intValue(); */
        int i = (int) a.get(2);
    }
    
}
