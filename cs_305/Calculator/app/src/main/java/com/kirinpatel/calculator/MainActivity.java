package com.kirinpatel.calculator;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kirinpatel.calculator.util.Calculation;

/**
 * Kirin Patel
 */
public class MainActivity extends AppCompatActivity {

    private TextView previousCalculation;
    private EditText currentCalculation;
    private Calculation calculation = new Calculation();

    private static final String TAG = "MainActivity";
    private static final String KEY_CALCULATION = "calculation";
    private static final int RESULT_CALCULATION = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate called");
        if (savedInstanceState != null) {
            calculation = (Calculation) savedInstanceState.getSerializable(KEY_CALCULATION);
        }

        previousCalculation = findViewById(R.id.calc_previous);
        previousCalculation.setText(calculation.getPreviousCalculation());
        currentCalculation = findViewById(R.id.calc_current);
        currentCalculation.setText(calculation.getCurrentCalculation());

        Button extraButton = findViewById(R.id.calc_extra);
        extraButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivityForResult(
                        SecondActivity.newIntent(MainActivity.this, calculation),
                        RESULT_CALCULATION);
            }
        });

        Button clearButton = findViewById(R.id.calc_clear);
        clearButton.setOnClickListener(
                new CalculatorButtonHandler(Calculation.CalculationType.CLEAR));

        Button calculateButton = findViewById(R.id.calc_calculate);
        calculateButton.setOnClickListener(
                new CalculatorButtonHandler(Calculation.CalculationType.CALCULATE));

        Button addButton = findViewById(R.id.calc_add);
        addButton.setOnClickListener(
                new CalculatorButtonHandler(Calculation.CalculationType.FUNCTION,
                        Calculation.FunctionType.ADD));

        Button subtractButton = findViewById(R.id.calc_subtract);
        subtractButton.setOnClickListener(
                new CalculatorButtonHandler(Calculation.CalculationType.FUNCTION,
                        Calculation.FunctionType.SUBTRACT));

        Button multiplyButton = findViewById(R.id.calc_multiply);
        multiplyButton.setOnClickListener(
                new CalculatorButtonHandler(Calculation.CalculationType.FUNCTION,
                        Calculation.FunctionType.MULTIPLY));

        Button divideButton = findViewById(R.id.calc_divide);
        divideButton.setOnClickListener(
                new CalculatorButtonHandler(Calculation.CalculationType.FUNCTION,
                        Calculation.FunctionType.DIVIDE));

        Button percentButton = findViewById(R.id.calc_percent);
        percentButton.setOnClickListener(
                new CalculatorButtonHandler(Calculation.CalculationType.FUNCTION,
                        Calculation.FunctionType.PERCENT));

        Button signButton = findViewById(R.id.calc_sign);
        signButton.setOnClickListener(
                new CalculatorButtonHandler(Calculation.CalculationType.FUNCTION,
                        Calculation.FunctionType.SIGN));

        Button periodButton = findViewById(R.id.calc_period);
        periodButton.setOnClickListener(
                new CalculatorButtonHandler(Calculation.CalculationType.INPUT, "."));

        Button zeroButton = findViewById(R.id.calc_0);
        zeroButton.setOnClickListener(
                new CalculatorButtonHandler(Calculation.CalculationType.INPUT, "0"));

        Button oneButton = findViewById(R.id.calc_1);
        oneButton.setOnClickListener(
                new CalculatorButtonHandler(Calculation.CalculationType.INPUT, "1"));

        Button twoButton = findViewById(R.id.calc_2);
        twoButton.setOnClickListener(
                new CalculatorButtonHandler(Calculation.CalculationType.INPUT, "2"));

        Button threeButton = findViewById(R.id.calc_3);
        threeButton.setOnClickListener(
                new CalculatorButtonHandler(Calculation.CalculationType.INPUT, "3"));

        Button fourButton = findViewById(R.id.calc_4);
        fourButton.setOnClickListener(
                new CalculatorButtonHandler(Calculation.CalculationType.INPUT, "4"));

        Button fiveButton = findViewById(R.id.calc_5);
        fiveButton.setOnClickListener(
                new CalculatorButtonHandler(Calculation.CalculationType.INPUT, "5"));

        Button sixButton = findViewById(R.id.calc_6);
        sixButton.setOnClickListener(
                new CalculatorButtonHandler(Calculation.CalculationType.INPUT, "6"));

        Button sevenButton = findViewById(R.id.calc_7);
        sevenButton.setOnClickListener(
                new CalculatorButtonHandler(Calculation.CalculationType.INPUT, "7"));

        Button eightButton = findViewById(R.id.calc_8);
        eightButton.setOnClickListener(
                new CalculatorButtonHandler(Calculation.CalculationType.INPUT, "8"));

        Button nineButton = findViewById(R.id.calc_9);
        nineButton.setOnClickListener(
                new CalculatorButtonHandler(Calculation.CalculationType.INPUT, "9"));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState called");
        outState.putSerializable(KEY_CALCULATION, calculation);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == RESULT_CALCULATION) {
            calculation = SecondActivity.getStoredCalculation(data);

            previousCalculation.setText(calculation.getPreviousCalculation());
            currentCalculation.setText(calculation.getCurrentCalculation());
        }
    }

    private class CalculatorButtonHandler implements View.OnClickListener {

        private Calculation.CalculationType type;
        private Calculation.FunctionType functionType;
        private String input;

        CalculatorButtonHandler(Calculation.CalculationType type) {
            this.type = type;
        }

        CalculatorButtonHandler(Calculation.CalculationType type,
                                Calculation.FunctionType functionType) {
            this(type);
            this.functionType = functionType;
        }

        CalculatorButtonHandler(Calculation.CalculationType type, String input) {
            this(type);
            this.input = input;
        }

        @Override
        public void onClick(View view) {
            switch (type) {
                case CLEAR:
                    calculation.clear();
                    break;
                case CALCULATE:
                    calculation.calculate();
                    break;
                case FUNCTION:
                    calculation.appendFunction(functionType);
                    break;
                case INPUT:
                    calculation.append(input);
                    break;
            }

            previousCalculation.setText(calculation.getPreviousCalculation());
            currentCalculation.setText(calculation.getCurrentCalculation());
        }
    }
}
