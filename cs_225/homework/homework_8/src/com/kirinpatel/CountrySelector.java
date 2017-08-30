/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Kirin Patel
 * @version 1.0
 * @see javax.swing.JFrame
 * @see javax.swing.JTextField
 * @see java.awt.GridLayout
 * @see javax.swing.JList
 * @see javax.swing.event.ListSelectionEvent
 * @see javax.swing.event.ListSelectionListener
 */
public class CountrySelector extends JFrame {
    
    private static JTextField selectedCountries;
    
    private String[] COUNTRIES = {"United States", "Canada", "Mexico", "Britian", "France", "Germany", "Russia", "Vietnam", "India"};
    
    public CountrySelector(String title) {
        super(title);
        
        setLayout(new GridLayout(2, 1));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JList countryList = new JList(COUNTRIES);
        countryList.setVisibleRowCount(3);
        countryList.addListSelectionListener(new ListListener());
        JScrollPane scroll = new JScrollPane(countryList);
        add(scroll);
        
        selectedCountries = new JTextField(40);
        add(selectedCountries);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        CountrySelector window = new CountrySelector("Country Selector");
    }
    
    public class ListListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            JList list = (JList) e.getSource();
            String selectedCountries = "";
            for (int i : list.getSelectedIndices()) 
                selectedCountries += COUNTRIES[i] + ";";
            CountrySelector.selectedCountries.setText(selectedCountries);
        }
    }
}
