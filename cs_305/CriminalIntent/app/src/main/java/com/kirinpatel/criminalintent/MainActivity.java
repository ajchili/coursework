package com.kirinpatel.criminalintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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

    @Override
    public void onResume() {
        super.onResume();
        updateUi();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_crime, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_new_crime:
                Crime crime = new Crime();
                CrimeLab.get(MainActivity.this).addCrime(crime);
                Intent intent = CrimeActivity.newIntent(MainActivity.this, crime.getUid());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateUi() {
        List<Crime> crimes = CrimeLab.get(MainActivity.this).getCrimes();

        crimeAdapter = new CrimeAdapter(crimes);
        recyclerView.setAdapter(crimeAdapter);
    }
}
