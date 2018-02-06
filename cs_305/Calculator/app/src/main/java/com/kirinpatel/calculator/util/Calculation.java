package com.kirinpatel.calculator.util;

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
    private float calculatedValue = 0f;

    public void clear() {
        if (currentCalculation.length() == 0) {
            previousCalculation = "";
        } else {
            currentCalculation = "";
        }
    }

    public void calculate() {
        if (currentCalculation.isEmpty()) {
            return;
        }

        String previousCalc = previousCalculation;
        String calculation = currentCalculation;
        boolean usedPrevious = false;

        char initialChar = calculation.charAt(0);
        if (initialChar == '+' || initialChar == '-' || initialChar == '*'
                || initialChar == '/'
                || initialChar == '%') {
            if (previousCalc.isEmpty()) {
                calculation = calculation.substring(2);
            } else {
                    calculation = calculatedValue + " " + calculation;
                    usedPrevious = true;
            }
        }

        calculation = calculate(calculation);

        if (usedPrevious) {
            previousCalculation = calculatedValue + " " + currentCalculation;
        } else {
            previousCalculation = currentCalculation;
        }
        calculatedValue = Float.parseFloat(calculation);
        if (calculatedValue == (int) calculatedValue) {
            currentCalculation = String.valueOf((int) calculatedValue);
        } else {
            currentCalculation = calculation;
        }
    }

    private String calculate(String calculation) {
        while (calculation.contains("*") || calculation.contains("/")) {
            int startIndex = 0;
            int endIndex = calculation.length() - 1;

            if (calculation.indexOf('*') != -1) {
                int index = calculation.indexOf("*");

                for (int i = index - 1; i > 0; i--) {
                    if (calculation.substring(i - 1, i).equals(" ")) {
                        startIndex = i;
                        break;
                    }
                }
                for (int i = index + 2; i < calculation.length(); i++) {
                    if (calculation.substring(i, i + 1).equals(" ")) {
                        endIndex = i;
                        break;
                    }
                }

                if (isLastOperation(calculation)) {
                    String section = calculation;
                    float firstElement = Float.parseFloat(
                            section.substring(0, section.indexOf(' ')));
                    float lastElement = Float.parseFloat(
                            section.substring(section.lastIndexOf(' ')));
                    calculation = String.valueOf(firstElement * lastElement);
                } else {
                    String section = calculation.substring(startIndex, endIndex);
                    float firstElement = Float.parseFloat(
                            section.substring(0, section.indexOf(' ')));
                    float lastElement = Float.parseFloat(
                            section.substring(section.lastIndexOf(' ')));
                    calculation = calculation.substring(0, startIndex)
                            + (firstElement * lastElement)
                            + calculation.substring(endIndex);
                }
            } else if (calculation.indexOf('/') != -1) {
                int index = calculation.indexOf("/");

                for (int i = index - 1; i > 0; i--) {
                    if (calculation.substring(i - 1, i).equals(" ")) {
                        startIndex = i;
                        break;
                    }
                }
                for (int i = index + 2; i < calculation.length(); i++) {
                    if (calculation.substring(i, i + 1).equals(" ")) {
                        endIndex = i;
                        break;
                    }
                }

                if (isLastOperation(calculation)) {
                    String section = calculation;
                    float firstElement = Float.parseFloat(
                            section.substring(0, section.indexOf(' ')));
                    float lastElement = Float.parseFloat(
                            section.substring(section.lastIndexOf(' ')));
                    calculation = String.valueOf(firstElement / lastElement);
                } else {
                    String section = calculation.substring(startIndex, endIndex);
                    float firstElement = Float.parseFloat(
                            section.substring(0, section.indexOf(' ')));
                    float lastElement = Float.parseFloat(
                            section.substring(section.lastIndexOf(' ')));
                    calculation = calculation.substring(0, startIndex)
                            + (firstElement / lastElement)
                            + calculation.substring(endIndex);
                }
            }
        }

        while (calculation.contains("%")) {
            int startIndex = 0;
            int index = calculation.indexOf("%");
            int endIndex = calculation.length() - 1;

            for (int i = index - 1; i > 0; i--) {
                if (calculation.substring(i - 1, i).equals(" ")) {
                    startIndex = i;
                    break;
                }
            }
            for (int i = index + 2; i < calculation.length(); i++) {
                if (calculation.substring(i, i + 1).equals(" ")) {
                    endIndex = i;
                    break;
                }
            }

            if (isLastOperation(calculation)) {
                String section = calculation;
                float firstElement = Float.parseFloat(
                        section.substring(0, section.indexOf(' ')));
                float lastElement = Float.parseFloat(
                        section.substring(section.lastIndexOf(' ')));
                calculation = String.valueOf(firstElement % lastElement);
            } else {
                String section = calculation.substring(startIndex, endIndex);
                float firstElement = Float.parseFloat(
                        section.substring(0, section.indexOf(' ')));
                float lastElement = Float.parseFloat(
                        section.substring(section.lastIndexOf(' ')));
                calculation = calculation.substring(0, startIndex)
                        + (firstElement % lastElement)
                        + calculation.substring(endIndex);
            }
        }

        while (calculation.contains("+") || calculation.contains("-")) {
            int startIndex = 0;
            int endIndex = calculation.length() - 1;

            if (calculation.indexOf('+') != -1) {
                int index = calculation.indexOf("+");

                for (int i = index - 1; i > 0; i--) {
                    if (calculation.substring(i - 1, i).equals(" ")) {
                        startIndex = i;
                        break;
                    }
                }
                for (int i = index + 2; i < calculation.length(); i++) {
                    if (calculation.substring(i, i + 1).equals(" ")) {
                        endIndex = i;
                        break;
                    }
                }

                if (isLastOperation(calculation)) {
                    String section = calculation;
                    float firstElement = Float.parseFloat(
                            section.substring(0, section.indexOf(' ')));
                    float lastElement = Float.parseFloat(
                            section.substring(section.lastIndexOf(' ')));
                    calculation = String.valueOf(firstElement + lastElement);
                } else {
                    String section = calculation.substring(startIndex, endIndex);
                    float firstElement = Float.parseFloat(
                            section.substring(0, section.indexOf(' ')));
                    float lastElement = Float.parseFloat(
                            section.substring(section.lastIndexOf(' ')));
                    calculation = calculation.substring(0, startIndex)
                            + (firstElement + lastElement)
                            + calculation.substring(endIndex);
                }
            } else if (calculation.indexOf('-') != -1) {
                if (!isOperable(calculation)) {
                    return calculation;
                }

                int index = calculation.indexOf("-");

                for (int i = index - 1; i > 0; i--) {
                    if (calculation.substring(i - 1, i).equals(" ")) {
                        startIndex = i;
                        break;
                    }
                }
                for (int i = index + 2; i < calculation.length(); i++) {
                    if (calculation.substring(i, i + 1).equals(" ")) {
                        endIndex = i;
                        break;
                    }
                }

                if (isLastOperation(calculation)) {
                    String section = calculation;
                    System.out.println(section);
                    float firstElement = Float.parseFloat(
                            section.substring(0, section.indexOf(' ')));
                    float lastElement = Float.parseFloat(
                            section.substring(section.lastIndexOf(' ')));
                    calculation = String.valueOf(firstElement - lastElement);
                } else {
                    String section = calculation.substring(startIndex, endIndex);
                    float firstElement = Float.parseFloat(
                            section.substring(0, section.indexOf(' ')));
                    float lastElement = Float.parseFloat(
                            section.substring(section.lastIndexOf(' ')));
                    calculation = calculation.substring(0, startIndex)
                            + (firstElement - lastElement)
                            + calculation.substring(endIndex);
                }
            }
        }

        return calculation;
    }

    private boolean isOperable(String calculation) {
        return calculation.contains(" ");
    }

    private boolean isLastOperation(String calculation) {
        int numberOfSpaces = 0;

        for (char c : calculation.toCharArray()) {
            if (c == ' ') {
                numberOfSpaces++;
            }

            if (numberOfSpaces > 2) {
                return false;
            }
        }

        return true;
    }

    public void append(String text) {
        currentCalculation += text;
    }

    public void appendFunction(FunctionType functionType) {
        String previousFunction = currentCalculation.length() >= 2
                ? currentCalculation.substring(currentCalculation.length() - 2)
                : currentCalculation;
        boolean shouldReplaceFunction = previousFunction.length() == 2
                && previousFunction.substring(1).equals(" ");
        switch (functionType) {
            case ADD:
                if (shouldReplaceFunction) {
                    currentCalculation =
                            currentCalculation.substring(0, currentCalculation.length() - 2) + "+ ";
                } else {
                    if (currentCalculation.isEmpty()) {
                        append("+ ");
                    } else {
                        append(" + ");
                    }
                }
                break;
            case SUBTRACT:
                if (shouldReplaceFunction) {
                    currentCalculation =
                            currentCalculation.substring(0, currentCalculation.length() - 2) + "- ";
                } else {
                    if (currentCalculation.isEmpty()) {
                        append("- ");
                    } else {
                        append(" - ");
                    }
                }
                break;
            case MULTIPLY:
                if (shouldReplaceFunction) {
                    currentCalculation =
                            currentCalculation.substring(0, currentCalculation.length() - 2) + "* ";
                } else {
                    if (currentCalculation.isEmpty()) {
                        append("* ");
                    } else {
                        append(" * ");
                    }
                }
                break;
            case DIVIDE:
                if (shouldReplaceFunction) {
                    currentCalculation =
                            currentCalculation.substring(0, currentCalculation.length() - 2) + "/ ";
                } else {
                    if (currentCalculation.isEmpty()) {
                        append("/ ");
                    } else {
                        append(" / ");
                    }
                }
                break;
            case PERCENT:
                if (shouldReplaceFunction) {
                    currentCalculation =
                            currentCalculation.substring(0, currentCalculation.length() - 2) + "% ";
                } else {
                    if (currentCalculation.isEmpty()) {
                        append("% ");
                    } else {
                        append(" % ");
                    }
                }
                break;
            case SIGN:
                if (!currentCalculation.isEmpty()
                        && !currentCalculation.substring(1, 2).equals(" ")) {
                    if (currentCalculation.length() >= 2
                            && currentCalculation.substring(0, 1).equals("-")) {
                        currentCalculation = currentCalculation.substring(2);
                    } else {
                        currentCalculation = "-" + currentCalculation;
                    }
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
