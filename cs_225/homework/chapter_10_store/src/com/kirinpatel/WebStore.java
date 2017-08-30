/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

/**
 * This class is a container for information pertaining to web store.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see com.kirinpatel.Store
 */
public class WebStore extends Store {
    
    private String url;
    private String language;
    
    /**
     * Main constructor that will set the url and programming language of a Web
     * Store.
     * 
     * @param url Web store url
     * @param language Programming language used to create the web store
     */
    public WebStore(String url, String language) {
        super("Web Store");
        setURL(url);
        setLanguage(language);
    }
    
    /**
     * Provides WebStore in string format.
     * 
     * @return Returns printable version of WebStore
     */
    @Override
    public String toString() {
        String returnValue = super.toString();
        returnValue += "\nStore URL: " + getURL();
        returnValue += "\nProgramming language used: " + getLanguage();
        return returnValue;
    }
    
    /**
     * Provides web store url.
     * 
     * @return Returns the url of web store
     */
    public String getURL() {
        return url;
    }
    
    /**
     * Provides programming language used by web store.
     * 
     * @return Returns the programming language used by web store
     */
    public String getLanguage() {
        return language;
    }
    
    /**
     * Sets the web store url.
     * 
     * @param url Web store url
     */
    public void setURL(String url) {
        if (url.length() > 0) {
            this.url = url;
        } else {
            // Error handling
            System.out.println("ERROR: URL MUST BE PROVIDED");
            this.url = "";
        }
    }
    
    /**
     * Sets the programming language used by web store.
     * 
     * @param language Programming language used to create the web store
     */
    public void setLanguage(String language) {
        if (language.length() > 0) {
            this.language = language;
        } else {
            // Error handling
            System.out.println("ERROR: PROGRAMMING LANGUAGE MUST BE PROVIDED");
            this.language = "";
        }
    }
}
