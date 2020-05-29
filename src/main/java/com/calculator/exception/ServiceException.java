package com.calculator.exception;

import com.calculator.constance.StatusConstants.HttpConstants;
import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {

    private HttpConstants status;

    public ServiceException(HttpConstants status) {
        super(status.getDesc(), null);
        this.status = status;
    }

}
