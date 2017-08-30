/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.util.*;

/**
 *
 * @author Kirin Patel
 * @version 1.0
 */
public class Lists {
    
    public static void main(String[] args) {
        List<String> l1 = new ArrayList();
        List<Integer> l2 = new LinkedList();
        
        l1.add("A");
        l1.add("b");
        l1.add("Java");
        
        l2.add(1);
        l2.add(5);
        
        print(l1);
        print(l2);
    }
    
    public static <E> void print(List<E> list) {
        for (Object o : list) {
            System.out.print(o + " ");
        }
        System.out.println();
    }
}
