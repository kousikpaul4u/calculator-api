package com.calculator.exception;

import com.calculator.constance.StatusConstants.HttpConstants;
import lombok.Getter;

@Getter
public class InvalidRequestException extends RuntimeException {

    private HttpConstants status;

    public InvalidRequestException(HttpConstants status) {
        super(status.getDesc(), null);
        this.status = status;
    }

}