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
 * @see com.kirinpatel.Shape
 * @see Math
 */
public class Circle implements Shape {
    
    double radius;
    
    public Circle(double radius) {
        setRadius(radius);
    }
    
    @Override
    public double area() {
        return Math.PI * Math.pow(radius, 2);
    }
    
    @Override
    public double perimeter() {
        return Math.PI * radius * 2;
    }
    
    public double getRadius() {
        return radius;
    }
    
    public void setRadius(double radius) {
        if (radius > 0.0) {
            this.radius = radius;
        } else {
            // Error handling
            System.out.println("ERROR: RADIUS MUST BE GREATER THAN 0");
            this.radius = -1.0;
        }
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Shape)) {
            return 0;
        } else {
            Shape objS = (Shape) o;
            return (int) (perimeter() - objS.perimeter());
        }
    }
}
