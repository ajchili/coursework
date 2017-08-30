/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.util.Random;

/**
 *
 * @author Kirin Patel
 * @version 1.0
 * @see com.kirinpatel.Circle
 * @see com.kirinpatel.Square
 */
public class InterfaceTester {
    
    public static void main(String[] args) {
        Shape a, b;
        Circle c;
        InterfaceTester test = new InterfaceTester();
        Shape[] s = new Shape[5];
        Random random = new Random();
        
        for(int i = 0; i < s.length; i++) {
            if (random.nextBoolean()) {
                double length = random.nextInt(25) + 0.1;
                s[i] = new Square(length, length);
            } else {
                double radius = random.nextInt(25) + 0.1;
                s[i] = new Circle(radius);
            }
        }
        
        a = new Circle(5);
        b = new Square(5, 5);
        c = (Circle) a;
        // c = (Circle) b;
        
        Shape m = test.max(a, b);
        System.out.println("The maximum area was: " + m.area());
        
        // a = new Square(5, 5);
        
        System.out.println("a.compareTo(b) " + a.compareTo(b));
        System.out.println("b.compareTo(a) " + b.compareTo(a));
        
        System.out.print("Array order:");
        for (Shape newShape : s) {
            System.out.print("\t" + newShape.perimeter());
        }
        
        boolean isRunning;
        int iteration = 0;
        Shape shape;
        Shape[] sortedArray = s;
        
        do {
            isRunning = false;
            iteration++;
            for (int i = 0; i < sortedArray.length - iteration; i++) {
                if (sortedArray[i].compareTo(sortedArray[i + 1]) > 0) {
                    shape = sortedArray[i];
                    sortedArray[i] = sortedArray[i + 1];
                    sortedArray[i + 1] = shape;
                    isRunning = true;
                }
            }
        } while(isRunning);
        
        System.out.print("\nSorted array order:");
        for (Shape newShape : sortedArray) {
            System.out.print("\t" + newShape.perimeter());
        }
    }
    
    public Shape max(Shape first, Shape second) {
        if (first.area() > second.area()) {
            return first;
        }
        
        return second;
    }
}
