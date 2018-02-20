package com.kirinpatel.criminalintent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.kirinpatel.criminalintent.util.Crime;

public class CrimeActivity extends AppCompatActivity {

    private Crime crime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime);
    }
}
