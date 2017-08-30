/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * This class is a tester for all of the Store classes in an ArrayList.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see com.kirinpatel.Store
 */
public class StoreTester {
    
    /**
     * Tests all of the Store classes in an ArrayList.
     * 
     * @param args Main arguments
     */
    public static void main(String[] args) {
        // Initialize variables
        ArrayList<Store> arrayList = new ArrayList<>();
        BikeStore bikeStore = new BikeStore(1, false);
        GroceryStore groceryStore = new GroceryStore(1.0, false);
        MusicStore musicStore = new MusicStore(1, " ");
        Restaurant restaurant = new Restaurant(1, 1.0);
        WebStore webStore = new WebStore(" ", " ");
        
        // Set information
        arrayList.add(musicStore);
        arrayList.add(groceryStore);
        arrayList.add(restaurant);
        arrayList.add(webStore);
        arrayList.add(bikeStore);
        
        // Display infromation
        System.out.print("Store order: ");
        for (Store store : arrayList) {
            System.out.print("  " + store.getName());
        }
        
        // Sort and re-display information
        sortArray(arrayList);
        System.out.print("\nNew store order: ");
        for (Store store : arrayList) {
            System.out.print("  " + store.getName());
        }
    }
    
    /**
     * Sorts an ArrayList of stores based on their name.
     * 
     * @param arrayList ArrayList to be sorted
     */
    public static void sortArray(ArrayList<Store> arrayList) {
        Collections.sort(arrayList, new Comparator<Store>() {
            @Override
            public int compare(Store store1, Store store2) {
                return store1.getName().compareTo(store2.getName());
            }
        });
    }
}
