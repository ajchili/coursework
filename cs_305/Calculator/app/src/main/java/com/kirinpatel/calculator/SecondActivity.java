package com.kirinpatel.calculator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.kirinpatel.calculator.util.Calculation;

public class SecondActivity extends AppCompatActivity {

    private Calculation calculation;

    private static final String TAG = "SecondActivity";
    private static final String KEY_EXTRA = "extra";
    private static final String KEY_RESULT = "result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.d(TAG, "onCreate called");

        calculation = (Calculation) getIntent().getSerializableExtra(KEY_EXTRA);

        Button backButton = findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = new Intent();
                data.putExtra(KEY_RESULT, calculation);
                setResult(Activity.RESULT_OK, data);
                finish();
            }
        });

        Button doubleButton = findViewById(R.id.calc_double);
        doubleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!calculation.getCurrentCalculation().isEmpty()) {
                    calculation.append(" * 2");
                    calculation.calculate();
                } else {
                    Toast.makeText(
                            SecondActivity.this,
                            "Cannot double nothing!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static Intent newIntent(Context packageContext, Calculation calculation) {
        Log.d(TAG, "newIntent called");
        Intent intent = new Intent(packageContext, SecondActivity.class);
        intent.putExtra(KEY_EXTRA, calculation);
        return intent;
    }

    public static Calculation getStoredCalculation(Intent result) {
        return (Calculation) result.getSerializableExtra(KEY_RESULT);
    }
}
