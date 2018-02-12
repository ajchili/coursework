package com.kirinpatel.criminalintent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kirinpatel.criminalintent.util.Crime;
import com.kirinpatel.criminalintent.util.CrimeLab;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Crime> crimes = CrimeLab.get(getApplicationContext()).getCrimes();
    }
}
