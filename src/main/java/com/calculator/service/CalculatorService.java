package com.calculator.service;

import com.calculator.exception.UtilException;
import com.calculator.util.CalculatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    private Logger LOG = LoggerFactory.getLogger(CalculatorService.class);

    public String calculate(String input) {
        try {
            String output = CalculatorUtil.calculate(input);
            return output;
        } catch (UtilException e) {
            LOG.error("Failed calculating input: {} with error: {} {}", input, e, e.getMessage());
            throw e;
        }

    }

}


