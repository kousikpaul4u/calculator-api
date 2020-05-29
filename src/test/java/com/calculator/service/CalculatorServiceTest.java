package com.calculator.service;

import com.calculator.constance.StatusConstants;
import com.calculator.exception.UtilException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorServiceTest {

    @InjectMocks
    private CalculatorService calculatorService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testCalculateSuccess() {
        String input = "5+2";
        String expected = "7.0";
        String output = calculatorService.calculate(input);
        Assert.assertEquals(expected, output);
    }

    @Test
    public void testCalculateFailureWithUtilException() {
        String input = "";

        expectedException.expect(UtilException.class);
        expectedException.expectMessage(StatusConstants.HttpConstants.EMPTY_INPUT.getDesc());

        String output = calculatorService.calculate(input);
        Assert.assertNull(output);
    }

    @Test
    public void testCalculateFailureWithException() {
        String input = null;

        expectedException.expect(Exception.class);
        expectedException.expectMessage(StatusConstants.HttpConstants.EMPTY_INPUT.getDesc());

        String output = calculatorService.calculate(input);
        Assert.assertNull(output);
    }

}
