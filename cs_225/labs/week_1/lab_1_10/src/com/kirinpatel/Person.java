/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

/**
 * This class is a container for information pertaining to a human, it is used
 * to store and manipulate various personal statistics.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see java.lang.string
 */
public class Person extends Object implements Cloneable {
    
    private final int SOCIAL_SECURITY_NUMBER;
    private int age;
    private double height;
    private String firstName;
    
    /**
     * Main constructor for the Person class, this will take in a Social 
     * Security Number and create the rest of a Person's statistics.
     * 
     * @param ssn Person's Social Security Number
     */
    public Person(int ssn) {
        this(ssn, 20, 6.0, "John");
    }
    
    /**
     * This is the sub constructor for the Person class.
     * This will be called by the main constructor to set all of the Person's
     * statistics.
     * 
     * @param ssn Person's Social Security Number
     * @param age Person's age in whole years
     * @param height Person's height in feet
     * @param firstName Person's first name
     */
    private Person (int ssn, int age, double height, String firstName) {
        this.SOCIAL_SECURITY_NUMBER = ssn;
        setAge(age);
        setHeight(height);
        setFirstName(firstName);
    }
    
    /**
     * Tests to see if the provided object equals this object.
     * 
     * @param o Object to be tested
     * @return Returns if the two objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Person)) {
            return false;
        } else {
            Person objP = (Person) o;
            return ((this.SOCIAL_SECURITY_NUMBER == objP.SOCIAL_SECURITY_NUMBER) && (this.age == objP.age) && (this.height == objP.height) && (this.firstName.equals(objP.firstName)));
        }
    }
    
   /**
    * This method will set the age based upon the input value.
    * If the input is greater than 0, the age will be set to the input. 
    * Otherwise, the age will be set to -1.
    * 
    * @param age Person's age in whole years
    */
    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        } else {
            // Error handling
            this.age = -1;
        }
    }
    
    
    /**
     * This method will set the height based upon the input value.
     * If the input is greater than 0.0, the height will be set to the input. 
     * Otherwise, the age will be set to -1.
     * 
     * @param height Person's height in feet
     */
    public void setHeight(double height) {
        if (height > 0.0) {
            this.height = height;
        } else {
            // Error handling
            this.height = -1;
        }
    }
    
    /**
     * This method will set the name based upon the input value.
     * If the input is not empty, the name will be set to the input. Otherwise,
     * the name will be set to nothing.
     * 
     * @param firstName Person's first name
     */
    public void setFirstName(String firstName) {
        if (!firstName.isEmpty()) {
            this.firstName = firstName;
        } else {
            // Error handling
            this.firstName = "";
        }
    }
    
    /**
     * Provides the person's Social Security Number.
     * 
     * @return Person's Social Security Number
     */
    public int getSSN() {
        return SOCIAL_SECURITY_NUMBER;
    }
    
    /**
     * Provides the person's age.
     * 
     * @return Person's age in whole years
     */
    public int getAge() {
        return age;
    }
    
    /**
     * Provides the person's height.
     * 
     * @return Person's height in feet
     */
    public double getHeight() {
        return height;
    }
    
    
    /**
     * Provides the person's first name.
     * 
     * @return Person's first name
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     * Provides a new object with identical data to the object provided.
     * 
     * @return Returns a new object with the same data as the object provided
     * @throws java.lang.CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        Person objP = (Person) super.clone();
        return new Person(objP.SOCIAL_SECURITY_NUMBER, objP.age, objP.height, objP.firstName);
    }
}
