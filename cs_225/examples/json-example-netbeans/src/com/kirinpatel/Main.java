/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import com.journaldev.model.*;
import java.util.Random;
import javax.json.Json;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author Kirin Patel
 * @version 1.0
 */
public class Main {
    
    public static void main(String[] args) {
        Employee[] employees = new Employee[5];
        
        for (Employee employee : employees) {
            employee = createEmployee();
        }
    }
    
    public static Employee createEmployee() {
        Employee emp = new Employee();
        
        Random random = new Random();
        
        emp.setId(random.nextInt(100) + 1);
        emp.setName("David");
        emp.setPermanent(false);
        emp.setPhoneNumbers(new long[] { random.nextLong(), 987654 });
        if (random.nextBoolean())
            emp.setRole("Manager");
        else
            emp.setRole("Clerk");

        Address add = new Address();
        add.setCity("Bangalore");
        add.setStreet("BTM 1st Stage");
        add.setZipcode(560100);
        emp.setAddress(add);

        return emp;
    }
    
    public void writeToFile() {
        JsonObjectBuilder empBuilder = Json.createObjectBuilder();
    }
    
    public void readFromFile() {
        
    }
}
