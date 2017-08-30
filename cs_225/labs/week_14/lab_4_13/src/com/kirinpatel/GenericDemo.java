/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Kirin Patel
 * @version 1.0
 */
public class GenericDemo {
    
    public static void main(String[] args) {
        Integer[] integers = { 1, 2, 3, 4, 5 };
        String[] strings = { "London", "Paris", "New York", "Austin" };
        
        GenericClass<Integer> gc = new GenericClass();
        gc.print(integers);
        System.out.println(gc.average(integers));

        ArrayList list1 = new ArrayList();
        list1.add("Hello");
        list1.add(5);
        list1.add(new java.util.Date());
        
        Date date = (Date) list1.get(2);
        
        ArrayList<String> list2 = new ArrayList();
        list2.add("Hello");
        list2.add("Hello");
        String s = list2.get(1);
        
        GenericDemo.<Integer>print(integers);
        GenericDemo.<String>print(strings);
    }
    
    public static <E> void print(E[] list) {
        for (Object o : list)
            System.out.print(o + " ");
        System.out.println("");
    }
}
