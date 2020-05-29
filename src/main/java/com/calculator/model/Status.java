package com.calculator.model;

import com.calculator.constance.StatusConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Status {

    private String code;

    private String message;

    public Status(StatusConstants.HttpConstants httpConstantsExpect) {
        this.code = httpConstantsExpect.getCode();
        this.message = httpConstantsExpect.getDesc();
    }

    @JsonIgnore
    public boolean isSuccess() {
        return (StatusConstants.HttpConstants.SUCCESS.getCode()).equals(code);
    }

}