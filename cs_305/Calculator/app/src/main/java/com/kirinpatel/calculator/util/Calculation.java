package com.kirinpatel.calculator.util;

import com.kirinpatel.calculator.R;

import java.io.Serializable;

public class Calculation implements Serializable {

    public enum CalculationType {
        CLEAR, CALCULATE, FUNCTION, INPUT
    }

    public enum FunctionType {
        ADD, SUBTRACT, MULTIPLY, DIVIDE, PERCENT, SIGN
    }

    private String previousCalculation = "";
    private String currentCalculation = "";

    public void clear() {
        if (currentCalculation.length() == 0) {
            previousCalculation = "";
        } else {
            currentCalculation = "";
        }
    }

    public void calculate() {

    }

    public void append(String text) {
        currentCalculation += text;
    }

    public void appendFunction(FunctionType functionType) {
        boolean shouldReplaceFunction = currentCalculation.length() == 2
                && currentCalculation.contains(" ");
        switch (functionType) {
            case ADD:
                if (shouldReplaceFunction) {
                    if (currentCalculation.isEmpty()) {
                        append("+ ");
                    } else {
                        append(" + ");
                    }
                } else {
                    currentCalculation = "+ ";
                }
                break;
            case SUBTRACT:
                if (shouldReplaceFunction) {
                    if (currentCalculation.isEmpty()) {
                        append("- ");
                    } else {
                        append(" - ");
                    }
                } else {
                    currentCalculation = "- ";
                }
                break;
            case MULTIPLY:
                if (shouldReplaceFunction) {
                    if (currentCalculation.isEmpty()) {
                        append(R.string.calc_multiply + " ");
                    } else {
                        append(" " + R.string.calc_multiply + " ");
                    }
                } else {
                    currentCalculation = R.string.calc_multiply + " ";
                }
                break;
            case DIVIDE:
                if (shouldReplaceFunction) {
                    if (currentCalculation.isEmpty()) {
                        append(R.string.calc_divide + " ");
                    } else {
                        append(" " + R.string.calc_divide + " ");
                    }
                } else {
                    currentCalculation = R.string.calc_divide + " ";
                }
                break;
            case PERCENT:
                if (shouldReplaceFunction) {
                    if (currentCalculation.isEmpty()) {
                        append("% ");
                    } else {
                        append(" % ");
                    }
                } else {
                    currentCalculation = "% ";
                }
                break;
            case SIGN:
                if (!shouldReplaceFunction && !currentCalculation.isEmpty()) {

                }
                break;
        }
    }

    public String getPreviousCalculation() {
        return previousCalculation;
    }

    public String getCurrentCalculation() {
        return currentCalculation;
    }
}
