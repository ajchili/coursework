package com.kirinpatel.calculator;

import com.kirinpatel.calculator.util.Calculation;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class CalculationTest {

    Calculation calculation;

    @Before
    public void setup() {
        calculation = new Calculation();
    }

    @Test
    public void testAddition() {
        calculation.append("4");
        calculation.appendFunction(Calculation.FunctionType.ADD);
        calculation.append("3");
        calculation.calculate();
        assertEquals(7, Integer.parseInt(calculation.getCurrentCalculation()));
    }

    @Test
    public void testSubtraction() {
        calculation.append("8");
        calculation.appendFunction(Calculation.FunctionType.SUBTRACT);
        calculation.append("3");
        calculation.calculate();
        assertEquals(5, Integer.parseInt(calculation.getCurrentCalculation()));
    }

    @Test
    public void testMultiplication() {
        calculation.append("4");
        calculation.appendFunction(Calculation.FunctionType.MULTIPLY);
        calculation.append("6");
        calculation.calculate();
        assertEquals(24, Integer.parseInt(calculation.getCurrentCalculation()));
    }

    @Test
    public void testDivision() {
        calculation.append("9");
        calculation.appendFunction(Calculation.FunctionType.DIVIDE);
        calculation.append("3");
        calculation.calculate();
        assertEquals(3, Integer.parseInt(calculation.getCurrentCalculation()));
    }

    @Test
    public void testPercent() {
        calculation.append("7");
        calculation.appendFunction(Calculation.FunctionType.PERCENT);
        calculation.append("2");
        calculation.calculate();
        assertEquals(1, Integer.parseInt(calculation.getCurrentCalculation()));
    }

    @Test
    public void testSign() {
        calculation.append("10");
        calculation.appendFunction(Calculation.FunctionType.SIGN);
        assertEquals(-10, Integer.parseInt(calculation.getCurrentCalculation()));
        calculation.appendFunction(Calculation.FunctionType.SIGN);
        assertEquals(10, Integer.parseInt(calculation.getCurrentCalculation()));
    }
}
