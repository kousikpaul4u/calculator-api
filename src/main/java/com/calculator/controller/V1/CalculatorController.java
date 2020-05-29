package com.calculator.controller.V1;

import com.calculator.constance.StatusConstants;
import com.calculator.controller.ControllerSupport;
import com.calculator.exception.InvalidRequestException;
import com.calculator.exception.ServiceException;
import com.calculator.exception.UtilException;
import com.calculator.model.Payload;
import com.calculator.model.Response;
import com.calculator.service.CalculatorService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
@RequestMapping(path = "/calculator-svc")
public class CalculatorController implements ControllerSupport {

    private Logger LOG = LoggerFactory.getLogger(CalculatorController.class);

    @Autowired
    private CalculatorService calculatorService;

    @PostMapping(path = "/api/v1/calculate")
    @ApiOperation(value = "Calculate with BODMAS rule", notes = "Possible response codes: 0, CAL0001, CAL0002, CAL0003, CAL9999")
    public Response<String> calculate(@RequestBody Payload payload, HttpServletResponse response) {

        LOG.info("Start calculating with: {}", payload.getInput());
        try {
            if (payload == null || StringUtils.isEmpty(payload.getInput())) {
                throw new InvalidRequestException(StatusConstants.HttpConstants.EMPTY_INPUT);
            }
            String calculatedValue = calculatorService.calculate(payload.getInput());
            LOG.info("Done calculating with: {} and output: {}", payload.getInput(), calculatedValue);
            return success(calculatedValue);
        } catch (InvalidRequestException e) {
            LOG.error("Failed calculating with error: {} {}", e, e.getMessage());
            return badRequest(e.getStatus(), response);
        } catch (UtilException e) {
            LOG.error("Failed calculating with: {} and error: {} {}", payload.getInput(), e, e.getMessage());
            return serverError(e.getStatus(), response);
        } catch (Exception e) {
            LOG.error("Failed calculating with: {} and error: {} {}", payload.getInput(), e, e.getMessage());
            return serverError(response);
        }

    }

}
