/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

/**
 * This class is a container for information pertaining to my array list.
 * 
 * @author Kirin Patel
 * @version 1.0
 */
public class MyArrayList implements Comparable {

    private Comparable[] arrayList;
    
    /**
     * Main constructor that will create array.
     * 
     */
    public MyArrayList() {
        arrayList = new Comparable[0];
    }
    
    /**
     * Secondary constructor that will create and set array size.
     * 
     * @param size Size of array
     */
    public MyArrayList(int size) {
        if (size > 0) {
            arrayList = new Comparable[size];
        } else {
            // Error handling
            System.out.println("ERROR: ARRAY SIZE MUST BE GREATER THAN 0");
            arrayList = new Comparable[1];
        }
    }
    
    /**
     * Provides MyArrayList in string format.
     * 
     * @return Returns printable version of MyArrayList
     */
    @Override
    public String toString() {
        String returnValue = "ArrayList length: " + arrayList.length;
        returnValue += "\nValues:";
        for (int i = 0; i < arrayList.length; i++) {
            if (arrayList[i] != null) {
                returnValue += "\t" + arrayList[i].toString();
            } else {
                returnValue += "\tnull";
            }
        }
        return returnValue;
    }
    
    /**
     * Tests to see if the provided object equals this object.
     * 
     * @param o Object to be tested
     * @return Returns if the two objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MyArrayList)) {
            return false;
        } else {
            MyArrayList objL = (MyArrayList) o;
            if (objL.size() != size()) {
                return false;
            } else {
                for (int i = 0; i < arrayList.length; i++) {
                    if (!arrayList[i].equals(objL.get(i))) {
                        return false;
                    }
                }
                
                return true;
            }
        }
    }
    
    /**
     * Provides object at given position.
     * 
     * @param position Position in array
     * @return Object at given position
     */
    public Comparable get(int position) {
        return arrayList[position];
    }
    
    /**
     * Provides index of given object.
     * 
     * @param c Object
     * @return Index of given object
     */
    public int indexOf(Comparable c) {
        for (int i = 0; i < arrayList.length; i++) {
            if (c.equals(arrayList[i])) {
                return i;
            }
        }
        
        return -1;
    }
    
    /**
     * Provides if the array is empty.
     * 
     * @return Is array empty
     */
    public boolean isEmpty() {
        for (int i = 0; i < arrayList.length; i++) {
            if (arrayList[i] != null) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Provides size of array.
     * 
     * @return Size of array
     */
    public int size() {
        return arrayList.length;
    }
    
    /**
     * Provides if an object is in the array.
     * 
     * @param c Object
     * @return Is object in array
     */
    public boolean search(Comparable c) {
        for (Comparable compare : arrayList) {
            if (compare.equals(c)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Adds object to array.
     * 
     * @param c Object
     */
    public void add(Comparable c) {
        Comparable[] oldArrayList = arrayList;
        arrayList = new Comparable[arrayList.length + 1];
        
        for (int i = 0; i < oldArrayList.length; i++) {
            arrayList[i] = oldArrayList[i];
        }
        
        arrayList[arrayList.length - 1] = c;
    }
    
    /**
     * Sets the given object to the specified index.
     * 
     * @param position Index
     * @param c Object
     */
    public void set(int position, Comparable c) {
        arrayList[position] = c;
    }
    
    /**
     * Removes object at given index.
     * 
     * @param position Index
     */
    public void remove(int position) {
        Comparable[] newArrayList = new Comparable[arrayList.length - 1];
        
        for (int i = 0; i < position; i++) {
            newArrayList[i] = arrayList[i];
        }
        
        for (int i = (position + 1); i < arrayList.length; i++) {
            newArrayList[i - 1] = arrayList[i];
        }
        
        arrayList = newArrayList;
    }
    
    /**
     * Clears array.
     * 
     */
    public void clear() {
        for (int i = 0; i < arrayList.length; i++) {
            arrayList[i] = null;
        }
    }
    
    /**
     * Sets the array size to number of objects it contains.
     * 
     */
    public void optimize() {
        int length = 0;
        
        for (int i = 0; i < arrayList.length; i++) {
            if (arrayList[i] != null) {
                length++;
            }
        }
        
        Comparable[] newArrayList = new Comparable[length];
        
        for (int i = 0; i < newArrayList.length; i++) {
            for (int j = 0; j < arrayList.length; j++) {
                if (arrayList[j] != null) {
                    newArrayList[i] = arrayList[j];
                    arrayList[j] = null;
                    break;
                }
            }
        }
        
        arrayList = newArrayList;
    }
    
    /**
     * Compares size of array with given object.
     * 
     * @param o Object
     * @return Which array is larger
     */
    @Override
    public int compareTo(Object o) {
        if (!(o instanceof MyArrayList)) {
            return 0;
        } else {
            MyArrayList objA = (MyArrayList) o;
            return size() - objA.size();
        }
    }
    
}
