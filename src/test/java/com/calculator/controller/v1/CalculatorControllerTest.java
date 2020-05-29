package com.calculator.controller.v1;

import com.calculator.constance.StatusConstants;
import com.calculator.controller.V1.CalculatorController;
import com.calculator.exception.ServiceException;
import com.calculator.exception.UtilException;
import com.calculator.model.Payload;
import com.calculator.model.Response;
import com.calculator.service.CalculatorService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorControllerTest {

    @InjectMocks
    private CalculatorController calculatorController;

    @Mock
    private CalculatorService calculatorService;

    @Test
    public void testCalculateSuccess() {
        Payload payload = Payload.builder()
                .input("2+5")
                .build();
        Mockito.doReturn("7").when(calculatorService).calculate(payload.getInput());
        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
        Response<String> response = calculatorController.calculate(payload, mockHttpServletResponse);

        // then
        Assert.assertEquals("7", response.getData());
        Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
        Assert.assertEquals(StatusConstants.HttpConstants.SUCCESS.getCode(), response.getStatus().getCode());
        Assert.assertEquals(StatusConstants.HttpConstants.SUCCESS.getDesc(), response.getStatus().getMessage());

    }

    @Test
    public void testCalculateInvalidRequestException() {
        Payload payload = Payload.builder()
                .input("")
                .build();
        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
        Response<String> response = calculatorController.calculate(payload, mockHttpServletResponse);

        // then
        Assert.assertNull(response.getData());
        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), mockHttpServletResponse.getStatus());
        Assert.assertEquals(StatusConstants.HttpConstants.EMPTY_INPUT.getCode(), response.getStatus().getCode());
        Assert.assertEquals(StatusConstants.HttpConstants.EMPTY_INPUT.getDesc(), response.getStatus().getMessage());

    }

    @Test
    public void testCalculateInvalidUtilException() {
        Payload payload = Payload.builder()
                .input("7+1")
                .build();
        Mockito.doThrow(new UtilException(StatusConstants.HttpConstants.INVALID_INPUT)).when(calculatorService).calculate(payload.getInput());
        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
        Response<String> response = calculatorController.calculate(payload, mockHttpServletResponse);

        // then
        Assert.assertNull(response.getData());
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), mockHttpServletResponse.getStatus());
        Assert.assertEquals(StatusConstants.HttpConstants.INVALID_INPUT.getCode(), response.getStatus().getCode());
        Assert.assertEquals(StatusConstants.HttpConstants.INVALID_INPUT.getDesc(), response.getStatus().getMessage());

    }

    @Test
    public void testCalculateInvalidException() {
        Payload payload = Payload.builder()
                .input("7+1")
                .build();
        Mockito.doThrow(Exception.class).when(calculatorService).calculate(payload.getInput());
        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
        Response<String> response = calculatorController.calculate(payload, mockHttpServletResponse);

        // then
        Assert.assertNull(response.getData());
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), mockHttpServletResponse.getStatus());
        Assert.assertEquals(StatusConstants.HttpConstants.INTERNAL_SERVER_ERROR.getCode(), response.getStatus().getCode());
        Assert.assertEquals(StatusConstants.HttpConstants.INTERNAL_SERVER_ERROR.getDesc(), response.getStatus().getMessage());

    }

}
