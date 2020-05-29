package com.calculator.exception;

import com.calculator.constance.StatusConstants;
import lombok.Getter;

@Getter
public class UtilException extends RuntimeException {

    private StatusConstants.HttpConstants status;

    public UtilException(StatusConstants.HttpConstants status) {
        super(status.getDesc(), null);
        this.status = status;
    }

}
