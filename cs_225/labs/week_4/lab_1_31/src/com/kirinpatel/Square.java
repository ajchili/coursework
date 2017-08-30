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
 */
public class Square implements Shape {
    
    private double length, width;
    
    public Square(double length, double width) {
        setLength(length);
        setWidth(width);
    }
    
    @Override
    public double area() {
        return length * width;
    }
    
    @Override
    public double perimeter() {
        return (length * 2) + (width * 2);
    }
    
    public double getLength() {
        return length;
    }
    
    public double getWidth() {
        return width;
    }
    
    public void setLength(double length) {
        if (length > 0.0) {
            this.length = length;
        } else {
            // Error handling
            System.out.println("ERROR: LENGTH MUST BE GREATER THAN 0");
            this.length = -1.0;
        }
    }
    
    public void setWidth(double width) {
        if (width > 0.0) {
            this.width = width;
        } else {
            // Error handling
            System.out.println("ERROR: WIDTH MUST BE GREATER THAN 0");
            this.width = -1.0;
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
