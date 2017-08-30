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
public class Sets {
    
    public static void main(String[] args) {
        Set<String> s1 = new HashSet();
        
        s1.add("Damien");
        s1.add("Jeffrey");
        s1.add("Dan");
        s1.add("Jeffrey");
        
        Set<String> s2 = new LinkedHashSet();
        
        s2.add("Damien");
        s2.add("Jeffrey");
        s2.add("Dan");
        s2.add("Jeffrey");
        
        Set<String> s3 = new TreeSet(s2);
        
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
    }
}
