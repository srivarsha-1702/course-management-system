package com.aj.employee.service;

import com.aj.employee.service.utils.Calculator;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
    Calculator calculator;
    @BeforeEach
    public void setUp() {
//        Arrange
        calculator = new Calculator();
    }
    @Test
    public void testMultiply() {
        Calculator calculator = new Calculator();
//        Act
        Integer result = calculator.multiply(2, 3);
//        Assert
        assertEquals(6, result);
    }
    @Test
    public void testDivide() {
//        Act
        Integer result = calculator.divide(6, 0);
        assertEquals(0, result);
    }
}
