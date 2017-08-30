/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kirinpatel
 */
public class B extends A{
    
    private char service;
    
    public B() {
        super();
        System.out.println("B() called");
    }
    
    public void foo1() {
        System.out.println("B version of foo1() called");
    }
    
    protected int foo2() {
        int n = super.foo2();
        System.out.println("B verison of foo2() called");
        return (n + 5);
    }
    
    public String foo3() {
        String temp = super.foo3();
        System.out.println("B version of foo3() called");
        return (temp + " foo3");
    }
}
