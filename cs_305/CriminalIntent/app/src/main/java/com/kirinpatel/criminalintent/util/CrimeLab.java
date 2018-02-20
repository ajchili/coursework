package com.kirinpatel.criminalintent.util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeLab {

    private static CrimeLab lab;
    private List<Crime> crimes;

    public static CrimeLab get() {
        if (lab == null) {
            lab = new CrimeLab();
        }

        return lab;
    }

    private CrimeLab() {
        crimes = new ArrayList<>();

        for (int i = 1; i <= 100; i++) {
            crimes.add(new Crime("Crime #" + i, i % 2 == 0));
        }
    }

    public List<Crime> getCrimes() {
        return crimes;
    }

    public Crime getcrime(UUID uid) {
        for (Crime crime : crimes) {
            if (crime.getUid().equals(uid)) {
                return crime;
            }
        }

        return null;
    }
}
