/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kirinpatel
 */
public class F {
    
    private String first;
    protected String name;
    
    public F() {
        
    }
    
    public F(String f, String n) {
        first = f;
        name = n;
    }
    
    public String getFirst() {
        return first;
    }
    
    public String getName() {
        return name;
    }
    
    public String toString() {
        return ("first: " + first + "\tname: " + name);
    }
    
    public boolean equals(Object f) {
        if (!(f instanceof F)) {
            return false;
        } else {
            F objF = (F) f;
            return(first.equals(objF.first) && name.equals(objF.name));
        }
    }
}
