package com.kirinpatel.criminalintent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kirinpatel.criminalintent.adapters.CrimeAdapter;
import com.kirinpatel.criminalintent.util.Crime;
import com.kirinpatel.criminalintent.util.CrimeLab;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CrimeAdapter crimeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.main_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        updateUi();
    }

    private void updateUi() {
        List<Crime> crimes = CrimeLab.get().getCrimes();

        crimeAdapter = new CrimeAdapter(crimes);
        recyclerView.setAdapter(crimeAdapter);
    }
}
