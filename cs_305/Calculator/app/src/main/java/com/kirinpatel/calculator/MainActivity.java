package com.kirinpatel.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView previousCalculation;
    private EditText currentCalculation;

    public enum ButtonType {
        CLEAR, CALCULATE, FUNCTION, INPUT;
    }

    public enum FunctionType {
        ADD, SUBTRACT, MULTIPLY, DIVIDE, PERCENT;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        previousCalculation = findViewById(R.id.calc_previous);
        currentCalculation = findViewById(R.id.calc_current);

        Button clearButton = findViewById(R.id.calc_clear);
        clearButton.setOnClickListener(new CalculatorButtonHandler(ButtonType.CLEAR));

        Button calculateButton = findViewById(R.id.calc_calculate);
        calculateButton.setOnClickListener(new CalculatorButtonHandler(ButtonType.CALCULATE));
    }

    private class CalculatorButtonHandler implements View.OnClickListener {

        private ButtonType type;
        private FunctionType functionType;
        private String input;

        public CalculatorButtonHandler(ButtonType type) {
            this.type = type;
        }

        public CalculatorButtonHandler(ButtonType type, FunctionType functionType) {
            this(type);
            this.functionType = functionType;
        }

        public CalculatorButtonHandler(ButtonType type, String input) {
            this(type);
            this.input = input;
        }

        @Override
        public void onClick(View view) {
            switch(type) {
                case CLEAR:
                    currentCalculation.setText("");
                    break;
                case CALCULATE:
                    break;
                case FUNCTION:
                    switch (functionType) {
                        case ADD:
                            break;
                        case SUBTRACT:
                            break;
                        case MULTIPLY:
                            break;
                        case DIVIDE:
                            break;
                        case PERCENT:
                            break;
                    }
                    break;
                case INPUT:
                    currentCalculation.setText(currentCalculation.getText().append(input));
                    break;
            }
        }
    }
}
