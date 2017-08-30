/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

/**
 * This class contains all the information pertaining to family.
 * 
 * @author Kirin Patel
 * @version 1.0
 */
public class Family implements Cloneable {
    
    Person brother;
    Person sister;
    String lastName;
    
    /**
     * Main constructor that sets the brother, sister, and family name of a
     * family.
     * 
     * @param brother Brother
     * @param sister Sister
     * @param lastName Family name
     */
    public Family(Person brother, Person sister, String lastName) {
        this.brother = brother;
        this.sister = sister;
        this.lastName = lastName;
    }
    
    /**
     * Tests to see if the provided object equals this object.
     * 
     * @param o Object to be tested
     * @return Returns if the two objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Family)) {
            return false;
        } else {
            Family objF = (Family) o;
            return (brother.equals(objF.brother) && sister.equals(objF.sister) && lastName.equals(objF.lastName));
        }
    }
    
    /**
     * Provides a new object with identical data to the object provided.
     * 
     * @return Returns a new object with the same data as the object provided
     * @throws java.lang.CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        Family objF = (Family) super.clone();
        
        objF.brother = (Person) brother.clone();
        objF.sister = (Person) sister.clone();
        objF.lastName = new String(this.lastName);
        
        return objF;
    }
}
