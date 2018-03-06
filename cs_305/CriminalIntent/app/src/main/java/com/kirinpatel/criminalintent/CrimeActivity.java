package com.kirinpatel.criminalintent;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;

import com.kirinpatel.criminalintent.util.Crime;
import com.kirinpatel.criminalintent.util.CrimeLab;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

public class CrimeActivity extends AppCompatActivity {

    private Crime crime;
    private static final String KEY_UUID = "uuid";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime);

        UUID uuid = (UUID) getIntent().getSerializableExtra(KEY_UUID);

        final TextView title = findViewById(R.id.crime_detail_title);
        final Button date = findViewById(R.id.crime_detail_date);
        final CheckBox solved = findViewById(R.id.crime_detail_solved);
        Button save = findViewById(R.id.crime_detail_save);

        crime = CrimeLab.get(CrimeActivity.this).getCrime(uuid);

        title.setText(crime.getTitle());
        date.setText(crime.getDate().toString());
        solved.setChecked(crime.isSolved());

        date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final View dialogView = LayoutInflater.from(CrimeActivity.this)
                        .inflate(R.layout.dialog_date_picker, null);
                final DatePicker datePicker = dialogView.findViewById(R.id.dialog_picker);
                datePicker.init(1900 + crime.getDate().getYear(),
                        crime.getDate().getMonth(),
                        crime.getDate().getDate(),
                        new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                        int year = datePicker.getYear();
                        int month = datePicker.getMonth();
                        int day = datePicker.getDayOfMonth();

                        Date selectedDate = new GregorianCalendar(year, month, day).getTime();
                        crime.setDate(selectedDate);
                        date.setText(crime.getDate().toString());
                    }
                });

                Dialog dialog = new AlertDialog.Builder(CrimeActivity.this)
                        .setTitle(R.string.crime_date)
                        .setView(dialogView)
                        .setPositiveButton(android.R.string.ok, null)
                        .setNeutralButton(android.R.string.cancel, null)
                        .create();
                dialog.show();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                crime.setTitle(title.getText().toString());
                crime.setSolved(solved.isChecked());
                CrimeLab.get(CrimeActivity.this).updateCrime(crime);
                finish();
            }
        });
    }

    public static Intent newIntent(Context context, UUID uid) {
        Intent intent = new Intent(context, CrimeActivity.class);
        intent.putExtra(KEY_UUID, uid);
        return intent;
    }
}
