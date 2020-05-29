package com.calculator.util;

import com.calculator.constance.StatusConstants;
import com.calculator.exception.UtilException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorUtilTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testCalculateSuccess1() {
        String input = "3+2*5";
        String output = CalculatorUtil.calculate(input);
        Assert.assertEquals("13.0", output);
    }

    @Test
    public void testCalculateSuccess2() {
        String input = "3-2*5";
        String output = CalculatorUtil.calculate(input);
        Assert.assertEquals("-7.0", output);
    }

    @Test
    public void testCalculateSuccess3() {
        String input = "3-6/2";
        String output = CalculatorUtil.calculate(input);
        Assert.assertEquals("0.0", output);
    }

    @Test
    public void testCalculateSuccess4() {
        String input = "3-6+2";
        String output = CalculatorUtil.calculate(input);
        Assert.assertEquals("-1.0", output);
    }

    @Test
    public void testCalculateSuccess5() {
        String input = "3-6+2-10+1-12";
        String output = CalculatorUtil.calculate(input);
        Assert.assertEquals("-22.0", output);
    }

    @Test
    public void testCalculateSuccess6() {
        String input = "3.0-6.5+2-10+1-12";
        String output = CalculatorUtil.calculate(input);
        Assert.assertEquals("-22.5", output);
    }

    @Test
    public void testCalculateSuccess7() {
        String input = "3.0-6.5*2-10+1-12";
        String output = CalculatorUtil.calculate(input);
        Assert.assertEquals("-31.0", output);
    }

    @Test
    public void testCalculateSuccess8() {
        String input = "3.0-6.5*2-10/1-12";
        String output = CalculatorUtil.calculate(input);
        Assert.assertEquals("-32.0", output);
    }

    @Test
    public void testCalculateSuccess9() {
        String input = "3.0-6.5*2-10/1-12/4";
        String output = CalculatorUtil.calculate(input);
        Assert.assertEquals("-23.0", output);
    }

    @Test
    public void testCalculateSuccess10() {
        String input = "3.0-6.5*2+12*9.5-10/1-4*3+12/4";
        String output = CalculatorUtil.calculate(input);
        Assert.assertEquals("85.0", output);
    }

    @Test
    public void testCalculateSuccess11() {
        String input = "-3+2";
        String output = CalculatorUtil.calculate(input);
        Assert.assertEquals("-1.0", output);
    }

    @Test
    public void testCalculateSuccess12() {
        String input = "-3+2*20-2/2";
        String output = CalculatorUtil.calculate(input);
        Assert.assertEquals("36.0", output);
    }

    @Test
    public void testCalculateSuccess13() {
        String input = "20/2*40/4+6-19";
        String output = CalculatorUtil.calculate(input);
        Assert.assertEquals("87.0", output);
    }

    @Test
    public void testCalculateSuccess14() {
        String input = "200/2/10";
        String output = CalculatorUtil.calculate(input);
        Assert.assertEquals("10.0", output);
    }

    @Test
    public void testCalculateSuccess15() {
        String input = "2*2*2";
        String output = CalculatorUtil.calculate(input);
        Assert.assertEquals("8.0", output);
    }

    @Test
    public void testCalculateSuccess16() {
        String input = "5*5+2";
        String output = CalculatorUtil.calculate(input);
        Assert.assertEquals("27.0", output);
    }

    @Test
    public void testCalculateSuccess17() {
        String input = "20/2*5/40/4";
        String output = CalculatorUtil.calculate(input);
        Assert.assertEquals("0.3125", output);
    }

    @Test
    public void testCalculateSuccess18() {
        String input = "20*2/5*40*4";
        String output = CalculatorUtil.calculate(input);
        Assert.assertEquals("1280.0", output);
    }

    @Test
    public void testCalculateSuccess19() {
        String input = "-20+2*5/7-10*3*4*5+10/10-2";
        String output = CalculatorUtil.calculate(input);
        Assert.assertEquals("-619.5714285714286", output);
    }

    @Test
    public void testCalculateSuccess20() {
        String input = "+20+8";
        String output = CalculatorUtil.calculate(input);
        Assert.assertEquals("28.0", output);
    }

    @Test
    public void testCalculateSuccess21() {
        String input = "0";
        String output = CalculatorUtil.calculate(input);
        Assert.assertEquals("0.0", output);
    }

    @Test
    public void testCalculateSuccess22() {
        String input = "1/0";

        String output = CalculatorUtil.calculate(input);
        Assert.assertEquals("Infinity", output);
    }

    @Test
    public void testCalculateSuccess23() {
        String input = "0/1/0";

        String output = CalculatorUtil.calculate(input);
        Assert.assertEquals("NaN", output);
    }

    @Test
    public void testCalculateFailureInvalidInput1() {
        String input = "--2+5";

        thrown.expect(UtilException.class);
        thrown.expectMessage(StatusConstants.HttpConstants.INVALID_INPUT.getDesc());

        String output = CalculatorUtil.calculate(input);
        Assert.assertNull(output);
    }

    @Test
    public void testCalculateFailureWithInvalidInput2() {
        String input = "/3+2*20-2/2";

        thrown.expect(UtilException.class);
        thrown.expectMessage(StatusConstants.HttpConstants.INVALID_INPUT.getDesc());

        String output = CalculatorUtil.calculate(input);
        Assert.assertNull(output);
    }

    @Test
    public void testCalculateFailureWithInvalidNumber1() {
        String input = "(2)+1";

        thrown.expect(UtilException.class);
        thrown.expectMessage(StatusConstants.HttpConstants.INVALID_NUMBER.getDesc());

        String output = CalculatorUtil.calculate(input);
        Assert.assertNull(output);
    }

    @Test
    public void testCalculateFailureWithInvalidNumber2() {
        String input = "a+b-c";

        thrown.expect(UtilException.class);
        thrown.expectMessage(StatusConstants.HttpConstants.INVALID_NUMBER.getDesc());

        String output = CalculatorUtil.calculate(input);
        Assert.assertNull(output);
    }

    @Test
    public void testCalculateFailureWithInvalidNumber3() {
        String input = "<>//98123jdadsd>";

        thrown.expect(UtilException.class);
        thrown.expectMessage(StatusConstants.HttpConstants.INVALID_NUMBER.getDesc());

        String output = CalculatorUtil.calculate(input);
        Assert.assertNull(output);
    }

    @Test
    public void testCalculateFailureWithEmptyInput() {
        String input = "";

        thrown.expect(UtilException.class);
        thrown.expectMessage(StatusConstants.HttpConstants.EMPTY_INPUT.getDesc());

        String output = CalculatorUtil.calculate(input);
        Assert.assertNull(output);
    }

    @Test
    public void testCalculateFailureWithNullInput() {
        String input = null;

        thrown.expect(UtilException.class);
        thrown.expectMessage(StatusConstants.HttpConstants.EMPTY_INPUT.getDesc());

        String output = CalculatorUtil.calculate(input);
        Assert.assertNull(output);
    }


//    @Test
//    public void testDivisionOperation() {
//        String input = "20/40/4+2";
//        String output = CalculatorUtil.divisionOperation(input);
//        Assert.assertEquals("87.0", output);
//    }

}
