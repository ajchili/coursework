/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

/**
 *
 * @author Kirin Patel
 * @version 1.0
 */
public class GenericClass<E> {
    
    private E[] vehicles;
    
    public void print(E[] list) {
        for (Object o : list) {
            System.out.print(o + " ");
        }
        System.out.println();
    }
    
    public double average(E[] array) {
        double total = 0;
        
        for (int i = 0; i < array.length; i++)
            total += Double.parseDouble(array[i].toString());
        
        return total / array.length;
    }
}
