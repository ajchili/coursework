package com.kirinpatel.calculator;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.kirinpatel.calculator.util.Calculation;

public class SecondActivity extends AppCompatActivity {

    private Calculation calculation;

    private static final String TAG = "SecondActivity";
    private static final String KEY_EXTRA = "extra";

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
                finish();
            }
        });
    }

    public static Intent newIntent(Context packageContext, Calculation calculation) {
        Log.d(TAG, "newIntent called");
        Intent intent = new Intent(packageContext, SecondActivity.class);
        intent.putExtra(KEY_EXTRA, calculation);
        return intent;
    }
}
