package com.calculator.constance;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class StatusConstants {

    @Getter
    @AllArgsConstructor
    public enum HttpConstants {
        SUCCESS("0", "Success"),
        EMPTY_INPUT("CAL0001", "Please enter input. e.g. 'input': '4+2*10-20/2'"),
        INVALID_INPUT("CAL0002", "Invalid input"),
        INVALID_NUMBER("CAL0003", "Invalid number"),

        BAD_REQUEST("CAL9998", "Bad request"),
        INTERNAL_SERVER_ERROR("CAL9999", "Internal server error.");

        private String code;
        private String desc;
    }

}
